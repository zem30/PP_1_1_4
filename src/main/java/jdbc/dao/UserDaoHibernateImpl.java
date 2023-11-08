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
        Transaction transaction = null;
        String sql = "CREATE TABLE IF NOT EXISTS users (Id INT AUTO_INCREMENT, Name VARCHAR(50), " +
                "LastName VARCHAR(50), Age TINYINT(3), PRIMARY KEY(Id))";

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql);
            query.executeUpdate();

            transaction.commit();

            System.out.println("Таблица создана");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println("Exception in createUsersTable: " + e);
            }
        }
    }

    //dropUsersTable прошел тест и работает
    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            transaction.commit();

            System.out.println("Таблица удалена");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println("Exception in dropUsersTable: " + e);
            }
        }
    }

    //saveUser прошел тест и работает
    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(new User(name, lastName, age));
            transaction.commit();

            System.out.println("User c именем " + name + " добавлен в базу");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println("Exception in saveUser: " + e);
            }
        }
    }

    //removeUserById прошел тест и работает
    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("DELETE FROM users WHERE Id =?").setParameter(1, id).executeUpdate();
            transaction.commit();

            System.out.println("Пользователь удален");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println("Exception in removeUserById: " + e);
            }
        }
    }

    //getAllUsers прошел тест и работает
    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        List<User> allUser = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            allUser = session.createQuery("from User", User.class).getResultList();
            System.out.println(allUser);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println("Exception in getAllUsers: " + e);
            }
        }
        return allUser;
    }

    //cleanUsersTable прошел тест и работает
    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery("TRUNCATE TABLE users");
            query.executeUpdate();
            transaction.commit();

            System.out.println("Таблица очищена");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println("Exception in cleanUsersTable: " + e);
            }
        }
    }
}
