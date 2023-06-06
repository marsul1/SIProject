package model.matches;
import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public class MultiAndSinglePK implements Serializable {
    private Integer player_id;

    @Embedded
    private MatchPK matchPK;

    public MultiAndSinglePK() {

    }

    public int getPlayerId() {
        return this.player_id;
    }

    public void setPlayerId(int player_id) {
        this.player_id = player_id;
    }

    public MatchPK getMatchPK() {
        return matchPK;
    }

    public void setMatchPK(MatchPK matchPK) {
        this.matchPK = matchPK;
    }
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MultiAndSinglePK)) {
            return false;
        }
        MultiAndSinglePK castOther = (MultiAndSinglePK)other;
        return
                this.getMatchPK().getMatchNumber().equals(castOther.getMatchPK().getMatchNumber())
                        && this.getMatchPK().getGameRef().equals(castOther.getMatchPK().getGameRef())
                        && this.player_id.equals(castOther.player_id);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.getMatchPK().getMatchNumber().hashCode();
        hash = hash * prime + this.getMatchPK().getGameRef().hashCode();
        hash = hash * prime + this.player_id.hashCode();

        return hash;
    }

    public String toString() {
        return "PlaysmultiPK(" + getMatchPK() + ", " + getPlayerId() + ")";
    }
}
