package model.matches;

import jakarta.persistence.*;

import model.players.Player;


@Entity
@Table(name="plays_multi")
@NamedQuery(name="PlaysMulti.findAll", query="SELECT pm FROM PlaysMulti pm")
public class PlaysMulti {
    @EmbeddedId
    private PlaysMultiPK id;
    private int points;

    //bi-directional many-to-one association to Player
    @ManyToOne
    @MapsId("player_id")
    @JoinColumn(name="player_id")
    private Player player;


    //bi-directional many-to-one association to Game
    @MapsId("matchPK")
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="match_number", referencedColumnName="match_number"),
            @JoinColumn(name="game_ref", referencedColumnName="game_ref")
    })
    private MultiPlayerMatch multiPlayerMatch;

    public PlaysMultiPK getId() {
        return id;
    }

    public void setId(PlaysMultiPK id) {
        this.id = id;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public MultiPlayerMatch getMultiPlayerMatch() {
        return multiPlayerMatch;
    }

    public void setMultiPlayerMatch(MultiPlayerMatch multiPlayerMatch) {
        this.multiPlayerMatch = multiPlayerMatch;
    }
}
