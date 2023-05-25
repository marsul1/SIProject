package model.players;

import jakarta.persistence.*;
import model.conversation.Conversation;
//import model.conversation.Messages;
import model.regions.*;

import java.util.Set;

@Entity
@Table(name="players")
@NamedQuery(name="Players.findAll", query="SELECT p FROM Player p")
public class Player {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;


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

//    @OneToMany
//    @JoinColumns({
//            @JoinColumn(name="message_number"),
//            @JoinColumn(name="conversation_id", referencedColumnName="id")
//    })
//    private List<Messages> messages;

    public Player() {
    }

    //@Id
    public long getId() {
        return this.id;
    }

    public void setId(long pId) {
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

    public PlayerStat getPlayerStats() {
        return this.playerStat;
    }

    public void setPlayerStats(PlayerStat playerStat) {
        this.playerStat = playerStat;
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

//    public List<Messages> getMessages() {
//        return this.messages;
//    }
//
//    public void setMessages(List<Messages> messages) {
//        this.messages = messages;
//    }
}