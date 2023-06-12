package dal.repositorys;

import dal.DataScope.DataAccessScope;
import dal.mappers.MapperGame;
import genericInterfaces.IRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import model.game.Game;

import java.util.List;

public class RepositoryGame implements IRepository<Game, String> {

    public Game find(String reference) throws Exception {
        MapperGame m = new MapperGame();
        try
        {
            return m.read(reference);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public List<Game> getAll() throws Exception {
        try (DataAccessScope ds = new DataAccessScope())
        {
            EntityManager em = ds.getEntityManager();
            //em.flush();  // ? necess?rio para a pr?xima query encontrar os registos caso eles tenham sido criados neste transa??o
            // com queries o flush ? feito automaticamente.
            List<Game> l = em.createQuery("select g from Game g",Game.class).setLockMode(LockModeType.PESSIMISTIC_READ)
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


    public void add(Game g) throws Exception {
        MapperGame m = new MapperGame();

        try
        {
            m.create(g);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            throw e;
        }
    }



    public void save(Game g) throws Exception {
        MapperGame m = new MapperGame();
        try
        {
            m.update(g);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void delete(Game g) throws Exception {
        MapperGame m = new MapperGame();

        try
        {
            m.delete(g);;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}