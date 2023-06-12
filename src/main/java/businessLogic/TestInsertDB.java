package businessLogic;

import jakarta.persistence.*;
import model.conversation.Conversation;
import model.game.Game;
import model.game.GameStat;
import model.matches.*;
import model.players.Player;
import model.players.PlayerPurchase;
import model.players.PlayerPurchasePK;
import model.players.PlayerStat;
import model.regions.Regions;
import utils.Utils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class TestInsertDB {
    /**
     * Test for a new player insert and creates the player stats all reseted.
     */
    public void testT1() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExemplo");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            int maxId = ((Integer) em.createQuery("SELECT MAX(p.id) FROM Player p").getSingleResult()) + 1;

            Player p = new Player();
            p.setEmail("TestEmail" + maxId + "@isel.pt");
            p.setUsername("TestName" + maxId);
            p.setState("Ativo");

            Regions r = new Regions();
            r.setName("Europe");
            p.setRegion(r);

            PlayerStat ps = new PlayerStat();
            ps.setNumMatches(0);
            ps.setNumGamesPlayed(0);
            ps.setTotalPoints(0);
            ps.setPlayer(p);
            p.setPlayerStat(ps);

            em.persist(p);
            em.getTransaction().commit();

            Utils.commitSuccess(p, "Player Created");
        } catch (Exception e) {
            System.out.println("[::ERROR::]" + e.getMessage());
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
            emf.close();
        }
    }

    /**
     * Test for a new game insert and creates the game stats all reseted.
     */
    public void testT2() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExemplo");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            int numberGames = ((Long) em.createQuery("SELECT COUNT(g.reference) FROM Game g").getSingleResult()).intValue() + 1;

            Game g = new Game();
            g.setReference("TestRef" + numberGames);
            g.setName("TestName" + numberGames);
            g.setUrl("www.tests" + numberGames + ".pt");

            GameStat gs = new GameStat();
            gs.setNumMatches(0);
            gs.setTotalPoints(0);
            gs.setNumPlayers(0);
            gs.setGame(g);
            g.setGameStat(gs);

            em.persist(g);
            em.getTransaction().commit();

            Utils.commitSuccess(g, "Game Created");
            Utils.commitSuccess(gs, "Game Status Created");
        } catch (Exception e) {
            System.out.println("[::ERROR::]" + e.getMessage());
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
            emf.close();
        }
    }

    /**
     * Test creating a conversation with a player.
     */
    public void testT3() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExemplo");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            Conversation c = new Conversation();
            c.setName("Conversation Test");

            Player player = em.find(Player.class, 1);
            Set<Player> players = Set.of(player);
            c.setPlayers(players);

            em.persist(c);
            em.getTransaction().commit();

            Utils.commitSuccess(c, "Conversation Created");
        } catch (Exception e) {
            System.out.println("[::ERROR::]" + e.getMessage());
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
            emf.close();
        }
    }

    /**
     * Test for match creation.
     */
//    public void testT4() throws Exception {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExemplo");
//        EntityManager em = emf.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            var gameReference = "G3";
//            StoredProcedureQuery query = em.createStoredProcedureQuery("next_match_number");
//            query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
//            ;
//            query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT);
//            ;
//            query.setParameter(1, gameReference);
//            query.execute();
//            Integer returnValue = (Integer) query.getOutputParameterValue(2);
//
//            MatchPK matchPK = new MatchPK();
//            matchPK.setMatchNumber(returnValue);
//            matchPK.setGameRef(gameReference);
//
//            Match m = new Match();
//            Game game = em.find(Game.class, gameReference);
//            m.setStartTime(Timestamp.valueOf(LocalDateTime.now()));
//            m.setGame(game);
//
//            m.setId(matchPK);//retirar
//
//            Regions region = new Regions();
//            region.setName("Europe");
//            m.setRegion(region);
//            em.persist(m);
//
//            em.getTransaction().commit();
//
//            Utils.commitSuccess(m, "Match Created");
//        } catch (Exception e) {
//            System.out.println("[::ERROR::]" + e.getMessage());
//            em.getTransaction().rollback();
//            throw e;
//        } finally {
//            em.close();
//            emf.close();
//        }
//    }

    @SuppressWarnings("unchecked")
    public void testT4() throws Exception //
    {
        //ban player
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExemplo");
        EntityManager em = emf.createEntityManager();
        try {

            em.getTransaction().begin();

            PlayerPurchase ps = new PlayerPurchase();
            ps.setPrice(BigDecimal.valueOf(100.00));
            ps.setPurchaseDate(Timestamp.valueOf(LocalDateTime.now()));
            PlayerPurchasePK psPK = new PlayerPurchasePK();
            psPK.setPlayerId(7);
            psPK.setGameRef("G1");
            //  ps.setId(psPK);
            Player player = em.find(Player.class, 7);
            Game game = em.find(Game.class, "G1");
            ps.setGame(game);
            ps.setPlayer(player);

            em.persist(ps);
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("[::ERROR::]" + e.getMessage());
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
            emf.close();
        }
    }

    @SuppressWarnings("unchecked")
    public void testT5() throws Exception //
    {
        //ban player
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExemplo");
        EntityManager em = emf.createEntityManager();
        try {
            // criar conversa dado um jogador
            em.getTransaction().begin();

            var gameReference = "G3";
            var playerID = 1;
            StoredProcedureQuery query = em.createStoredProcedureQuery("next_match_number");
            query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT);
            query.setParameter(1, gameReference);
            query.execute();
            Integer returnValue = (Integer) query.getOutputParameterValue(2);

            Player player = em.find(Player.class, playerID);

            MatchPK matchPK = new MatchPK();

            matchPK.setMatchNumber(returnValue);
            matchPK.setGameRef(gameReference);

            Match match = new Match();
            Regions region = new Regions();
            region.setName("Europe");
            match.setRegion(region);
            match.setId(matchPK);
            match.setStartTime(Timestamp.valueOf(LocalDateTime.now()));
            Game game = em.find(Game.class, gameReference);
            match.setGame(game);
            SinglePlayerMatch singlePlayerMatch = new SinglePlayerMatch();
            singlePlayerMatch.setMatch(match);
            singlePlayerMatch.setDifficulty(4);
            singlePlayerMatch.setPoints(4000);
            MultiAndSinglePK multiAndSinglePK = new MultiAndSinglePK();
            multiAndSinglePK.setMatchPK(matchPK);
            multiAndSinglePK.setPlayerId(playerID);
            singlePlayerMatch.setId(multiAndSinglePK);


            singlePlayerMatch.setPlayer(player);

            match.setSinglePlayerMatch(singlePlayerMatch);
            singlePlayerMatch.setMatch(match);

            System.out.println();
            em.persist(match);
            em.persist(singlePlayerMatch);
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("[::ERROR::]" + e.getMessage());
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
            emf.close();
        }
    }

    public void testT6() throws Exception //
    {
        //ban player
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExemplo");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            var gameReference = "G4";
            StoredProcedureQuery query = em.createStoredProcedureQuery("next_match_number");
            query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT);
            query.setParameter(1, gameReference);
            query.execute();
            Integer returnValue = (Integer) query.getOutputParameterValue(2);

            MatchPK matchPK = new MatchPK();
            matchPK.setMatchNumber(returnValue);
            Game game = em.find(Game.class, gameReference);
            matchPK.setGameRef(game.getReference());
            Match match = new Match();
            Regions region = new Regions();
            region.setName("Europe");
            match.setRegion(region);

            match.setId(matchPK);
            match.setStartTime(Timestamp.valueOf(LocalDateTime.now()));

            match.setGame(game);

            MultiPlayerMatch multiPlayerMatch = new MultiPlayerMatch();
            multiPlayerMatch.setMatch(match);
            multiPlayerMatch.setState("Terminada");
            multiPlayerMatch.setId(matchPK);

            PlaysMulti playsMulti1 = new PlaysMulti();
            MultiAndSinglePK playsMulti1PK = new MultiAndSinglePK();
            Player player1 = em.find(Player.class, 3);
            playsMulti1.setId(playsMulti1PK);
            playsMulti1.setPlayer(player1);
            playsMulti1.setPoints(5000);
            playsMulti1.setMultiPlayerMatch(multiPlayerMatch);

            PlaysMulti playsMulti2 = new PlaysMulti();
            MultiAndSinglePK playsMulti2PK = new MultiAndSinglePK();
            Player player2 = em.find(Player.class, 5);
            playsMulti2.setId(playsMulti2PK);
            playsMulti2.setPlayer(player2);
            playsMulti2.setPoints(5000);
            playsMulti2.setMultiPlayerMatch(multiPlayerMatch);

            multiPlayerMatch.setPlaysMultis(List.of(playsMulti1, playsMulti2));
            match.setMultiPlayerMatch(multiPlayerMatch);
            multiPlayerMatch.setMatch(match);

            System.out.println();
            em.persist(match);
            em.persist(multiPlayerMatch);
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("[::ERROR::]" + e.getMessage());
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
            emf.close();
        }
    }

}
