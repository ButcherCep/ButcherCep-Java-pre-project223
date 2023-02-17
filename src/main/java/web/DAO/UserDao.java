package web.DAO;

import java.util.List;
import web.model.User;

public interface UserDao {
    List<User> showAllUsers();

    User showUserId(int id);

    void saveUser(User user);

    void updateUser(int id, User updateuser);

    void deleteUser(int id);
}
