package model.matches;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MatchPK implements Serializable{

    private String game_ref;

    private Integer match_number;

    public MatchPK() {

    }
    public String getGameRef() {
        return this.game_ref;
    }
    public void setGameRef(String gameRef) {
        this.game_ref = gameRef;
    }


    public Integer getMatchNumber() {
        return this.match_number;
    }
    public void setMatchNumber(Integer matchNumber) {
        this.match_number = matchNumber;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MatchPK)) {
            return false;
        }
        MatchPK castOther = (MatchPK)other;
        return
                (this.match_number == castOther.match_number)
                        && this.game_ref.equals(castOther.game_ref);
    }

    @Override
    public int hashCode() {
        return Objects.hash(game_ref);
    }

    public String toString() {
        return "MatchPK(" + getMatchNumber() + ", " + getGameRef() + ")";
    }
}
