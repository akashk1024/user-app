package com.greatlearning.jdbc.service;

import com.greatlearning.jdbc.dao.UserDao;
import com.greatlearning.jdbc.model.User;

import java.sql.Connection;
import java.sql.SQLException;

public class UserService {
    private UserDao userDao;
    Connection connection;

    public UserService(UserDao userDao, Connection connection) {
        this.userDao = userDao;
        this.connection = connection;
    }

    public int createUser(int id, String fn, String ln, String mailid) throws SQLException {
        User user=new User(id,fn,ln,mailid);
        return userDao.insertUser(connection,user);
    }
    public int updateUser(int id,String fn, String ln, String mailid) throws SQLException {
        User updatedUser= new User(id,fn,ln,mailid);
        return userDao.updateUser(connection,id,updatedUser);
    }
    public User getUser(int id) throws SQLException {
        return userDao.readUser(connection,id);
    }
    public int deleteUser(int id) throws SQLException {
        return userDao.deleteUser(connection,id);
    }
    public void createTable() throws SQLException {
        userDao.createUserTable(connection);
    }
    public void createDummyUser() throws SQLException {
        User user=new User(0,"test","test","test");
        userDao.insertUser(connection,user);
    }
    public void printAllUsers() throws SQLException {
        userDao.readAllUser(connection).stream().forEach(System.out::println);
    }
}
