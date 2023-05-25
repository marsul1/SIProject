//package model.game;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name="game")
//@NamedQuery(name="Game.findAll", query="SELECT g FROM Game g")
//public class Game /*implements Serializable*/ {
//
//    @Id
//    private String reference;
//
//
//    private String name;
//
//
//    private String url;
//
//
//
//    public Game() {
//    }
//
//    //@Id
//    public String getReference() {
//        return this.reference;
//    }
//
//    public void setReference(String gReference) {
//        this.reference = gReference;
//    }
//
//    public String getName() {
//        return this.name;
//    }
//
//    public void setName(String gName) {
//        this.name = gName;
//    }
//
//    public String getUrl() {
//        return this.url;
//    }
//
//    public void setUrl(String gUrl ) {
//        this.url = gUrl;
//    }
//
//
//}