package model.players;

import jakarta.persistence.*;
import model.badges.Badge;
import model.conversation.Conversation;
import model.conversation.Message;
import model.matches.PlaysMulti;
import model.matches.SinglePlayerMatch;
import model.regions.*;

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
    private Regions region;

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

    @OneToMany(mappedBy="player",cascade=CascadeType.PERSIST)
    private List<PlayerPurchase> playerPurchases;

    @ManyToMany(cascade=CascadeType.REMOVE)
    @JoinTable(name="player_badge",
            joinColumns=@JoinColumn(name="player_id"),
            inverseJoinColumns = {
                    @JoinColumn(name = "badge_name", referencedColumnName = "name"),
                    @JoinColumn(name = "game_ref", referencedColumnName = "game_ref")
            }
    )
    private Set<Badge> badges;
    @OneToMany(mappedBy="player",cascade=CascadeType.PERSIST)
    private List<SinglePlayerMatch> singlePlayerMatches;

    @OneToMany(mappedBy="player",cascade=CascadeType.PERSIST)
    private List<PlaysMulti> playsMultis;

    @ManyToMany(cascade=CascadeType.REMOVE)
    @JoinTable(name="friends",
            joinColumns=@JoinColumn(name="player_id"),
            inverseJoinColumns=@JoinColumn(name="friend_id"))
    private Set<Player> friends;
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
        return this.region;
    }

    public void setRegion(Regions pRegion) {
        this.region = pRegion;
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

    public List<PlayerPurchase> getPlayerPurchases() {
        return playerPurchases;
    }

    public void setPlayerPurchases(List<PlayerPurchase> playerPurchases) {
        this.playerPurchases = playerPurchases;
    }

    public void addPlayerPurchases(PlayerPurchase playerPurchase) {
        this.playerPurchases.add(playerPurchase);
    }

    public void removePlayerPurchases(PlayerPurchase playerPurchase) {
        this.playerPurchases.remove(playerPurchase);
    }

    public List<SinglePlayerMatch> getSinglePlayerMatches() {
        return singlePlayerMatches;
    }

    public void setSinglePlayerMatches(List<SinglePlayerMatch> singlePlayerMatches) {
        this.singlePlayerMatches = singlePlayerMatches;
    }

    public void addSinglePlayerMatch(SinglePlayerMatch singlePlayerMatch) {
        this.singlePlayerMatches.add(singlePlayerMatch);
    }

    public void removeSinglePlayerMatch(SinglePlayerMatch singlePlayerMatch) {
        this.singlePlayerMatches.remove(singlePlayerMatch);
    }

    public List<PlaysMulti> getPlaysMultis() {
        return playsMultis;
    }

    public void setPlaysMultis(List<PlaysMulti> playsMultis) {
        this.playsMultis = playsMultis;
    }

    public void addPlaysMulti(PlaysMulti singlePlayerMatch) {
        this.playsMultis.add(singlePlayerMatch);
    }

    public void removePlaysMulti(PlaysMulti singlePlayerMatch) {
        this.singlePlayerMatches.remove(singlePlayerMatch);
    }

    public String toString() {
        return "Player(" + getId() + ", " + getEmail() + ", " + getUsername() + ", " + getState() + ", " + getRegion().getName() + ")";
    }

    public Set<Player> getFriends() {
        return friends;
    }

    public void setFriends(Set<Player> friends) {
        this.friends = friends;
    }

    public Set<Badge> getBadges() {
        return badges;
    }

    public void setBadges(Set<Badge> badges) {
        this.badges = badges;
    }
}