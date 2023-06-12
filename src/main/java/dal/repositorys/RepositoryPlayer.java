package dal.repositorys;

import dal.DataScope.DataAccessScope;
import dal.mappers.MapperPlayer;
import genericInterfaces.IRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import model.players.Player;

import java.util.List;

public class RepositoryPlayer implements IRepository<Player, Integer> {

    public Player find(Integer id) throws Exception {
        MapperPlayer m = new MapperPlayer();
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

    public List<Player> getAll() throws Exception {
        try (DataAccessScope ds = new DataAccessScope())
        {
            EntityManager em = ds.getEntityManager();
            //em.flush();  // ? necess?rio para a pr?xima query encontrar os registos caso eles tenham sido criados neste transa??o
            // com queries o flush ? feito automaticamente.
            List<Player> l = em.createQuery("select p from Player p",Player.class).setLockMode(LockModeType.PESSIMISTIC_READ)
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


    public void add(Player p) throws Exception {
        MapperPlayer m = new MapperPlayer();

        try
        {
            m.create(p);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void save(Player p) throws Exception {
        MapperPlayer m = new MapperPlayer();
        try
        {
            m.update(p);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void delete(Player p) throws Exception {
        MapperPlayer m = new MapperPlayer();
        try
        {
            m.delete(p);;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
