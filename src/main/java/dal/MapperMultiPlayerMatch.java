package dal;

import genericInterfaces.IMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import model.matches.Match;
import model.matches.MatchPK;
import model.matches.MultiAndSinglePK;
import model.matches.MultiPlayerMatch;

public class MapperMultiPlayerMatch implements IMapper<MultiPlayerMatch, MatchPK> {

    public MatchPK create(MultiPlayerMatch multiPlayerMatch ) throws Exception {
        try (DataAccessScope ds = new DataAccessScope()) {
            EntityManager em = ds.getEntityManager();
            em.persist(multiPlayerMatch);
            ds.validateWork();
            return multiPlayerMatch.getId();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public MultiPlayerMatch read (MatchPK matchPK) throws Exception {
        try (DataAccessScope ds = new DataAccessScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();  // ? necess?rio para o pr?ximo find encontrar o registo caso ele tenha sido criado neste transa??o
            MultiPlayerMatch match = em.find(MultiPlayerMatch.class, matchPK, LockModeType.PESSIMISTIC_READ);
            ds.validateWork();
            return match;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void update(MultiPlayerMatch match) throws Exception {
        try (DataAccessScope ds = new DataAccessScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();  // ? necess?rio para o pr?ximo find encontrar o registo caso ele tenha sido criado neste transa??o
            MultiPlayerMatch updateMatch = em.find(MultiPlayerMatch.class, match.getId(), LockModeType.PESSIMISTIC_WRITE);
            if (updateMatch == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            updateMatch.setMatch(match.getMatch());
            updateMatch.setState(match.getState());
            updateMatch.setPlaysMultis(match.getPlaysMultis());
            ds.validateWork();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }

    }

    public void delete(MultiPlayerMatch match) throws Exception {
        try (DataAccessScope ds = new DataAccessScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush(); // ? necess?rio para o pr?ximo find encontrar o registo caso ele tenha sido criado neste transa??o
            MultiPlayerMatch deleteMatch = em.find(MultiPlayerMatch.class, match.getId(), LockModeType.PESSIMISTIC_WRITE);
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