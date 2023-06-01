package model.matches;

import jakarta.persistence.*;
import model.players.PlayerPurchase;
import model.players.PlayerPurchasePK;

import java.util.List;

@Entity
@Table(name="multi_player_match")
@NamedQuery(name="MultiPlayerMatch.findAll", query="SELECT mp FROM MultiPlayerMatch mp")
public class MultiPlayerMatch {

    @EmbeddedId
    private MatchPK id;

    private String state;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name="match_number", referencedColumnName="match_number", insertable = false, updatable = false),
            @JoinColumn(name="game_ref", referencedColumnName="game_ref", insertable = false, updatable = false)
    })
    private Match match;

    @OneToMany(mappedBy="multiPlayerMatch",cascade=CascadeType.PERSIST)
    private List<PlaysMulti> playsMultis;
    public MultiPlayerMatch() {
    }

    //@Id
    public MatchPK getId() {
        return this.id;
    }

    public void setId(MatchPK mId) {
        this.id = mId;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String mState) {
        this.state = mState;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public List<PlaysMulti> getPlaysMultis() {
        return playsMultis;
    }

    public void setPlaysMultis(List<PlaysMulti> playsMultis) {
        this.playsMultis = playsMultis;
    }

    public String toString() {
        return "MultiPlayerMatch(" + getId().getMatchNumber() + ", " + getId().getGameRef() + ", " + getState() + ")";
    }
}
