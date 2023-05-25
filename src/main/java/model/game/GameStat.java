package model.game;

import jakarta.persistence.*;
import model.players.Player;

@Entity
@Table(name = "game_stats")
@NamedQuery(name = "GameStats.findAll", query = "SELECT gs FROM GameStat gs")
public class GameStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long num_matches;

    private long num_games_played;

    private long total_points;

    @OneToOne
    @JoinColumn(name="game_ref", unique = true,  referencedColumnName = "reference")
    private Game game ;

    public GameStat() {
    }

    //@Id
    public long getId() {
        return this.id;
    }

    public void setId(long psId) {
        this.id = psId;
    }

    public long getNum_matches() {
        return this.num_matches;
    }

    public void setNum_matches(long numMatches) {
        this.num_matches = numMatches;
    }

    public long getNum_games_played() {
        return this.num_games_played;
    }

    public void setNum_games_played(long numGamesPlayed) {
        this.num_games_played = numGamesPlayed;
    }

    public long getTotal_points() {
        return this.total_points;
    }

    public void setTotal_points(long totalPoints) {
        this.total_points = totalPoints;
    }

}