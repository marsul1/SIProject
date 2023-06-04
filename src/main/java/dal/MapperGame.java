package dal;

import genericInterfaces.IMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import model.badges.Badge;
import model.badges.BadgePK;
import model.game.Game;
import model.players.Player;

public class MapperGame implements IMapper<Game, String> {
    public String create(Game game ) throws Exception {
        try (DataAccessScope ds = new DataAccessScope()) {
            EntityManager em = ds.getEntityManager();
            em.persist(game);
            ds.validateWork();
            return game.getReference();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Game read (String ref) throws Exception {
        try (DataAccessScope ds = new DataAccessScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();  // ? necess?rio para o pr?ximo find encontrar o registo caso ele tenha sido criado neste transa??o
            Game game = em.find(Game.class, ref, LockModeType.PESSIMISTIC_READ);
            ds.validateWork();
            return game;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void update(Game game) throws Exception {
        try (DataAccessScope ds = new DataAccessScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();  // ? necess?rio para o pr?ximo find encontrar o registo caso ele tenha sido criado neste transa??o
            Game uptateGame = em.find(Game.class, game.getReference(), LockModeType.PESSIMISTIC_WRITE);
            if (uptateGame == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            uptateGame.setGameStat(game.getGameStat());
            uptateGame.setName(game.getName());
            uptateGame.setUrl(game.getUrl());
            uptateGame.setBadges(game.getBadges());
            uptateGame.setPlayerPurchase(game.getPlayerPurchase());
            uptateGame.setMatches(game.getMatches());
            ds.validateWork();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }

    }

    public void delete(Game game) throws Exception {
        try (DataAccessScope ds = new DataAccessScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush(); // ? necess?rio para o pr?ximo find encontrar o registo caso ele tenha sido criado neste transa??o
            Game deleteGame = em.find(Game.class, game.getReference(), LockModeType.PESSIMISTIC_WRITE);
            if (deleteGame == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            em.remove(deleteGame);
            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
