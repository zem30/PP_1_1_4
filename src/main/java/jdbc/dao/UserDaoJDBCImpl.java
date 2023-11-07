package jdbc.dao;

import jdbc.model.User;
import jdbc.util.Util;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final Connection connection = Util.getconnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (Id INT AUTO_INCREMENT, Name VARCHAR(50), " +
                "LastName VARCHAR(50), Age INT, PRIMARY KEY(Id))";

        try (Statement statement = connection.createStatement()) {

            statement.execute(sql);

            System.out.println("Таблица создана");
        } catch (SQLException e) {
            System.out.println("ERROR в createUsersTable: " + e);
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";

        try (Statement statement = connection.createStatement()) {

            statement.execute(sql);

            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            System.out.println("ERROR в dropUsersTable: " + e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String sql = "INSERT INTO users(Name, LastName, AGE) VALUES(?,?,?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);

            statement.executeUpdate();

            System.out.println("User c именем " + name + " добавлен в базу");

        } catch (SQLException e) {
            System.out.println("ERROR в saveUser: " + e);
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE Id =?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            statement.executeUpdate();

            System.out.println("Пользователь удален");
        } catch (SQLException e) {
            System.out.println("ERROR в removeUserById: " + e);
        }
    }

    public List<User> getAllUsers() {
        List<User> allUser = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("Id"));
                user.setName(resultSet.getString("Name"));
                user.setLastName(resultSet.getString("LastName"));
                user.setAge(resultSet.getByte("Age"));

                allUser.add(user);
            }

        } catch (SQLException e) {
            System.out.println("ERROR в getAllUsers" + e);
        }

        for (User user : allUser) {
            System.out.println(user);
        }
        return allUser;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM users";

        try (Statement statement = connection.createStatement()) {

            statement.execute(sql);

            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            System.out.println("ERROR в cleanUsersTable" + e);
        }
    }
}