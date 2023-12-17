package org.dancebot.database;

import java.sql.*;

public class DatabaseManager {
    private String url = "jdbc:mysql://localhost:3306/sds_db";
    private String username = "root";
    private String password = "J@Rung11";

    public void insertClient(int tg_id) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Соединение с базой данных установлено");

            String sql = "INSERT INTO tg_ids (tg_id) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, tg_id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Новая запись добавлена успешно");
            } else {
                System.out.println("Ошибка при добавлении новой записи");
            }

            connection.close();
            System.out.println("Соединение с базой данных закрыто");
        } catch (SQLException e) {
            System.out.println("Ошибка при работе с базой данных: " + e.getMessage());
        }
    }
    public void insertCoach_tgId(int tg_id) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Соединение с базой данных установлено");

            String sql = "INSERT INTO coaches (tg_id) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, tg_id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Новая запись добавлена успешно");
            } else {
                System.out.println("Ошибка при добавлении новой записи");
            }

            connection.close();
            System.out.println("Соединение с базой данных закрыто");
        } catch (SQLException e) {
            System.out.println("Ошибка при работе с базой данных: " + e.getMessage());
        }
    }
    public void insertCoach_nickname(String nickname) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Соединение с базой данных установлено");

            String sql = "INSERT INTO coaches (nickname) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nickname);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Новая запись добавлена успешно");
            } else {
                System.out.println("Ошибка при добавлении новой записи");
            }

            connection.close();
            System.out.println("Соединение с базой данных закрыто");
        } catch (SQLException e) {
            System.out.println("Ошибка при работе с базой данных: " + e.getMessage());
        }
    }
    public void insertCoach_info(String info) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Соединение с базой данных установлено");

            String sql = "INSERT INTO coaches (tg_id) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, info);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Новая запись добавлена успешно");
            } else {
                System.out.println("Ошибка при добавлении новой записи");
            }

            connection.close();
            System.out.println("Соединение с базой данных закрыто");
        } catch (SQLException e) {
            System.out.println("Ошибка при работе с базой данных: " + e.getMessage());
        }
    }


}