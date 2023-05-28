package model.game;

import jakarta.persistence.*;
import model.conversation.Message;
import model.matches.Match;
import model.players.PlayerPurchase;
import model.players.PlayerStat;

import java.util.List;

@Entity
@Table(name="game")
@NamedQuery(name="Game.findAll", query="SELECT g FROM Game g")
public class Game /*implements Serializable*/ {

    @Id
    private String reference;


    private String name;


    private String url;


    @OneToOne(mappedBy="game",cascade=CascadeType.PERSIST)
    private GameStat gameStat;

    @OneToMany(mappedBy="game",cascade=CascadeType.PERSIST, orphanRemoval=true)
    private List<Match> matches;

    @OneToMany(mappedBy="game",cascade=CascadeType.PERSIST)
    private List<PlayerPurchase> playerPurchase;

    public Game() {
    }

    //@Id
    public String getReference() {
        return this.reference;
    }

    public void setReference(String gReference) {
        this.reference = gReference;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String gName) {
        this.name = gName;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String gUrl ) {
        this.url = gUrl;
    }

    public GameStat getGameStat() {
        return this.gameStat;
    }

    public void setGameStat(GameStat gameStat ) {
        this.gameStat = gameStat;
    }

    public List<PlayerPurchase> getPlayerPurchase() {
        return this.playerPurchase;
    }

    public void setPlayerPurchase(List<PlayerPurchase> playerPurchase) {
        this.playerPurchase = playerPurchase;
    }
}