package model.players;
import jakarta.persistence.*;
import model.game.Game;
import org.eclipse.persistence.jpa.jpql.parser.NumericLiteral;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "player_purchase")
@NamedQuery(name="PlayerPurchase.findAll", query="SELECT ps FROM PlayerPurchase ps")
public class PlayerPurchase {

    @EmbeddedId
    private PlayerPurchasePK id;
    private Timestamp purchase_date;
    private BigDecimal price ;

    //bidirectional many-to-one association to Player
    @ManyToOne
    @MapsId("player_id")
    @JoinColumn(name="player_id")
    private Player player;


    //bidirectional many-to-one association to Game
    @ManyToOne
    @MapsId("game_ref")
    @JoinColumn(name="game_ref")
    private Game game;

    public Timestamp getPurchaseDate() {
        return purchase_date;
    }

    public void setPurchaseDate(Timestamp purchase_date) {
        this.purchase_date = purchase_date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public PlayerPurchasePK getId() {
        return id;
    }

    public void setId(PlayerPurchasePK id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String toString() {
        return "PlayerPurchase(" + getId().getPlayerId() + ", " + getId().getGameRef() + ", " + getPurchaseDate() + ", " + getPrice() + ")";
    }
}
