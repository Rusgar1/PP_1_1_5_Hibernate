package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    Connection connection = getConn();
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (\n"
                + "id BIGINT NOT NULL AUTO_INCREMENT,\n"
                + "name VARCHAR(50),\n"
                + "lastName VARCHAR(50),\n"
                + "age TINYINT,\n"
                + "PRIMARY KEY (id)"
                + ")";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        try(Statement Statement = connection.createStatement()){
            Statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users(name, lastName, age) VALUES (?, ?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users ";
        List<User> usersList = new ArrayList<>();
        try(ResultSet resultSet = connection.createStatement().executeQuery(sql)){
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                usersList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";
        try(Statement statement = getConn().createStatement()){
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
