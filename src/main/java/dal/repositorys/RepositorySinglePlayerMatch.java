package dal.repositorys;

import dal.DataScope.DataAccessScope;
import dal.mappers.MapperSinglePlayerMatch;
import genericInterfaces.IRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import model.matches.MultiAndSinglePK;
import model.matches.SinglePlayerMatch;

import java.util.List;

public class RepositorySinglePlayerMatch implements IRepository<SinglePlayerMatch, MultiAndSinglePK> {

    public SinglePlayerMatch find(MultiAndSinglePK id) throws Exception {
        MapperSinglePlayerMatch m = new MapperSinglePlayerMatch();
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

    public List<SinglePlayerMatch> getAll() throws Exception {
        try (DataAccessScope ds = new DataAccessScope())
        {
            EntityManager em = ds.getEntityManager();
            //em.flush();  // ? necess?rio para a pr?xima query encontrar os registos caso eles tenham sido criados neste transa??o
            // com queries o flush ? feito automaticamente.
            List<SinglePlayerMatch> l = em.createQuery("select m from SinglePlayerMatch m",SinglePlayerMatch.class).setLockMode(LockModeType.PESSIMISTIC_READ)
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


    public void add(SinglePlayerMatch match) throws Exception {
        MapperSinglePlayerMatch m = new MapperSinglePlayerMatch();
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

    public void save(SinglePlayerMatch match) throws Exception {
        MapperSinglePlayerMatch m = new MapperSinglePlayerMatch();
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

    public void delete(SinglePlayerMatch match) throws Exception {
        MapperSinglePlayerMatch m = new MapperSinglePlayerMatch();
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