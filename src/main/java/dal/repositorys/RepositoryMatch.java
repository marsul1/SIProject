package dal.repositorys;

import dal.DataScope.DataAccessScope;
import dal.mappers.MapperMatch;
import genericInterfaces.IRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import model.matches.Match;
import model.matches.MatchPK;

import java.util.List;

public class RepositoryMatch implements IRepository<Match, MatchPK> {

    public Match find(MatchPK id) throws Exception {
        MapperMatch m = new MapperMatch();
        try
        {
            return m.read(id);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public List<Match> getAll() throws Exception {
        try (DataAccessScope ds = new DataAccessScope())
        {
            EntityManager em = ds.getEntityManager();
            //em.flush();  // ? necess?rio para a pr?xima query encontrar os registos caso eles tenham sido criados neste transa??o
            // com queries o flush ? feito automaticamente.
            List<Match> l = em.createQuery("select m from Match m",Match.class).setLockMode(LockModeType.PESSIMISTIC_READ)
                    .getResultList();
            ds.validateWork();
            return l;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            throw e;
        }
    }


    public void add(Match match) throws Exception {
        MapperMatch m = new MapperMatch();

        try
        {
            m.create(match);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void save(Match match) throws Exception {
        MapperMatch m = new MapperMatch();
        try
        {
            m.update(match);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void delete(Match match) throws Exception {
        MapperMatch m = new MapperMatch();
        try
        {
            m.delete(match);;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}