/*
 Walter Vieira (2022-04-22)
 Sistemas de Informa��o - projeto JPAAulas_ex1
 C�digo desenvolvido para iulustra��o dos conceitos sobre acesso a dados, concretizados com base na especifica��o JPA.
 Todos os exemplos foram desenvolvidos com EclipseLinlk (3.1.0-M1), usando o ambientre Eclipse IDE vers�o 2022-03 (4.23.0).
 
N�o existe a pretens�o de que o c�digo estaja completo.

Embora tenha sido colocado um esfor�o significativo na corre��o do c�digo, n�o h� garantias de que ele n�o contenha erros que possam 
acarretar problemas v�rios, em particular, no que respeita � consist�ncia dos dados.  
 
*/

package businessLogic;
import jakarta.persistence.*;


import model.conversation.Conversation;
import model.game.Game;
import model.game.GameStat;
import model.matches.*;

import model.players.PlayerPurchase;
import model.players.PlayerPurchasePK;
import model.players.PlayerStat;
import model.players.Player;
import model.regions.Regions;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class BLService 
{
    @SuppressWarnings("unchecked")
    public  void testT1() throws Exception // Alinea D
    {
        // Cria um player e consequentemente uma nova linha em player_stats com tudo a 0

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExemplo");
        EntityManager em = emf.createEntityManager();
        try
        {
            System.out.println("Criar player");
            em.getTransaction().begin();


            Query query = em.createQuery("SELECT MAX(p.id) FROM Player p");
            Integer maxId = (Integer) query.getSingleResult();
            Player p = new Player();
            p.setEmail("13@isel.pt");
            p.setUsername("t53e3");
            p.setState("Ativo");
            p.setId(maxId);
            Regions r = new Regions();
            r.setName("Europe");
            p.setRegion(r);
            PlayerStat ps =new PlayerStat();
            ps.setNumMatches(0);
            ps.setNumGamesPlayed(0);
            ps.setTotalPoints(0);
            ps.setPlayer(p);
            p.setPlayerStat(ps);
            em.persist(p);
            em.getTransaction().commit();

        }
        catch(Exception e)
        {
            System.out.println("[::ERROR::]" + e.getMessage());
            em.getTransaction().rollback();
            throw e;
        }
        finally
        {
            em.close();
            emf.close();
        }
    }

    @SuppressWarnings("unchecked")
    public  void testT2() throws Exception //
    {
        //ban player
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExemplo");
        EntityManager em = emf.createEntityManager();
        try
        {
            // Cria um GAME e consequentemente uma nova linha em game_stats com tudo a 0
            em.getTransaction().begin();

            Game g = new Game();
            g.setReference("G7");
            g.setName("TEST1");
            g.setUrl("www.tests.pt");
            GameStat gs =new GameStat();
            gs.setNumMatches(0);
            gs.setTotalPoints(0);
            gs.setNumPlayers(0);
            g.setGameStat(gs);
            em.persist(g);
            em.getTransaction().commit();

        }
        catch(Exception e)
        {
            System.out.println("[::ERROR::]" + e.getMessage());
            em.getTransaction().rollback();
            throw e;
        }
        finally
        {
            em.close();
            emf.close();
        }
    }

    @SuppressWarnings("unchecked")
    public  void testT3() throws Exception //
    {
        //ban player
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExemplo");
        EntityManager em = emf.createEntityManager();
        try
        {
            // criar conversa dado um jogador
            em.getTransaction().begin();

            Conversation c = new Conversation();
            c.setName("Conversation test");
            Player player = em.find(Player.class, 3);
            Set<Player> players = Set.of(player);
            c.setPlayers(players);
            em.persist(c);
            em.getTransaction().commit();

        }
        catch(Exception e)
        {
            System.out.println("[::ERROR::]" + e.getMessage());
            throw e;
        }
        finally
        {
            em.close();
            emf.close();
        }
    }

    @SuppressWarnings("unchecked")
    public  void testT4() throws Exception //
    {
        //ban player
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExemplo");
        EntityManager em = emf.createEntityManager();
        try
        {
            // Criar match
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT MAX(m.id.match_number) FROM Match m");
            Integer last_match_number = (Integer) query.getSingleResult();
            MatchPK matchPK = new MatchPK();
            matchPK.setMatchNumber(last_match_number+1);
            matchPK.setGameRef("G1");
            Match m = new Match();
            Game game = em.find(Game.class, "G1");
            m.setStartTime(Timestamp.valueOf( LocalDateTime.now()));
            m.setGame(game);
           // m.setId(matchPK);
            Regions region = new Regions();
            region.setName("Europe");
            m.setRegion(region);
            em.persist(m);
            em.getTransaction().commit();

        }
        catch(Exception e)
        {
            System.out.println("[::ERROR::]" + e.getMessage());
            throw e;
        }
        finally
        {
            em.close();
            emf.close();
        }
    }

    @SuppressWarnings("unchecked")
    public  void testT5() throws Exception //
    {
        //ban player
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExemplo");
        EntityManager em = emf.createEntityManager();
        try
        {
            // criar conversa dado um jogador
            em.getTransaction().begin();

            PlayerPurchase ps = new PlayerPurchase();
            ps.setPrice(BigDecimal.valueOf(100.00));
            ps.setPurchaseDate(Timestamp.valueOf( LocalDateTime.now()));
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

        }
        catch(Exception e)
        {
            System.out.println("[::ERROR::]" + e.getMessage());
            throw e;
        }
        finally
        {
            em.close();
            emf.close();
        }
    }

    @SuppressWarnings("unchecked")
    public  void testT6() throws Exception //
    {
        //ban player
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExemplo");
        EntityManager em = emf.createEntityManager();
        try
        {
            // criar conversa dado um jogador
            em.getTransaction().begin();

            Query query = em.createQuery("SELECT MAX(m.id.match_number) FROM Match m");

            Integer last_match_number = (Integer) query.getSingleResult();
            if (last_match_number == null ) last_match_number= 0 ;
            MatchPK matchPK = new MatchPK();
            matchPK.setMatchNumber(last_match_number+1);
            matchPK.setGameRef("G1");
            Match match = new Match();
            Regions region = new Regions();
            region.setName("Europe");
            match.setRegion(region);

            match.setId(matchPK);
            match.setStartTime(Timestamp.valueOf( LocalDateTime.now()));
            Game game = em.find(Game.class, "G1");
            match.setGame(game);
            SinglePlayerMatch singlePlayerMatch = new SinglePlayerMatch();
            singlePlayerMatch.setMatch(match);
            singlePlayerMatch.setDifficulty(4);
            singlePlayerMatch.setPoints(4000);
            singlePlayerMatch.setId(matchPK);

            Player player = em.find(Player.class, 1);
            singlePlayerMatch.setPlayer(player);

            match.setSinglePlayerMatch(singlePlayerMatch);
            singlePlayerMatch.setMatch(match);

            System.out.println();
            em.persist(match);
            em.persist(singlePlayerMatch);
            em.getTransaction().commit();

        }
        catch(Exception e)
        {
            System.out.println("[::ERROR::]" + e.getMessage());
            throw e;
        }
        finally
        {
            em.close();
            emf.close();
        }
    }

    public  void testT7() throws Exception //
    {
        //ban player
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExemplo");
        EntityManager em = emf.createEntityManager();
        try
        {
            em.getTransaction().begin();

            Query query = em.createQuery("SELECT MAX(m.id.match_number) FROM Match m");

            Integer last_match_number = (Integer) query.getSingleResult();
            if (last_match_number == null ) last_match_number= 0 ;
            MatchPK matchPK = new MatchPK();
            matchPK.setMatchNumber(last_match_number+1);
            Game game = em.find(Game.class, "G3");
            matchPK.setGameRef(game.getReference());
            Match match = new Match();
            Regions region = new Regions();
            region.setName("Europe");
            match.setRegion(region);

            match.setId(matchPK);
            match.setStartTime(Timestamp.valueOf( LocalDateTime.now()));

            match.setGame(game);
            MultiPlayerMatch multiPlayerMatch = new MultiPlayerMatch();
            multiPlayerMatch.setMatch(match);
            multiPlayerMatch.setState("Terminada");
            multiPlayerMatch.setId(matchPK);

            PlaysMulti playsMulti1 = new PlaysMulti();
            PlaysMultiPK playsMulti1PK =new PlaysMultiPK();
            Player player1 = em.find(Player.class, 1);
            playsMulti1.setId(playsMulti1PK);
            playsMulti1.setPlayer(player1);
            playsMulti1.setPoints(5000);
            playsMulti1.setMultiPlayerMatch(multiPlayerMatch);

            PlaysMulti playsMulti2 = new PlaysMulti();
            PlaysMultiPK playsMulti2PK =new PlaysMultiPK();
            Player player2 = em.find(Player.class, 2);
            playsMulti2.setId(playsMulti2PK);
            playsMulti2.setPlayer(player2);
            playsMulti2.setPoints(5000);
            playsMulti2.setMultiPlayerMatch(multiPlayerMatch);

            multiPlayerMatch.setPlaysMultis(List.of(playsMulti1,playsMulti2));
            match.setMultiPlayerMatch(multiPlayerMatch);
            multiPlayerMatch.setMatch(match);

            System.out.println();
            em.persist(match);
            em.persist(multiPlayerMatch);
            em.getTransaction().commit();

        }
        catch(Exception e)
        {
            System.out.println("[::ERROR::]" + e.getMessage());
            throw e;
        }
        finally
        {
            em.close();
            emf.close();
        }
    }
    @SuppressWarnings("unchecked")
    public  void testCriarJogador(String email, String username, String region ) throws Exception // Alinea D
    {
        //create player
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExemplo");
        EntityManager em = emf.createEntityManager();
        try
        {
            System.out.println("Criar com o procedimento create_player_logic");
            em.getTransaction().begin();
            //suposto chamar create_player mas da erro.
            Query query = em.createNativeQuery("CALL create_player_logic(?, ?, ?)");
            query.setParameter(1, email);
            query.setParameter(2, username);
            query.setParameter(3, region);

            query.executeUpdate();

            em.getTransaction().commit();

        }
        catch(Exception e)
        {
            System.out.println("[::ERROR::]" + e.getMessage());
            throw e;
        }
        finally
        {
            em.close();
            emf.close();
        }
    }

    @SuppressWarnings("unchecked")
    public  void testBanirJogador(int playerID) throws Exception // Alinea D
    {
        //ban player
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExemplo");
        EntityManager em = emf.createEntityManager();
        try
        {
            System.out.println("Criar com o procedimento ban_player_logic");
            em.getTransaction().begin();
            //suposto chamar create_player mas da erro.
            Query query = em.createNativeQuery("CALL ban_player_logic(?)");
            query.setParameter(1, playerID);
            query.executeUpdate();

            em.getTransaction().commit();

        }
        catch(Exception e)
        {
            System.out.println("[::ERROR::]" + e.getMessage());
            throw e;
        }
        finally
        {
            em.close();
            emf.close();
        }
    }

    @SuppressWarnings("unchecked")
    public  void testDesativarJogador(int playerID) throws Exception // Alinea D
    {
        //ban player
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExemplo");
        EntityManager em = emf.createEntityManager();
        try
        {
            System.out.println("Criar com o procedimento deactivate_player_logic");
            em.getTransaction().begin();
            //suposto chamar create_player mas da erro.
            Query query = em.createNativeQuery("CALL deactivate_player_logic(?)");
            query.setParameter(1, playerID);
            query.executeUpdate();

            em.getTransaction().commit();

        }
        catch(Exception e)
        {
            System.out.println("[::ERROR::]" + e.getMessage());
            throw e;
        }
        finally
        {
            em.close();
            emf.close();
        }
    }

    @SuppressWarnings("unchecked")
    public  void testTotalPontosJogador(int playerID) throws Exception // Alinea D
    {
        //ban player
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExemplo");
        EntityManager em = emf.createEntityManager();
        try
        {
            System.out.println("total_pontos_jogador");
            em.getTransaction().begin();

            StoredProcedureQuery query = em.createStoredProcedureQuery("total_pontos_jogador");
            query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT);
            query.setParameter(1, playerID);
            query.execute();

            Integer returnValue = (Integer) query.getOutputParameterValue(2); // Retrieve the return value
            System.out.println("Return value: " + returnValue);

            em.getTransaction().commit();
        }
        catch(Exception e)
        {
            System.out.println("[::ERROR::]" + e.getMessage());
            throw e;
        }
        finally
        {
            em.close();
            emf.close();
        }
    }

    @SuppressWarnings("unchecked")
    public  void testJogosJogador(int playerID) throws Exception // Alinea D
    {
        //ban player
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExemplo");
        EntityManager em = emf.createEntityManager();
        try
        {
            System.out.println("total_jogos_jogador");
            em.getTransaction().begin();

            StoredProcedureQuery query = em.createStoredProcedureQuery("total_jogos_jogador");
            query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT);
            query.setParameter(1, playerID);
            query.execute();

            Integer returnValue = (Integer) query.getOutputParameterValue(2); // Retrieve the return value
            System.out.println("Return value: " + returnValue);

            em.getTransaction().commit();
        }
        catch(Exception e)
        {
            System.out.println("[::ERROR::]" + e.getMessage());
            throw e;
        }
        finally
        {
            em.close();
            emf.close();
        }
    }

    public  void testPontosJogoPorJogador(String gameRef) throws Exception // Alinea D
    {
        //ban player
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExemplo");
        EntityManager em = emf.createEntityManager();
        try
        {
            System.out.println("pontos_jogo_por_jogador");
            em.getTransaction().begin();

            StoredProcedureQuery query = em.createStoredProcedureQuery("pontos_jogo_por_jogador");
            query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            query.setParameter(1, gameRef);
            query.execute();

            List<Object[]> resultList = query.getResultList();
            for (Object[] result : resultList) {
                Integer playerId = (Integer) result[0];
                Long totalPoints = (Long) result[1];
                System.out.println("Player ID: " + playerId + ", Total Points: " + totalPoints);
            }

            em.getTransaction().commit();
        }
        catch(Exception e)
        {
            System.out.println("[::ERROR::]" + e.getMessage());
            throw e;
        }
        finally
        {
            em.close();
            emf.close();
        }
    }

    public  void testAssociarCracha(int playerID, String gameRef, String badgeName) throws Exception // Alinea D
    {
        //ban player
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExemplo");
        EntityManager em = emf.createEntityManager();
        try
        {
            System.out.println("associar_cracha_logic");
            em.getTransaction().begin();

            Query query = em.createNativeQuery("CALL associar_cracha_logic(?,?,?)");
            query.setParameter(1, playerID);
            query.setParameter(2, gameRef);
            query.setParameter(3, badgeName);
            query.executeUpdate();

            em.getTransaction().commit();
        }
        catch(Exception e)
        {
            System.out.println("[::ERROR::]" + e.getMessage());
            throw e;
        }
        finally
        {
            em.close();
            emf.close();
        }
    }


    @SuppressWarnings("unchecked")
    public  void testIniciarConversa(int playerID, String conversationName) throws Exception // Alinea D
    {
        //ban player
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExemplo");
        EntityManager em = emf.createEntityManager();
        try
        {
            System.out.println("Criar com o procedimento iniciar_conversa_logica");
            em.getTransaction().begin();

            StoredProcedureQuery query = em.createStoredProcedureQuery("function_iniciar_conversa_logica");
            query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter( 2, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(3, Integer.class, ParameterMode.OUT);
            query.setParameter(1, playerID);
            query.setParameter(2, conversationName);
            query.execute();

            Integer returnValue = (Integer) query.getOutputParameterValue(3); // Retrieve the return value
            System.out.println("Return value: " + returnValue);

            em.getTransaction().commit();
        }
        catch(Exception e)
        {
            System.out.println("[::ERROR::]" + e.getMessage());
            throw e;
        }
        finally
        {
            em.close();
            emf.close();
        }
    }

    public  void testJuntarConversa(int playerID, int conversationID) throws Exception // Alinea D
    {
        //ban player
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExemplo");
        EntityManager em = emf.createEntityManager();
        try
        {
            System.out.println("Criar com o procedimento juntar_conversa");
            em.getTransaction().begin();
            //suposto chamar create_player mas da erro.
            Query query = em.createNativeQuery("CALL juntar_conversa_logica(?,?)");
            query.setParameter(1, playerID);
            query.setParameter(2, conversationID);
            query.executeUpdate();

            em.getTransaction().commit();

        }
        catch(Exception e)
        {
            System.out.println("[::ERROR::]" + e.getMessage());
            throw e;
        }
        finally
        {
            em.close();
            emf.close();
        }
    }

    public  void testEnviarMensagem(int playerID, int conversationID,  String message) throws Exception // Alinea D
    {
        //ban player
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExemplo");
        EntityManager em = emf.createEntityManager();
        try
        {
            System.out.println("Criar com o procedimento enviar_mensagem_logica");
            em.getTransaction().begin();
            //suposto chamar create_player mas da erro.
            Query query = em.createNativeQuery("CALL enviar_mensagem_logica(?,?,?)");
            query.setParameter(1, playerID);
            query.setParameter(2, conversationID);
            query.setParameter(3,message);
            query.executeUpdate();

            em.getTransaction().commit();

        }
        catch(Exception e)
        {
            System.out.println("[::ERROR::]" + e.getMessage());
            throw e;
        }
        finally
        {
            em.close();
            emf.close();
        }
    }
}
