package model.conversation;
import jakarta.persistence.*;
import model.players.Player;



@Entity
@Table(name="messages")
@NamedQuery(name="Messages.findAll", query="SELECT m FROM Message m")
public class Message {

    @EmbeddedId
    private MessagePK id;

    private String name;

    @MapsId("conversation_id")
    @ManyToOne
    @JoinColumn(name="conversation_id")
    private Conversation conversation;

    @ManyToOne
    @JoinColumn(name="player_id")
    private Player player;

    public Message() {
    }

    //@Id
    public MessagePK getId() {
        return this.id;
    }

    public void setId(MessagePK mId) {
        this.id = mId;
    }

    public String getMessageName() {
        return this.name;
    }

    public void setMessageName(String cName) {
        this.name = cName;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Conversation getConversation() {
        return this.conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }


}
