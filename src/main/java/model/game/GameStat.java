package model.game;

import jakarta.persistence.*;
import model.players.Player;

@Entity
@Table(name = "game_stats")
@NamedQuery(name = "GameStats.findAll", query = "SELECT gs FROM GameStat gs")
public class GameStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private long num_matches;

    private long num_players;

    private long total_points;

    @OneToOne
    @JoinColumn(name="game_ref", unique = true,  referencedColumnName = "reference")
    private Game game ;

    public GameStat() {
    }

    //@Id
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer psId) {
        this.id = psId;
    }

    public long getNumMatches() {
        return this.num_matches;
    }

    public void setNumMatches(long numMatches) {
        this.num_matches = numMatches;
    }

    public long getNumPlayers() {
        return this.num_players;
    }

    public void setNumPlayers(long num_players) {
        this.num_players = num_players;
    }

    public long getTotalPoints() {
        return this.total_points;
    }

    public void setTotalPoints(long totalPoints) {
        this.total_points = totalPoints;
    }

    public Game getGame() {
        return this.game;
    }

    public void setGame(Game game ) {
        this.game = game;
    }

    public String toString() {
        return "GameStat(" + getId() + ", " + getGame().getReference() + ", " + getNumMatches() + ", " + getNumPlayers() + ", " + getTotalPoints() + ")";
    }
}