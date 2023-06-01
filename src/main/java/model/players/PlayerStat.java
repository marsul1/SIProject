package model.players;

import jakarta.persistence.*;

@Entity
@Table(name = "player_stats")
@NamedQuery(name = "PlayerStats.findAll", query = "SELECT ps FROM PlayerStat ps")
public class PlayerStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private long num_matches;


    private long num_games_played;


    private long total_points;

    @OneToOne
    @JoinColumn(name="player_id", unique = true,  referencedColumnName = "id")
    private Player player ;

    public PlayerStat() {
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

    public long getNumGamesPlayed() {
        return this.num_games_played;
    }

    public void setNumGamesPlayed(long numGamesPlayed) {
        this.num_games_played = numGamesPlayed;
    }

    public long getTotalPoints() {
        return this.total_points;
    }

    public void setTotalPoints(long totalPoints) {
        this.total_points = totalPoints;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String toString() {
        return "PlayerStat(" + getId() + ", " + getPlayer().getId() + ", " + getNumMatches() + ", " + getNumGamesPlayed() + ", " + getTotalPoints() + ")";
    }
}
