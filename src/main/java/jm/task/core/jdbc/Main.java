package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoJDBCImpl();
        userDao.createUsersTable();
        userDao.saveUser("Степан", "Иванов", (byte) 21);
        userDao.saveUser("Анжелика", "Петрова", (byte) 25);
        userDao.saveUser("Анна", "Кирова", (byte) 19);
        userDao.saveUser("Николай", "Сидоров", (byte) 35);

        List<User> users = userDao.getAllUsers();
        System.out.println("Получены все пользователи:");
        if (users != null) {
            for (User user : users) {
                System.out.println(user);
            }
        }

        userDao.removeUserById(3);

        userDao.cleanUsersTable();

        userDao.dropUsersTable();
    }
}
