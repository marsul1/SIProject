package dal.DataScope;

import dal.mappers.MapperBadge;
import dal.repositorys.RepositoryBadge;
import model.badges.Badge;
import model.badges.BadgePK;

import java.util.List;

public class DataScopeBadge extends AbstractDataScope implements AutoCloseable {
    public DataScopeBadge() throws Exception{
        super();
    }

    public List<Badge> getAll() throws Exception {
        return new RepositoryBadge().getAll();
    }

    public Badge find(BadgePK Id) throws Exception  {
        return new MapperBadge().read(Id);
    }

    public void delete(Badge b) throws Exception {
        new MapperBadge().delete(b);
    }

    public void deleteByKey(BadgePK Id) throws Exception {
        Badge a = new Badge();
        a.setId(Id);
        new MapperBadge().delete(a);
    }

    public void update(Badge b) throws Exception {
        new MapperBadge().update(b);
    }

    public void insert(Badge b) throws Exception {
        new MapperBadge().create(b);
    }
}
