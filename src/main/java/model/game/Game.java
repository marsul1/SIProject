package model.game;

import jakarta.persistence.*;
import model.players.PlayerStat;

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

}