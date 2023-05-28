/*
 Walter Vieira (2022-04-22)
 Sistemas de Informação - projeto JPAAulas_ex1
 Código desenvolvido para iulustração dos conceitos sobre acesso a dados, concretizados com base na especificação JPA.
 Todos os exemplos foram desenvolvidos com EclipseLinlk (3.1.0-M1), usando o ambientre Eclipse IDE versão 2022-03 (4.23.0).
 
Não existe a pretensão de que o código estaja completo.

Embora tenha sido colocado um esforço significativo na correção do código, não há garantias de que ele não contenha erros que possam 
acarretar problemas vários, em particular, no que respeita à consistência dos dados.  
 
*/

package businessLogic;
import jakarta.persistence.*;


import model.conversation.Conversation;
import model.game.Game;
import model.game.GameStat;
import model.matches.Match;
import model.matches.MatchPK;
import model.players.PlayerPurchase;
import model.players.PlayerPurchasePK;
import model.players.PlayerStat;
import model.players.Player;
import model.regions.Regions;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
            p.setEmail("13233@isel.pt");
            p.setUsername("t5423e3");
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
            m.setId(matchPK);
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
    public  void testCriarJogador() throws Exception // Alinea D
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
            query.setParameter(1, "bernardoss@isel.pt");
            query.setParameter(2, "12234");
            query.setParameter(3, "Europe");

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
    public  void testBanirJogador() throws Exception // Alinea D
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
            query.setParameter(1, 1);
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
    public  void testDesativarJogador() throws Exception // Alinea D
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
            query.setParameter(1, 2);
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
    public  void testIniciarConversa() throws Exception // Alinea D
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
            query.setParameter(1, 3);
            query.setParameter(2, "new function");
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

    public  void testJuntarConversa() throws Exception // Alinea D
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
            query.setParameter(1, 1);
            query.setParameter(2, 4);
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

    public  void testEnviarMensagem() throws Exception // Alinea D
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
            query.setParameter(1, 3);
            query.setParameter(2, 1);
            query.setParameter(3,"Autodominio é muito importante");
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
