package dal.repositorys;

import dal.DataScope.DataAccessScope;
import dal.mappers.MapperBadge;
import genericInterfaces.IRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import model.badges.Badge;
import model.badges.BadgePK;
import java.util.List;

public class RepositoryBadge implements IRepository<Badge, BadgePK> {

    public Badge find(BadgePK Id) throws Exception {
        MapperBadge m = new MapperBadge();
        try
        {
            return m.read(Id);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public List<Badge> getAll() throws Exception {
        try (DataAccessScope ds = new DataAccessScope())
        {
            EntityManager em = ds.getEntityManager();
            //em.flush();  // ? necess?rio para a pr?xima query encontrar os registos caso eles tenham sido criados neste transa??o
            // com queries o flush ? feito automaticamente.
            List<Badge> l = em.createQuery("select b from Badge b",Badge.class).setLockMode(LockModeType.PESSIMISTIC_READ)
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


    public void add(Badge b) throws Exception {
        MapperBadge m = new MapperBadge();

        try
        {
            m.create(b);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            throw e;
        }
    }



    public void save(Badge b) throws Exception {
        MapperBadge m = new MapperBadge();
        try
        {
            m.update(b);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void delete(Badge b) throws Exception {
        MapperBadge m = new MapperBadge();

        try
        {
            m.delete(b);;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
