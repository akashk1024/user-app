package com.greatlearning.jdbc;

import com.greatlearning.jdbc.dao.UserDao;
import com.greatlearning.jdbc.model.User;
import com.greatlearning.jdbc.service.UserService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class UserInterface {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        UserDao userDao = new UserDao();
        UserService userService = new UserService(userDao, connection);
        userService.createTable();
        userService.createDummyUser();
        defaultOptions();
        int input;
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextInt();
        int userid = 1;
        int id = 0;
        String firstName, lastName, emailId;
        while(input!=0){
        switch (input) {
            case 1:
                System.out.println("Enter the first name, last name and email_id respectively");
                firstName = scanner.next();
                lastName = scanner.next();
                emailId = scanner.next();
                int output = userService.createUser(userid, firstName, lastName, emailId);
                System.out.println("No. of records inserted=" + output);
                userid++;
                defaultOptions();
                input = scanner.nextInt();
                break;
            case 2:
                System.out.println("Enter the first name, last name and email_id respectively");
                id = scanner.nextInt();
                firstName = scanner.next();
                lastName = scanner.next();
                emailId = scanner.next();
                int result = userService.updateUser(id, firstName, lastName, emailId);
                System.out.println("No. of records updated=" + result);
                defaultOptions();
                input = scanner.nextInt();
                break;
            case 3:
                System.out.print("Enter id:");
                id = scanner.nextInt();
                User user= userService.getUser(id);
                System.out.println(user);
                defaultOptions();
                input = scanner.nextInt();
                break;
            case 4:
                System.out.print("Enter id:");
                id = scanner.nextInt();
                int op = userService.deleteUser(id);
                System.out.println("No. of records deleted=" + op);
                defaultOptions();
                input = scanner.nextInt();
                break;
            case 5:
                userService.printAllUsers();
                defaultOptions();
                input = scanner.nextInt();
                break;
            case 0:
                connection.close();
                break;
            default:

        }
        }
    }

    private static void defaultOptions() {
        System.out.println("!!!----Welcome to USER CRUD Operations----!!!");
        System.out.println("Enter the operation that you want to perform \n1.Registration\n2.Update\n3.Display Data\n4.Delete\n5.Display all users\n0.Exit");
    }
}
