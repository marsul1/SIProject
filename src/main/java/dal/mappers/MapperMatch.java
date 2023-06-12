package dal.mappers;

import dal.DataScope.DataAccessScope;
import genericInterfaces.IMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import model.matches.Match;
import model.matches.MatchPK;

public class MapperMatch implements IMapper<Match, MatchPK> {

    public MatchPK create(Match match ) throws Exception {
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

    public Match read (MatchPK matchPK) throws Exception {
        try (DataAccessScope ds = new DataAccessScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();  // ? necess?rio para o pr?ximo find encontrar o registo caso ele tenha sido criado neste transa??o
            Match match = em.find(Match.class, matchPK, LockModeType.PESSIMISTIC_READ);
            ds.validateWork();
            return match;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void update(Match match) throws Exception {
        try (DataAccessScope ds = new DataAccessScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();  // ? necess?rio para o pr?ximo find encontrar o registo caso ele tenha sido criado neste transa??o
            Match uptateMatch = em.find(Match.class, match.getId(), LockModeType.PESSIMISTIC_WRITE);
            if (uptateMatch == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            uptateMatch.setRegion(match.getRegion());
            uptateMatch.setGame(match.getGame());
            uptateMatch.setStartTime(match.getStartTime());
            uptateMatch.setEndTime(match.getEndTime());
            uptateMatch.setMultiPlayerMatch(match.getMultiPlayerMatch());
            uptateMatch.setSinglePlayerMatch(match.getSinglePlayerMatch());
            ds.validateWork();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }

    }

    public void delete(Match match) throws Exception {
        try (DataAccessScope ds = new DataAccessScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush(); // ? necess?rio para o pr?ximo find encontrar o registo caso ele tenha sido criado neste transa??o
            Match deleteMatch = em.find(Match.class, match.getId(), LockModeType.PESSIMISTIC_WRITE);
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
