package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
  //  private final Connection connection = Util.getConnection();
    public UserDaoJDBCImpl() {

    }
    @Override
    public void createUsersTable() {
        String sql =  "CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(50) NOT NULL," +
                "lastName VARCHAR(50) NOT NULL," +
                "age TINYINT NOT NULL" +
                ")";
        try (Connection connection = Util.getConnection(); // Получаем соединение тут
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Таблица 'users' создана или уже существует.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

@Override
    public void dropUsersTable() {
    String sql = "DROP TABLE IF EXISTS users";
    try (Connection connection = Util.getConnection();
         Statement statement = connection.createStatement()) {
        statement.execute(sql);
        System.out.println("Таблица 'users' удалена или не существовала.");
    } catch (SQLException e) {
        e.printStackTrace();
    }

    }
@Override
    public void saveUser(String name, String lastName, byte age) {
    String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
    try (Connection connection = Util.getConnection();
         PreparedStatement pStatement = connection.prepareStatement(sql)) {
        pStatement.setString(1, name);
        pStatement.setString(2, lastName);
        pStatement.setByte(3, age);
        pStatement.executeUpdate();
        System.out.println("Пользователь с именем — " + name + " добавлен в базу данных.");
    } catch (SQLException e) {
        e.printStackTrace();
    }

    }
@Override
    public void removeUserById(long id) {
    String sql = "DELETE FROM users WHERE id = ?";
    try(Connection connection = Util.getConnection();
    PreparedStatement pStatement = connection.prepareStatement(sql)) {
        pStatement.setLong(1, id);
        pStatement.executeUpdate();
        System.out.println("Пользователь с ID " + id + " удален.");
    } catch (SQLException e) {
        e.printStackTrace();
    }

    }
@Override
    public List<User> getAllUsers() {
      List<User> users = new ArrayList<>();
      String sql = "SELECT * FROM users";
    try(Connection connection = Util.getConnection();
    Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(sql)) { // ResultSet хранит результат запроса

        while (resultSet.next()) { // Перебираем все строки результата
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));
            user.setLastName(resultSet.getString("lastName"));
            user.setAge(resultSet.getByte("age"));
            users.add(user);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return users;

    }
@Override
    public void cleanUsersTable() {
    String sql = "TRUNCATE TABLE users";
    try (Connection connection = Util.getConnection();
    Statement statement = connection.createStatement()) {
        statement.execute(sql);
        System.out.println("Таблица 'users' очищена.");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

}
