//package model.badges;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name="badges")
//@NamedQuery(name="Badges.findAll", query="SELECT b FROM Badges b")
//public class Badges {
//
//    @EmbeddedId
//    private BadgesPK id;
//
//    private String image_url;
//
//    private Integer points_limit;
//
//    public Badges() {
//    }
//
//    //@Id
//    public BadgesPK getId() {
//        return this.id;
//    }
//
//    public void setId(BadgesPK bId) {
//        this.id = bId;
//    }
//
//    public String getImageUrl() {
//        return this.image_url;
//    }
//
//    public void setImageUrl(String imageUrl) {
//        this.image_url = imageUrl;
//    }
//
//    public Integer getPointsLimit() {
//        return this.points_limit;
//    }
//
//    public void setPointsLimit(Integer pointsLimit) {
//        this.points_limit = pointsLimit;
//    }
//
//}
