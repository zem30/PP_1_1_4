package jdbc.dao;

import jdbc.model.User;
import jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {

    private static SessionFactory sessionFactory = Util.getSessionFactory();


    public UserDaoHibernateImpl() {

    }

    //createUsersTable прошел тест и работает
    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (Id INT AUTO_INCREMENT, Name VARCHAR(50), " +
                "LastName VARCHAR(50), Age TINYINT(3), PRIMARY KEY(Id))";

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query=session.createSQLQuery(sql);
            query.executeUpdate();

            transaction.commit();

            System.out.println("Таблица создана");
        }
    }

    //dropUsersTable прошел тест и работает
    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            transaction.commit();

            System.out.println("Таблица удалена");
        }
    }

    //saveUser прошел тест и работает
    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(new User(name, lastName, age));
            transaction.commit();

            System.out.println("User c именем " + name + " добавлен в базу");
        }
    }

    //removeUserById прошел тест и работает
    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = new User();
            user.setId(id);
            session.delete(user);
            transaction.commit();

            System.out.println("Пользователь удален");
        }
    }

    //getAllUsers прошел тест и работает
    @Override
    public List<User> getAllUsers() {
        List<User> allUser = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            allUser = session.createQuery("from User", User.class).getResultList();
            System.out.println(allUser);
            transaction.commit();
        }

        return allUser;
    }

    //cleanUsersTable прошел тест и работает
    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM User");
            query.executeUpdate();
            transaction.commit();

            System.out.println("Таблица очищена");
        }
    }
}
