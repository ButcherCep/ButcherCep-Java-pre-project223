package web.DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import web.model.User;
@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> showAllUsers() {
        TypedQuery<User> query= entityManager.createQuery("SELECT u FROM User u ", User.class);
        return query.getResultList();
    }
    @Override
    public User showUserId(int id) {
        TypedQuery<User> query= entityManager.createQuery("SELECT u FROM User u WHERE u=:id", User.class);
        query.setParameter("id",id);
        return query.getResultList().stream().findAny().orElse(null);
    }
    @Override
    public void saveUser(User user) {
        entityManager.persist(user);

    }
    @Override
    public void updateUser(int id, User updateUser) {
        entityManager.merge(updateUser);
    }

    @Override
    public void deleteUser(int id) {
        TypedQuery<User> query= entityManager.createQuery("DELETE FROM User WHERE id=:id", User.class);
        query.setParameter("id",id);
    }
}
