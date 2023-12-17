package org.dancebot.database;

import java.sql.*;

public class DatabaseManager {
    private String url = "jdbc:mysql://localhost:3306/sds_db";
    private String username = "root";
    private String password = "J@Rung11";

    public void insertClient(int tg_id) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected successfully");

            String sql = "INSERT INTO tg_ids (tg_id) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, tg_id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("New row is added successfully");
            } else {
                System.out.println("New row error");
            }

            connection.close();
            System.out.println("Connection is closed");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
    public void insertCoach(int tg_id, String nickname, String info, String timing) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected successfully");

            String sql = "INSERT INTO coaches (tg_id, nickname, info, timing) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, tg_id);
            preparedStatement.setString(2, nickname);
            preparedStatement.setString(3, info);
            preparedStatement.setString(4, timing);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("New row is added successfully");
            } else {
                System.out.println("New row error");
            }

            connection.close();
            System.out.println("Connection is closed");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
    public void change_nickname(String new_name, String old_name){
        try{
        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println("Connected successfully");
        String sql = "UPDATE coaches SET nickname = '?' WHERE nickname = '?';" ;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, new_name);
        preparedStatement.setString(2,old_name);
        int rowsUpdated = preparedStatement.executeUpdate();
        if (rowsUpdated==1){
            System.out.println("A row is updated successfully");
        }else{
            System.out.println("New row error");
        }
        } catch (SQLException e) {
        System.out.println("Database error: " + e.getMessage());
        }
    }

    public void change_timing(String new_timing, String name){
        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected successfully");
            String sql = "UPDATE coaches SET info = '?' WHERE nickname = '?';" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, new_timing);
            preparedStatement.setString(2, name);
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated==1){
                System.out.println("A row is updated successfully");
            }else{
                System.out.println("New row error");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}