package dal.repositorys;

import dal.DataScope.DataAccessScope;
import dal.mappers.MapperPlaysMulti;
import genericInterfaces.IRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import model.matches.MultiAndSinglePK;
import model.matches.PlaysMulti;

import java.util.List;

    public class RepositoryPlaysMulti implements IRepository<PlaysMulti, MultiAndSinglePK> {

    public PlaysMulti find(MultiAndSinglePK id) throws Exception {
        MapperPlaysMulti m = new MapperPlaysMulti();
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

    public List<PlaysMulti> getAll() throws Exception {
        try (DataAccessScope ds = new DataAccessScope())
        {
            EntityManager em = ds.getEntityManager();
            //em.flush();  // ? necess?rio para a pr?xima query encontrar os registos caso eles tenham sido criados neste transa??o
            // com queries o flush ? feito automaticamente.
            List<PlaysMulti> l = em.createQuery("select m from PlaysMulti m",PlaysMulti.class).setLockMode(LockModeType.PESSIMISTIC_READ)
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


    public void add(PlaysMulti match) throws Exception {
        MapperPlaysMulti m = new MapperPlaysMulti();
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

    public void save(PlaysMulti match) throws Exception {
        MapperPlaysMulti m = new MapperPlaysMulti();
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

    public void delete(PlaysMulti match) throws Exception {
        MapperPlaysMulti m = new MapperPlaysMulti();
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