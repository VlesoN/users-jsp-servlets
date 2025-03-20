package repository;

import user.User;
import util.ConnectionConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {

    private static final Connection CONNECTION = ConnectionConfig.open();


    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String sql = """
                SELECT id, username, password, email
                FROM users_list
                """;
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(sql)) {
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
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(sql)) {
            CONNECTION.setAutoCommit(false);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);

            preparedStatement.executeUpdate();
            CONNECTION.commit();
        } catch (Exception e) {
                try {
                    CONNECTION.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException("Ошибка при откате транзакции",ex);
                }
            throw new RuntimeException("Ошибка при добавлении пользователя",e);
        }
    }

    public Optional<User> getById(int id) {
        String sql = """
                SELECT id, username, password, email
                FROM users_list
                WHERE id = ?
                """;
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(sql)) {
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
            return Optional.ofNullable(user);
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
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(sql)) {
            CONNECTION.setAutoCommit(false);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();
            CONNECTION.commit();
        } catch (Exception e) {
                try {
                    CONNECTION.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException("Ошибка при откате транзакции",ex);
                }
                throw new RuntimeException("Не удалось изменить данные пользователя",e);
        }
    }

    public void delete(int id) {
        String sql = """
                DELETE FROM users_list
                WHERE id = ?
                """;
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(sql)) {
            CONNECTION.setAutoCommit(false);

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            CONNECTION.commit();
        } catch (Exception e) {
                try {
                    CONNECTION.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException("Ошибка при откате транзакции",ex);
                }
                throw new RuntimeException("Ошибка при удалении пользователя",e);
        }
    }

    public Optional<User> find(String username, String password) {
        String sql = """
                SELECT id,username,password,email
                FROM users_list
                WHERE username = ? AND password = ?
                """;

        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(sql)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            User user = null;

            if (resultSet.next()) {
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("email")
                );
            }
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при аутентификации", e);
        }
    }
}
