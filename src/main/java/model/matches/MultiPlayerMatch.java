//package model.matches;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name="multi_player_match")
//@NamedQuery(name="MultiPlayerMatch.findAll", query="SELECT mp FROM MultiPlayerMatch mp")
//public class MultiPlayerMatch {
//
//    @EmbeddedId
//    private MatchesPK id;
//
//    private String state;
//
//
//    public MultiPlayerMatch() {
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
//    public String getState() {
//        return this.state;
//    }
//
//    public void setState(String mState) {
//        this.state = mState;
//    }
//}
