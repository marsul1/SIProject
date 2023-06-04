package dal;

import genericInterfaces.IMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import model.badges.Badge;
import model.badges.BadgePK;


public class MapperBadge implements IMapper<Badge, BadgePK> {


    public BadgePK create(Badge badge) throws Exception {
        try (DataAccessScope ds = new DataAccessScope()) {
            EntityManager em = ds.getEntityManager();
            em.persist(badge);
            ds.validateWork();
            return badge.getId();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Badge read (BadgePK id) throws Exception {
        try (DataAccessScope ds = new DataAccessScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();  // ? necess?rio para o pr?ximo find encontrar o registo caso ele tenha sido criado neste transa??o
            Badge badge = em.find(Badge.class, id, LockModeType.PESSIMISTIC_READ);
            ds.validateWork();
            return badge;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void update(Badge badge) throws Exception {
        try (DataAccessScope ds = new DataAccessScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush();  // ? necess?rio para o pr?ximo find encontrar o registo caso ele tenha sido criado neste transa??o
            Badge uptateBadge = em.find(Badge.class, badge.getId(), LockModeType.PESSIMISTIC_WRITE);
            if (uptateBadge == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            uptateBadge.setImageUrl(badge.getImageUrl());
            uptateBadge.setPointsLimit(uptateBadge.getPointsLimit());
            uptateBadge.setGame(badge.getGame());
            uptateBadge.setPlayers(badge.getPlayers());
            ds.validateWork();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }

    }

    public void delete(Badge badge) throws Exception {
        try (DataAccessScope ds = new DataAccessScope()) {
            EntityManager em = ds.getEntityManager();
            em.flush(); // ? necess?rio para o pr?ximo find encontrar o registo caso ele tenha sido criado neste transa??o
            Badge deleteBadge = em.find(Badge.class, badge.getId(), LockModeType.PESSIMISTIC_WRITE);
            if (deleteBadge == null)
                throw new java.lang.IllegalAccessException("Entidade inexistente");
            em.remove(deleteBadge);
            ds.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
