package server.models.services;

import server.Logger;
import server.DatabaseConnection;
import server.models.Topic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TopicService {

    public static String selectAllInto(List<Topic> targetList) {
        targetList.clear();
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT Topic, TopicDescription FROM Topics"
            );
            if (statement != null) {
                ResultSet results = statement.executeQuery();
                if (results != null) {
                    while (results.next()) {
                        targetList.add(new Topic(results.getString("Topic"), results.getString("TopicDescription")));


                    }
                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select all from 'Topics' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
        return "OK";
    }

    public static Topic selectById(int id) {
        Topic result = null;
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT Topic, TopicDescription FROM Topics WHERE Topic = ?"
            );
            if (statement != null) {
                statement.setInt(1, id);
                ResultSet results = statement.executeQuery();
                if (results != null && results.next()) {
                    result = new Topic(results.getString("Topic"), results.getString("TopicDescription"));


                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select by id from 'Topics' table: " + resultsException.getMessage();

            Logger.log(error);
        }
        return result;
    }

    public static String insert(Topic itemToSave) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "INSERT INTO Topics (Topic, TopicDescription) VALUES (?, ?)"
            );
            statement.setString(1, itemToSave.getTopic());
            statement.setString(2, itemToSave.getTopicDescription());








            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't insert into 'Topics' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
    }

    public static String update(Topic itemToSave) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "UPDATE Topics SET TopicDescription = ? WHERE Topic = ?"
            );
            statement.setString(1, itemToSave.getTopicDescription());








            statement.setString(2, itemToSave.getTopic());
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't update 'Topics' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
    }

    public static String deleteById(int id) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "DELETE FROM Topics WHERE Topic = ?"
            );
            statement.setInt(1, id);
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't delete by id from 'Topics' table: " + resultsException.getMessage();

            Logger.log(error);
            return error;
        }
    }

}