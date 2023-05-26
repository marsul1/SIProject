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
import model.players.PlayerStat;
import model.players.Player;
import model.regions.Regions;

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
            p.setEmail("1225@isel.pt");
            p.setUsername("te2st5");
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
            p.setPlayerStats(ps);
            em.persist(p);
            em.getTransaction().commit();

        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
            throw e;
        }
        finally
        {
            em.close();
            emf.close();
        }
    }

    @SuppressWarnings("unchecked")
    public  void testD1() throws Exception // Alinea D
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
            System.out.println(e.getMessage());
            throw e;
        }
        finally
        {
            em.close();
            emf.close();
        }
    }

    @SuppressWarnings("unchecked")
    public  void testD2() throws Exception // Alinea D
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
            System.out.println(e.getMessage());
            throw e;
        }
        finally
        {
            em.close();
            emf.close();
        }
    }

    @SuppressWarnings("unchecked")
    public  void testD3() throws Exception // Alinea D
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
            System.out.println(e.getMessage());
            throw e;
        }
        finally
        {
            em.close();
            emf.close();
        }
    }


    @SuppressWarnings("unchecked")
    public  void testI1() throws Exception // Alinea D
    {
        //ban player
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExemplo");
        EntityManager em = emf.createEntityManager();
        try
        {
            System.out.println("Criar com o procedimento iniciar_conversa_logica");
            em.getTransaction().begin();

            StoredProcedureQuery query = em.createStoredProcedureQuery("function_iniciar_conversa_logica");
            query.registerStoredProcedureParameter(1,
                    Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter( 2, String.class,
                    ParameterMode.IN);
            query.setParameter(1, 3);
            query.setParameter(2, "new function");
            query.execute();

            em.getTransaction().commit();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            throw e;
        }
        finally
        {
            em.close();
            emf.close();
        }
    }
}
