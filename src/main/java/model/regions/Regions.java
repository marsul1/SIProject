package model.regions;


import jakarta.persistence.*;
import model.players.Player;

import java.util.List;

@Entity
@Table(name="regions")
@NamedQuery(name="Regions.findAll", query="SELECT r FROM Regions r")
public class Regions {
    @Id
    private String name;

    @OneToMany(mappedBy = "region_name", cascade=CascadeType.PERSIST, orphanRemoval=true)
    private List<Player> players;

    public Regions (){

    }

    public String getName() {
        return this.name;
    }

    public void setName(String rName) {
        this.name = rName;
    }
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void removePlayer(Player player)  {
       this.players.remove(player);
    }


}
