//package model.badges;
//
//import jakarta.persistence.*;
//
//import java.io.Serializable;
//import java.util.Objects;
//
//@Embeddable
//public class BadgesPK implements Serializable {
//
//    @Column(name = "game_ref", insertable=true, updatable=false)
//    private String game_ref;
//
//    private String name;
//
//    public BadgesPK () {
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
//    public String getBadgeName() {
//        return this.name;
//    }
//    public void setBadgeName(String bName) {
//        this.name = bName;
//    }
//
//    @Override
//    public boolean equals(Object other) {
//        if (this == other) {
//            return true;
//        }
//        if (!(other instanceof BadgesPK)) {
//            return false;
//        }
//        BadgesPK castOther = (BadgesPK)other;
//        return
//                (this.game_ref == castOther.game_ref)
//                        && this.name.equals(castOther.name);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(game_ref);
//    }
//}
