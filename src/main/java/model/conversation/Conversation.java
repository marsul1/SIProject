package model.conversation;
import jakarta.persistence.*;
import model.players.Player;

import java.util.List;
import java.util.Set;

@Entity
@Table(name="conversations")
@NamedQuery(name="Conversations.findAll", query="SELECT c FROM Conversation c")
public class Conversation {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToMany(mappedBy="conversations",cascade=CascadeType.REMOVE)
    private Set<Player> players;

    @OneToMany(mappedBy="conversation",cascade=CascadeType.PERSIST, orphanRemoval=true)
    private List<Message> messages;

    public Conversation() {
    }

    //@Id
    public long getId() {
        return this.id;
    }

    public void setId(long cId) {
        this.id = cId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String cName) {
        this.name = cName;
    }

    public Set<Player> getPlayers() {
        return this.players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public void addPlayer(Player player) {
        this.players.add(player);

    }

    public void removePlayer(Player player) {
        this.players.remove(player);
    }

    public List<Message> getMessages() {
        return this.messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void addMessages(Message message) {
        getMessages().add(message);
        message.setConversation(this);
    }

    public void removeMessages(Message message) {
        getMessages().remove(message);
        message.setConversation(null);
    }
}
