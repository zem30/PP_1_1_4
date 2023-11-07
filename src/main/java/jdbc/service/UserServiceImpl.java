package jdbc.service;

import jdbc.dao.UserDao;
import jdbc.dao.UserDaoHibernateImpl;
import jdbc.dao.UserDaoJDBCImpl;
import jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDaoHibernate = new UserDaoHibernateImpl();

    public void createUsersTable()  {
        userDaoHibernate.createUsersTable();
    }


    public void dropUsersTable() {
        userDaoHibernate.dropUsersTable();
    }


    public void saveUser(String name, String lastName, byte age) {
        userDaoHibernate.saveUser(name,lastName,age);
    }


    public void removeUserById(long id) {
        userDaoHibernate.removeUserById(id);
    }


    public List<User> getAllUsers() {
        return userDaoHibernate.getAllUsers();
    }


    public void cleanUsersTable() {
        userDaoHibernate.cleanUsersTable();
    }
}
