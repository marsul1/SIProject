package dal;

import genericInterfaces.IMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import model.matches.*;

public class MapperSinglePlayerMatch implements IMapper<SinglePlayerMatch, MultiAndSinglePK> {

    public MultiAndSinglePK create(SinglePlayerMatch match ) throws Exception {
        try (DataAccessScope ds = new DataAccessScope()) {
            EntityManager em = ds.getEntityManager();
            em.persist(match);
            ds.validateWork();
            return match.getId();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public SinglePlayerMatch read (MultiAndSinglePK matchPK) throws Exception {
        try (DataAccessScope ds = new DataAccessScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();  // ? necess?rio para o pr?ximo find encontrar o registo caso ele tenha sido criado neste transa??o
            SinglePlayerMatch match = em.find(SinglePlayerMatch.class, matchPK, LockModeType.PESSIMISTIC_READ);
            ds.validateWork();
            return match;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void update(SinglePlayerMatch match) throws Exception {
        try (DataAccessScope ds = new DataAccessScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();  // ? necess?rio para o pr?ximo find encontrar o registo caso ele tenha sido criado neste transa??o
            SinglePlayerMatch updateMatch = em.find(SinglePlayerMatch.class, match.getId(), LockModeType.PESSIMISTIC_WRITE);
            if (updateMatch == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            updateMatch.setMatch(match.getMatch());
            updateMatch.setPlayer(match.getPlayer());
            updateMatch.setPoints(match.getPoints());
            updateMatch.setDifficulty(match.getDifficulty());
            ds.validateWork();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }

    }

    public void delete(SinglePlayerMatch match) throws Exception {
        try (DataAccessScope ds = new DataAccessScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush(); // ? necess?rio para o pr?ximo find encontrar o registo caso ele tenha sido criado neste transa??o
            SinglePlayerMatch deleteMatch = em.find(SinglePlayerMatch.class, match.getId(), LockModeType.PESSIMISTIC_WRITE);
            if (deleteMatch == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            em.remove(deleteMatch);
            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

}