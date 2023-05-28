package model.regions;


import jakarta.persistence.*;
import model.matches.Match;
import model.players.Player;

import java.util.List;

@Entity
@Table(name="regions")
@NamedQuery(name="Regions.findAll", query="SELECT r FROM Regions r")
public class Regions {
    @Id
    private String name;

    @OneToMany(mappedBy = "region", cascade=CascadeType.PERSIST, orphanRemoval=true)
    private List<Player> players;

    @OneToMany(mappedBy = "region", cascade=CascadeType.PERSIST, orphanRemoval=true)
    private List<Match> matches;

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


    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public void addMatch(Match match) {
        this.matches.add(match);
    }

    public void removeMatch(Match match)  {
        this.matches.remove(match);
    }
}
