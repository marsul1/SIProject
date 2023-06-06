package dal;

import genericInterfaces.IMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import model.matches.MultiAndSinglePK;
import model.matches.PlaysMulti;
import model.matches.SinglePlayerMatch;

public class MapperPlaysMulti implements IMapper<PlaysMulti, MultiAndSinglePK> {

    public MultiAndSinglePK create(PlaysMulti match ) throws Exception {
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

    public PlaysMulti read (MultiAndSinglePK matchPK) throws Exception {
        try (DataAccessScope ds = new DataAccessScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();  // ? necess?rio para o pr?ximo find encontrar o registo caso ele tenha sido criado neste transa??o
            PlaysMulti match = em.find(PlaysMulti.class, matchPK, LockModeType.PESSIMISTIC_READ);
            ds.validateWork();
            return match;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void update(PlaysMulti match) throws Exception {
        try (DataAccessScope ds = new DataAccessScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();  // ? necess?rio para o pr?ximo find encontrar o registo caso ele tenha sido criado neste transa??o
            PlaysMulti updateMatch = em.find(PlaysMulti.class, match.getId(), LockModeType.PESSIMISTIC_WRITE);
            if (updateMatch == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            updateMatch.setPlayer(match.getPlayer());
            updateMatch.setPoints(match.getPoints());
            updateMatch.setMultiPlayerMatch(match.getMultiPlayerMatch());
            ds.validateWork();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }

    }

    public void delete(PlaysMulti match) throws Exception {
        try (DataAccessScope ds = new DataAccessScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush(); // ? necess?rio para o pr?ximo find encontrar o registo caso ele tenha sido criado neste transa??o
            PlaysMulti deleteMatch = em.find(PlaysMulti.class, match.getId(), LockModeType.PESSIMISTIC_WRITE);
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