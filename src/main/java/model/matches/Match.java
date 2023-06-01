package model.matches;


import jakarta.persistence.*;
import model.conversation.Conversation;
import model.conversation.Message;
import model.game.Game;
import model.regions.Regions;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="matches")
@NamedQuery(name="Matches.findAll", query="SELECT m FROM Match m")
public class Match implements Serializable {

    @EmbeddedId
    private MatchPK id;

    private Timestamp start_time;

    private Timestamp end_time;

    @MapsId("game_ref")
    @ManyToOne
    @JoinColumn(name="game_ref")
    private Game game;

    @ManyToOne
    @JoinColumn(name="region_name", referencedColumnName = "name")
    private Regions region;

    @OneToOne(mappedBy="match")
    private SinglePlayerMatch singlePlayerMatch;

    @OneToOne(mappedBy="match")
    private MultiPlayerMatch multiPlayerMatch;
    public Match() {
    }

    //@Id
    public MatchPK getId() {
        return this.id;
    }

    public void setId(MatchPK mId) {
        this.id = mId;
    }

    public Timestamp getStartTime() {
        return this.start_time;
    }

    public void setStartTime(Timestamp startTime) {
        this.start_time = startTime;
    }

    public Timestamp getEndTime() {
        return this.end_time;
    }

    public void setEndTime(Timestamp endTime) {
        this.end_time = endTime;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Regions getRegion() {
        return region;
    }

    public void setRegion(Regions region) {
        this.region = region;
    }

    public SinglePlayerMatch getSinglePlayerMatch() {
        return singlePlayerMatch;
    }

    public void setSinglePlayerMatch(SinglePlayerMatch singlePlayerMatch) {
        this.singlePlayerMatch = singlePlayerMatch;
    }

    public MultiPlayerMatch getMultiPlayerMatch() {
        return multiPlayerMatch;
    }

    public void setMultiPlayerMatch(MultiPlayerMatch multiPlayerMatch) {
        this.multiPlayerMatch = multiPlayerMatch;
    }

    public String toString() {
        return "Match(" + getId().getMatchNumber() + ", " + getStartTime() + ", " + getEndTime() + ", " + getId().getGameRef() + ", " + getRegion().getName() + ")";
    }
}
