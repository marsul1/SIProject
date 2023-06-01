package model.players;
import jakarta.persistence.*;
import model.conversation.MessagePK;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PlayerPurchasePK implements Serializable {
    private int player_id;

    @Column( insertable=false, updatable=false)
    private String game_ref;

    public PlayerPurchasePK() {

    }

    public int getPlayerId() {
        return this.player_id;
    }

    public void setPlayerId(int player_id) {
        this.player_id = player_id;
    }

    public String getGameRef() {
        return this.game_ref;
    }

    public void setGameRef(String gameRef) {
        this.game_ref = gameRef;
    }
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlayerPurchasePK)) {
            return false;
        }
        PlayerPurchasePK castOther = (PlayerPurchasePK)other;
        return
                (this.player_id == castOther.player_id)
                        && this.game_ref.equals(castOther.game_ref);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player_id);
    }

    public String toString() {
        return "PlayerpurchasePK(" + getPlayerId() + ", " + getGameRef() + ")";
    }
}
