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
import dal.DataScope.DataAccessScope;
import dal.repositorys.*;
import jakarta.persistence.*;


import model.badges.Badge;
import model.badges.BadgePK;
import model.players.Player;
import java.util.List;


/**
 * Bl Service
 */
public class BLService {

    @SuppressWarnings("unchecked")
    public void criarJogador(String email, String username, String region) throws Exception // Alinea D
    {
        //create player
        try (DataAccessScope ds = new DataAccessScope()) {
            EntityManager em = ds.getEntityManager();

            Query query = em.createNativeQuery("CALL create_player_logic(?, ?, ?)");
            query.setParameter(1, email);
            query.setParameter(2, username);
            query.setParameter(3, region);
            query.executeUpdate();

            ds.validateWork();
        }
    }

    @SuppressWarnings("unchecked")
    public void banirJogador(int playerID) throws Exception // Alinea D
    {
        //ban player
        try (DataAccessScope ds = new DataAccessScope()) {
            EntityManager em = ds.getEntityManager();
            Query query = em.createNativeQuery("CALL ban_player_logic(?)");
            query.setParameter(1, playerID);
            query.executeUpdate();

            em.getTransaction().commit();
            ds.validateWork();

        }
    }

    @SuppressWarnings("unchecked")
    public void desativarJogador(int playerID) throws Exception // Alinea D
    {
        //ban player
        try (DataAccessScope ds = new DataAccessScope()) {
            EntityManager em = ds.getEntityManager();

            System.out.println("Criar com o procedimento deactivate_player_logic");

            //suposto chamar create_player mas da erro.
            Query query = em.createNativeQuery("CALL deactivate_player_logic(?)");
            query.setParameter(1, playerID);
            query.executeUpdate();

            ds.validateWork();

        }
    }

    @SuppressWarnings("unchecked")
    public void totalPontosJogador(int playerID) throws Exception // Alinea D
    {
        //ban player
        try (DataAccessScope ds = new DataAccessScope()) {
            EntityManager em = ds.getEntityManager();

            System.out.println("total_pontos_jogador");

            StoredProcedureQuery query = em.createStoredProcedureQuery("total_pontos_jogador");
            query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT);
            query.setParameter(1, playerID);
            query.execute();

            Integer returnValue = (Integer) query.getOutputParameterValue(2); // Retrieve the return value
            System.out.println("Return value: " + returnValue);

            ds.validateWork();
        }
    }

    @SuppressWarnings("unchecked")
    public void jogosJogador(int playerID) throws Exception // Alinea D
    {
        //ban player
        try (DataAccessScope ds = new DataAccessScope()) {
            EntityManager em = ds.getEntityManager();

            System.out.println("total_jogos_jogador");

            StoredProcedureQuery query = em.createStoredProcedureQuery("total_jogos_jogador");
            query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT);
            query.setParameter(1, playerID);
            query.execute();

            Integer returnValue = (Integer) query.getOutputParameterValue(2); // Retrieve the return value
            System.out.println("Return value: " + returnValue);

            ds.validateWork();
        }
    }

    public void pontosJogoPorJogador(String gameRef) throws Exception // Alinea D
    {
        //ban player
        try (DataAccessScope ds = new DataAccessScope()) {
            EntityManager em = ds.getEntityManager();

            System.out.println("pontos_jogo_por_jogador");

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

            ds.validateWork();
        }
    }

    public void associarCracha(int playerID, String gameRef, String badgeName) throws Exception // Alinea D
    {
        //ban player
        try (DataAccessScope ds = new DataAccessScope()) {
            EntityManager em = ds.getEntityManager();

            System.out.println("associar_cracha_logic");

            Query query = em.createNativeQuery("CALL associar_cracha_logic(?,?,?)");
            query.setParameter(1, playerID);
            query.setParameter(2, gameRef);
            query.setParameter(3, badgeName);
            query.executeUpdate();

            ds.validateWork();
        }
    }


    @SuppressWarnings("unchecked")
    public void iniciarConversa(int playerID, String conversationName) throws Exception // Alinea D
    {
        //ban player
        try (DataAccessScope ds = new DataAccessScope()) {
            EntityManager em = ds.getEntityManager();

            System.out.println("Criar com o procedimento iniciar_conversa_logica");

            StoredProcedureQuery query = em.createStoredProcedureQuery("function_iniciar_conversa_logica");
            query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(3, Integer.class, ParameterMode.OUT);
            query.setParameter(1, playerID);
            query.setParameter(2, conversationName);
            query.execute();

            Integer returnValue = (Integer) query.getOutputParameterValue(3); // Retrieve the return value
            System.out.println("Return value: " + returnValue);

            ds.validateWork();
        }
    }

    public void juntarConversa(int playerID, int conversationID) throws Exception // Alinea D
    {
        //ban player
        try (DataAccessScope ds = new DataAccessScope()) {
            EntityManager em = ds.getEntityManager();

            System.out.println("Criar com o procedimento juntar_conversa");

            Query query = em.createNativeQuery("CALL juntar_conversa_logica(?,?)");
            query.setParameter(1, playerID);
            query.setParameter(2, conversationID);
            query.executeUpdate();

            ds.validateWork();

        }
    }

    public void enviarMensagem(int playerID, int conversationID, String message) throws Exception // Alinea D
    {
        try (DataAccessScope ds = new DataAccessScope()) {
            EntityManager em = ds.getEntityManager();

            System.out.println("Criar com o procedimento enviar_mensagem_logica");
            //suposto chamar create_player mas da erro.
            Query query = em.createNativeQuery("CALL enviar_mensagem_logica(?,?,?)");
            query.setParameter(1, playerID);
            query.setParameter(2, conversationID);
            query.setParameter(3, message);
            query.executeUpdate();

            ds.validateWork();

        }
    }

    public void jogadorTotalInfo() throws Exception {

        //ban player
        try (DataAccessScope ds = new DataAccessScope()) {
            EntityManager em = ds.getEntityManager();

            System.out.println("Jogador total Info View");

            Query query = em.createNativeQuery("select  * from jogador_total_info");

            List<Object[]> resultList = query.getResultList();

            for (Object[] result : resultList) {
                for (Object param : result) {
                    System.out.print(param + "    |");
                }
                System.out.println();
            }

            ds.validateWork();
        }
    }

    public void associarCrachaJPA(int playerId, String gameRef, String badgeName) throws Exception {
        try (DataAccessScope ds = new DataAccessScope()) {
            EntityManager em = ds.getEntityManager();

            RepositoryBadge repositoryBadge = new RepositoryBadge();
            RepositoryPlayer repositoryPlayer = new RepositoryPlayer();
            RepositoryPlaysMulti repositoryPlaysMulti = new RepositoryPlaysMulti();
            RepositorySinglePlayerMatch repositorySinglePlayerMatch = new RepositorySinglePlayerMatch();

            BadgePK badgePK = new BadgePK();
            badgePK.setBadgeName(badgeName);
            badgePK.setGameRef(gameRef);

            Player player = repositoryPlayer.find(playerId);
            Badge badge = repositoryBadge.find(badgePK);

            List<Integer> singlePlayerPoints = repositorySinglePlayerMatch.getAll()
                    .stream().filter(it -> it.getPlayer() == player && it.getId().getMatchPK().getGameRef().equals(gameRef))
                    .map(it -> it.getPoints()).toList();
            int totalPoints = 0;
            for (int point : singlePlayerPoints) {
                totalPoints += point;
            }

            List<Integer> multiplayerPlayerPoints = repositoryPlaysMulti.getAll()
                    .stream().filter(it -> it.getPlayer() == player && it.getId().getMatchPK().getGameRef().equals(gameRef))
                    .map(it -> it.getPoints()).toList();
            for (int point : multiplayerPlayerPoints) {
                totalPoints += point;
            }

            int badgeLimitPoints = badge.getPointsLimit();

            if (badge.getPlayers().contains(player))
                throw new Exception("O jogador " + playerId + " ja tem o crachá " + badgeName);
            if (totalPoints >= badgeLimitPoints) {
                badge.addPlayer(player);
                player.addBadge(badge);
                System.out.println("Badge associado com sucesso");
            } else {
                System.out.println("O jogador " + player.getUsername() + " Não tem pontos suficientes");
            }
            ds.validateWork();
        }
    }

    public void optimisticLocking(String gameRef, String badgeName, int attempts) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExemplo");
        EntityManager em = emf.createEntityManager();
        int nreps = attempts;
        try {
            do {
                try {
                    --nreps;
                    em.getTransaction().begin();
                    BadgePK badgePK = new BadgePK();
                    badgePK.setBadgeName(badgeName);
                    badgePK.setGameRef(gameRef);

                    Badge badge = em.find(Badge.class, badgePK, LockModeType.OPTIMISTIC);
                    if (badge == null) throw new Exception("Badge inexistente");

                    badge.setPointsLimit((int) (badge.getPointsLimit()*1.2));
                    em.getTransaction().commit();
                } catch (RollbackException | OptimisticLockException e) {
                    if (e.getCause() instanceof OptimisticLockException ||
                            e instanceof OptimisticLockException) {
                        if (em.getTransaction().isActive())
                            em.getTransaction().rollback();
                        if (nreps == 0)
                            throw new Exception("erro de concorrência");
                    } else throw e;
                }
            } while(nreps > 0);
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
            throw e;
        } finally {
            em.close();
            emf.close();
        }
    }

    public void pessimistLocking(String gameRef, String badgeName) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExemplo");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            BadgePK badgePK = new BadgePK();
            badgePK.setBadgeName(badgeName);
            badgePK.setGameRef(gameRef);

            Badge badge = em.find(Badge.class, badgePK, LockModeType.PESSIMISTIC_READ);
            if (badge == null) throw new Exception("Badge inexistente");
            badge.setPointsLimit((int) (badge.getPointsLimit()*1.2));
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            em.close();
            emf.close();
        }
    }
}
