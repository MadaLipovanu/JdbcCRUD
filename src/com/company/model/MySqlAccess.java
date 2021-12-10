package com.company.model;

import java.sql.*;

public class MySqlAccess {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void setConnection() throws SQLException {
        this.connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/feedback?user=root&password=123456");
        statement = connect.createStatement();
    }

    //START OF CRUD OPERATIONS
    public void create() throws SQLException {
        setConnection();
        preparedStatement = connect.prepareStatement("INSERT INTO comments (MYUSER, WEBPAGE, DATUM, SUMMARY, COMMENTS) VALUES ('Ana', 'TESTWEB', 'TESTWEB', 'TESTSUMMARY', 'TESTCOMMENT')");
        int rowsInserted = statement.executeUpdate("INSERT INTO comments (MYUSER, WEBPAGE, DATUM, SUMMARY, COMMENTS) VALUES ('Ana', 'TESTWEB','TESTWEB', 'TESTSUMMARY', 'TESTCOMMENT')");
        System.out.println(rowsInserted + " rows updated");
        if (rowsInserted >= 0) {
            System.out.println("A new user was inserted successfully!");
        }
        System.out.println();
    }

    public void read() throws SQLException {
        setConnection();
        Statement statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from comments");
        System.out.println("Read table: ");
        while (resultSet.next()) {
            String username = resultSet.getString(1);
            String webpage = resultSet.getString(2);
            String date = resultSet.getString("datum");
            String summary = resultSet.getString("summary");
            String comments = resultSet.getString("comments");
            String output = "User:  " + username + " , " + webpage + " , " + date + " , " + summary + " , " + comments + ";";
            System.out.printf((output) + "%n", username, webpage, date, summary, comments);
        }
        System.out.println();
    }

    public void update() throws SQLException {
        setConnection();
        String sql = "UPDATE comments SET MYUSER = 'deni' WHERE id=1";
        PreparedStatement prStatement = connect.prepareStatement(sql);
        int rowsUpdated = statement.executeUpdate("UPDATE comments SET MYUSER = 'deni' WHERE id=1");
        System.out.println(rowsUpdated + " rows updated");
        if (rowsUpdated != 0) {
            System.out.println("A new user was updated successfully!");
        }
        System.out.println();
    }

    public void delete() throws SQLException {
        setConnection();
        preparedStatement = connect.prepareStatement("DELETE FROM comments WHERE MYUSER='TESTUSER'");
        preparedStatement.executeUpdate();
        int rowsDeleted = statement.executeUpdate("DELETE FROM comments WHERE MYUSER='TESTUSER'");
        System.out.println(rowsDeleted + " rows deleted");
        if (rowsDeleted > 0) {
            System.out.println("A new user was deleted successfully!");
        }
        System.out.println();
    }

    public void readDataBase() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  // This will load the MySQL driver, each DB has its own driver
            setConnection();
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    private void close() {  //close the resultSet
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }
}

