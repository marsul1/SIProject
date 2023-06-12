package dal.mappers;

import dal.DataScope.DataAccessScope;
import genericInterfaces.IMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import model.players.Player;

public class MapperPlayer implements IMapper<Player, Integer> {

    public Integer create(Player player) throws Exception {
        try (DataAccessScope ds = new DataAccessScope()) {
            EntityManager em = ds.getEntityManager();
            em.persist(player);
            ds.validateWork();
            return player.getId();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Player read(Integer id) throws Exception {
        try (DataAccessScope ds = new DataAccessScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();  // ? necess?rio para o pr?ximo find encontrar o registo caso ele tenha sido criado neste transa??o
            Player player = em.find(Player.class, id, LockModeType.PESSIMISTIC_READ);
            ds.validateWork();
            return player;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void update(Player player) throws Exception {
        try (DataAccessScope ds = new DataAccessScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();  // ? necess?rio para o pr?ximo find encontrar o registo caso ele tenha sido criado neste transa??o
            Player uptatePlayer = em.find(Player.class, player.getId(), LockModeType.PESSIMISTIC_WRITE);
            if (uptatePlayer == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            uptatePlayer.setEmail(player.getEmail());
            uptatePlayer.setUsername(player.getUsername());
            uptatePlayer.setRegion(player.getRegion());
            uptatePlayer.setState(player.getState());
            uptatePlayer.setFriends(player.getFriends());
            uptatePlayer.setPlayerStat(player.getPlayerStat());
            uptatePlayer.setPlayerPurchases(player.getPlayerPurchases());
            uptatePlayer.setConversations(player.getConversations());
            uptatePlayer.setMessages(player.getMessages());
            uptatePlayer.setBadges(player.getBadges());
            uptatePlayer.setPlaysMultis(player.getPlaysMultis());
            uptatePlayer.setSinglePlayerMatches(player.getSinglePlayerMatches());
            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }

    }

    public void delete(Player player) throws Exception {
        try (DataAccessScope ds = new DataAccessScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush(); // ? necess?rio para o pr?ximo find encontrar o registo caso ele tenha sido criado neste transa??o
            Player deletePlayer = em.find(Player.class, player.getId(), LockModeType.PESSIMISTIC_WRITE);
            if (deletePlayer == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            em.remove(deletePlayer);
            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}