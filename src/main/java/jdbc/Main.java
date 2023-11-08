package jdbc;

import jdbc.service.UserService;
import jdbc.service.UserServiceImpl;


public class Main {
    private static final UserService userDao = new UserServiceImpl();

    public static void main(String[] args) {
//        1. Создание таблицы User(ов)
//        2. Добавление 4 User(ов) в таблицу с данными на свой выбор. После каждого добавления должен быть вывод в консоль ( User с именем – name добавлен в базу данных )
//        3. Получение всех User из базы и вывод в консоль ( должен быть переопределен toString в классе User)
//        4. Очистка таблицы User(ов)
//        5. Удаление таблицы

        userDao.dropUsersTable();
        userDao.createUsersTable();

        userDao.saveUser("Name1", "LastName1", (byte) 20);
        userDao.saveUser("Name2", "LastName2", (byte) 25);
        userDao.saveUser("Name3", "LastName3", (byte) 31);
        userDao.saveUser("Name4", "LastName4", (byte) 38);

        userDao.getAllUsers();
        userDao.removeUserById(1);
        userDao.cleanUsersTable();
        userDao.dropUsersTable();


    }
}
