package model.matches;

import jakarta.persistence.*;
import model.players.Player;

import java.io.Serializable;

@Entity
@Table(name="single_player_match")
@NamedQuery(name="SinglePlayerMatch.findAll", query="SELECT sm FROM SinglePlayerMatch sm")
public class SinglePlayerMatch  implements Serializable {
    @EmbeddedId
    private MatchPK id;

    private Integer points;

    private Integer difficulty;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name="match_number", referencedColumnName="match_number", insertable = false, updatable = false),
            @JoinColumn(name="game_ref", referencedColumnName="game_ref", insertable = false, updatable = false)
    })
    private Match match;

    @ManyToOne
    @JoinColumn(name="player_id")
    private Player player;

    public SinglePlayerMatch() {
    }

    public MatchPK getId() {
        return this.id;
    }

    public void setId(MatchPK mId) {
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
}
