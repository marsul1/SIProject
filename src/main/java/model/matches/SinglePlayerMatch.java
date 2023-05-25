//package model.matches;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name="single_player_match")
//@NamedQuery(name="SinglePlayerMatch.findAll", query="SELECT sm FROM SinglePlayerMatch sm")
//public class SinglePlayerMatch {
//    @EmbeddedId
//    private MatchesPK id;
//
//
//    private Integer points;
//
//    private Integer difficulty;
//
//
//    public SinglePlayerMatch() {
//    }
//
//    //@Id
//    public MatchesPK getId() {
//        return this.id;
//    }
//
//    public void setId(MatchesPK mId) {
//        this.id = mId;
//    }
//
//    public Integer getPoints() {
//        return this.points;
//    }
//
//    public void setPoints(Integer sPoints) {
//        this.points = sPoints;
//    }
//
//    public Integer getDifficulty() {
//        return this.difficulty;
//    }
//
//    public void setDifficulty(Integer sDifficulty) {
//        this.difficulty = sDifficulty;
//    }
//
//}
