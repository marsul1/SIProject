package model.badges;

import jakarta.persistence.*;
import model.game.Game;
import model.players.Player;

import java.util.Set;

@Entity
@Table(name="badges")
@NamedQuery(name="Badge.findAll", query="SELECT b FROM Badge b")
public class Badge {

    @EmbeddedId
    private BadgePK id;

    private String image_url;

    private Integer points_limit;

    @MapsId("game_ref")
    @ManyToOne
    @JoinColumn(name="game_ref")
    private Game game;

    @ManyToMany(mappedBy="badges",cascade=CascadeType.REMOVE)
    private Set<Player> players;

    public Badge() {
    }

    //@Id
    public BadgePK getId() {
        return this.id;
    }

    public void setId(BadgePK bId) {
        this.id = bId;
    }

    public String getImageUrl() {
        return this.image_url;
    }

    public void setImageUrl(String imageUrl) {
        this.image_url = imageUrl;
    }

    public Integer getPointsLimit() {
        return this.points_limit;
    }

    public void setPointsLimit(Integer pointsLimit) {
        this.points_limit = pointsLimit;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void removePlayer(Player player) {
        this.players.remove(player);
    }

    public String toString() {
        return "Badge(" + getId().getBadgeName() + ", " + getId().getGameRef() + ", " + getPointsLimit() + ", " + getImageUrl() + ")";
    }
}
