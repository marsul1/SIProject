//package model.matches;
//
//import jakarta.persistence.*;
//
//import java.io.Serializable;
//import java.util.Objects;
//
//@Embeddable
//public class MatchesPK implements Serializable{
//    @Column(name = "game_ref", insertable=true, updatable=false)
//    private String game_ref;
//
//    private Integer match_number;
//
//    public MatchesPK () {
//
//    }
//    public String getGameRef() {
//        return this.game_ref;
//    }
//    public void setGameRef(String gameRef) {
//        this.game_ref = gameRef;
//    }
//
//
//    public Integer getMatchNumber() {
//        return this.match_number;
//    }
//    public void setMatchNumber(Integer matchNumber) {
//        this.match_number = matchNumber;
//    }
//
//    @Override
//    public boolean equals(Object other) {
//        if (this == other) {
//            return true;
//        }
//        if (!(other instanceof MatchesPK)) {
//            return false;
//        }
//        MatchesPK castOther = (MatchesPK)other;
//        return
//                (this.game_ref == castOther.game_ref)
//                        && this.match_number.equals(castOther.match_number);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(game_ref);
//    }
//}
