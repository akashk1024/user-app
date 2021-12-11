package com.greatlearning.jdbc.dao;

import com.greatlearning.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public void createUserTable(Connection connection) throws SQLException {
        String sql = "create table Users( id integer, first_name varchar(50), last_name varchar(50), email varchar(50))";
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }

    public int insertUser(Connection connection, User user) throws SQLException {
        String sql = "insert into Users values(?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, user.getId());
        preparedStatement.setString(2, user.getFirstName());
        preparedStatement.setString(3, user.getLastName());
        preparedStatement.setString(4, user.getEmailId());
        return preparedStatement.executeUpdate();

    }

    public int updateUser(Connection connection, int id, User updatedUser) throws SQLException {
        String sql = "update Users set first_name=?,last_name=?,email=? where id =" + id;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, updatedUser.getFirstName());
        preparedStatement.setString(2, updatedUser.getLastName());
        preparedStatement.setString(3, updatedUser.getEmailId());
        return preparedStatement.executeUpdate();
    }

    public User readUser(Connection connection, int id) throws SQLException {
        String sql = "select * from Users where id=" + id;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        User user = null;
        while (resultSet.next()) {
            int foundId = resultSet.getInt(1);
            if (foundId == id) {
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String emailId = resultSet.getString(4);
                user = new User(id, firstName, lastName, emailId);
                break;
            }
        }
        return user;
    }

    public int deleteUser(Connection connection, int id) throws SQLException {
        String sql = "delete from Users where id=" + id;
        Statement statement = connection.createStatement();
        return statement.executeUpdate(sql);
    }

    public List<User> readAllUser(Connection connection) throws SQLException {
        String sql = "select * from Users";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        User user = null;
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            int foundId = resultSet.getInt(1);
            String firstName = resultSet.getString(2);
            String lastName = resultSet.getString(3);
            String emailId = resultSet.getString(4);
            user = new User(foundId, firstName, lastName, emailId);
            users.add(user);
        }

        return users;
}
}
