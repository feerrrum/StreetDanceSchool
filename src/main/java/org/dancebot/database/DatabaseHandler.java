package org.dancebot.database;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {
    private static List<List<String>> coaches = null;

    private final Statement stmt;
    private static DatabaseHandler instance = null;

    private DatabaseHandler() throws IOException, SQLException {
        var data = Files.readAllLines(new File("C:\\Users\\userName\\dbData.txt").toPath());
        var url = data.get(0);
        var user = data.get(1);
        var password = data.get(2);
        Connection conn = DriverManager.getConnection(url, user, password);
        this.stmt = conn.createStatement();
        System.out.println("Connected successfully");
    }

    public static DatabaseHandler getInstance() throws SQLException, IOException {
        if (instance == null) {
            instance = new DatabaseHandler();
        }
        return instance;
    }

    public boolean isOnRecord(String id) throws SQLException {
        var exist = stmt.executeQuery("SELECT EXISTS" +
                "(SELECT * FROM users WHERE tg_id=" + id + ")");
        exist.next();
        return exist.getInt(1) != 0;
    }
    public boolean hasThatCoach(String userId, String coachId) throws SQLException {
        var exist = stmt.executeQuery("SELECT EXISTS" +
                "(SELECT * FROM users WHERE tg_id=" + userId + " AND coach_id=" + coachId + ")");
        exist.next();
        return exist.getInt(1) != 0;
    }

    public void addCoach(String userId, String coachId) throws SQLException {

        stmt.executeUpdate("INSERT INTO users (tg_id, coach_id) VALUES (" + userId + "," +  coachId + ")");
    }

    public void deleteCoach(String userId, String coachId) throws SQLException {

        stmt.executeUpdate("DELETE FROM users WHERE tg_id=" + userId + " AND coach_id=" + coachId);
    }

    private List<List<String>> getCoaches() throws SQLException {
        if (coaches == null) {
            coaches = new ArrayList<>();
            var rs = stmt.executeQuery("SELECT * FROM coaches");
            while (rs.next()) {
                List<String> coach = List.of(
                        rs.getString("nick"),
                        rs.getString("info"),
                        rs.getString("sched"));

                coaches.add(coach);
            }
        }
        return coaches;
    }
    public List<String> getCards() throws SQLException {
        List<String> cards = new ArrayList<>();
        int i = 1;
        for (List<String> couch : getCoaches()) {
            cards.add(i +". " + couch.get(0) + "\n\nИнфа:\n\n" +
                    couch.get(1) + "\n\nРасписание:\n\n" +
                    couch.get(2));
            i++;
        }
        return cards;
    }
    public List<String> getNicks() throws SQLException {
        List<String> nicks = new ArrayList<>();
        int i = 1;
        for (List<String> couch : getCoaches()) {
            nicks.add(i + ". " + couch.get(0));
            i++;
        }
        return nicks;
    }
    public List<String> getSchedule(String userId) throws SQLException {
        List<String> schedule = new ArrayList<>();
        var rs = stmt.executeQuery("SELECT * FROM users WHERE tg_id=" + userId);
        getCoaches();
        while (rs.next()) {
            var index = Integer.parseInt(rs.getString("coach_id")) - 1;
            schedule.add(coaches.get(index).get(0) + ":\n\n" + coaches.get(index).get(2));
        }
        return schedule;
    }
}
