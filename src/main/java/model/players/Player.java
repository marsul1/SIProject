package model.players;

import jakarta.persistence.*;
import model.conversation.Conversation;
//import model.conversation.Messages;
//import model.conversation.Message;
import model.conversation.Message;
import model.regions.*;
import org.glassfish.jaxb.core.marshaller.Messages;

import java.util.List;
import java.util.Set;

@Entity
@Table(name="players")
@NamedQuery(name="Players.findAll", query="SELECT p FROM Player p")
public class Player {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;


    private String email;


    private String username;


    private String state;

    @ManyToOne
    @JoinColumn(name="region_name", referencedColumnName = "name")
    private Regions region_name;

    @OneToOne(mappedBy="player",cascade=CascadeType.PERSIST)
    private PlayerStat playerStat;

    @ManyToMany(cascade=CascadeType.REMOVE)
    @JoinTable(name="players_conversation",
            joinColumns=@JoinColumn(name="player_id"),
            inverseJoinColumns=@JoinColumn(name="conversations_id"))
    private Set<Conversation> conversations;

    @OneToMany(mappedBy="player",cascade=CascadeType.PERSIST, orphanRemoval=true)
    @JoinColumns({
            @JoinColumn(name="message_number"),
            @JoinColumn(name="conversation_id", referencedColumnName="id")
    })
    private List<Message> messages;

    public Player() {
    }

    //@Id
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer pId) {
        this.id = pId;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String pEmail) {
        this.email = pEmail;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String pUsername) {
        this.username = pUsername;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String pState) {
        this.state = pState;
    }

    public Regions getRegion() {
        return this.region_name;
    }

    public void setRegion(Regions pRegion) {
        this.region_name = pRegion;
    }



    public Set<Conversation> getConversations() {
        return this.conversations;
    }

    public void setConversations(Set<Conversation> conversations) {
        this.conversations = conversations;
    }

    public void addConversation(Conversation conversation) {
        this.conversations.add(conversation);
    }

    public void removeConversation(Conversation conversation) {
        this.conversations.remove(conversation);
    }

    public List<Message> getMessages() {
        return this.messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }

    public void removeMessage(Message message) {
        this.messages.remove(message);
    }

    public PlayerStat getPlayerStat() {
        return playerStat;
    }

    public void setPlayerStat(PlayerStat playerStat) {
        this.playerStat = playerStat;
    }
}