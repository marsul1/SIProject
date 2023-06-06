package model.matches;

import jakarta.persistence.*;
import model.players.Player;

import java.io.Serializable;

@Entity
@Table(name="single_player_match")
@NamedQuery(name="SinglePlayerMatch.findAll", query="SELECT sm FROM SinglePlayerMatch sm")
public class SinglePlayerMatch  implements Serializable {
    @EmbeddedId
    private MultiAndSinglePK id;

    private Integer points;

    private Integer difficulty;

    @OneToOne
    @MapsId("matchPK")
    @JoinColumns({
            @JoinColumn(name="match_number", referencedColumnName="match_number"),
            @JoinColumn(name="game_ref", referencedColumnName="game_ref")
    })
    private Match match;

    @ManyToOne
    @MapsId("player_id")
    @JoinColumn(name="player_id")
    private Player player;

    public SinglePlayerMatch() {
    }

    public MultiAndSinglePK getId() {
        return this.id;
    }

    public void setId(MultiAndSinglePK mId) {
        this.id = mId;
    }

    public Integer getPoints() {
        return this.points;
    }

    public void setPoints(Integer sPoints) {
        this.points = sPoints;
    }

    public Integer getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(Integer sDifficulty) {
        this.difficulty = sDifficulty;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String toString() {
        return "SinglePlayerMatch(" + getId().getMatchPK().getMatchNumber() + ", " + getId().getMatchPK().getGameRef() + ", " + getPlayer().getId() + ", " + getDifficulty() + ", " + getPoints() + ")";
    }
}
