package repository;

import user.User;
import util.ConnectionConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String sql = """
                SELECT id, username, password, email
                FROM users_list
                """;
        try (Connection connection = ConnectionConfig.open();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("email")
                ));

            }
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось отобразить пользователей.", e);
        }
        return users;
    }

    public void add(String username, String password, String email) {
        String sql = """
                INSERT INTO users_list("username", "password", "email") 
                VALUES (?,?,?)
                """;
        try (Connection connection = ConnectionConfig.open();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось добавить пользователя", e);
        }
    }

    public User getById(int id) {
        String sql = """
                SELECT id, username, password, email
                FROM users_list
                WHERE id = ?
                """;
        try (Connection connection = ConnectionConfig.open();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            User user = null;
            while (resultSet.next()) {
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("email")
                );
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось найти пользователя", e);
        }
    }

    public void update(int id, String username, String password, String email) {
        String sql = """
                UPDATE users_list
                SET username = ?,
                    password = ?,
                    email = ?
                WHERE id = ?
                """;
        try (Connection connection = ConnectionConfig.open();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось обновить данные пользователя", e);
        }
    }

    public void delete(int id) {
        String sql = """
                DELETE FROM users_list
                WHERE id = ?
                """;
        try (Connection connection = ConnectionConfig.open();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось удалить пользователя", e);
        }
    }
}
