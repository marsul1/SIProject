//package model.matches;
//
//
//import jakarta.persistence.*;
//
//import java.io.Serializable;
//import java.sql.Timestamp;
//
//@Entity
//@Table(name="matches")
//@NamedQuery(name="Matches.findAll", query="SELECT m FROM Matches m")
//public class Matches  {
//
//    @EmbeddedId
//    private MatchesPK id;
//
//
//    private Timestamp start_time;
//
//    private Timestamp end_time;
//
//
//    public Matches() {
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
//    public Timestamp getStartTime() {
//        return this.start_time;
//    }
//
//    public void setStartTime(Timestamp startTime) {
//        this.start_time = startTime;
//    }
//
//    public Timestamp getEndTime() {
//        return this.end_time;
//    }
//
//    public void setEndTime(Timestamp endTime) {
//        this.end_time = endTime;
//    }
//
//}
