package dal.repositorys;

import dal.DataScope.DataAccessScope;
import dal.mappers.MapperMultiPlayerMatch;
import genericInterfaces.IRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import model.matches.MatchPK;
import model.matches.MultiPlayerMatch;

import java.util.List;

public class RepositoryMultiPlayerMatch implements IRepository<MultiPlayerMatch, MatchPK> {

    public MultiPlayerMatch find(MatchPK id) throws Exception {
        MapperMultiPlayerMatch m = new MapperMultiPlayerMatch();
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

    public List<MultiPlayerMatch> getAll() throws Exception {
        try (DataAccessScope ds = new DataAccessScope())
        {
            EntityManager em = ds.getEntityManager();
            //em.flush();  // ? necess?rio para a pr?xima query encontrar os registos caso eles tenham sido criados neste transa??o
            // com queries o flush ? feito automaticamente.
            List<MultiPlayerMatch> l = em.createQuery("select m from MultiPlayerMatch m",MultiPlayerMatch.class).setLockMode(LockModeType.PESSIMISTIC_READ)
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


    public void add(MultiPlayerMatch match) throws Exception {
        MapperMultiPlayerMatch m = new MapperMultiPlayerMatch();

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

    public void save(MultiPlayerMatch match) throws Exception {
        MapperMultiPlayerMatch m = new MapperMultiPlayerMatch();
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

    public void delete(MultiPlayerMatch match) throws Exception {
        MapperMultiPlayerMatch m = new MapperMultiPlayerMatch();
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