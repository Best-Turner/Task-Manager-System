package org.example.storage;

import org.example.db.ConnectionManager;
import org.example.model.Task;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskRepository {
    private final ConnectionManager connectionManager;
    private final List<Task> taskList;
    private String sql;

    public TaskRepository(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        this.taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        sql = "INSERT INTO tasks (title, description, date_time_creation) VALUES(?,?,?);";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, task.getName());
            preparedStatement.setString(2, task.getDescription());
            Timestamp timestamp = Timestamp.valueOf(task.getDateTimeCreation());
            preparedStatement.setTimestamp(3, timestamp);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Task> getTaskById(int id) {
        Task task = null;
        sql = "SELECT * FROM tasks WHERE id = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int taskIdFromDb = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                Timestamp dateTimeCreation = resultSet.getTimestamp("date_time_creation");
                LocalDateTime localDateTime = dateTimeCreation.toLocalDateTime();
                task = new Task(taskIdFromDb, title, description, localDateTime);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //return Optional.of(task);
        return Optional.ofNullable(task);
    }

    public void deleteTaskById(int id) {
        sql = "DELETE FROM tasks WHERE id=?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getSizeTasks() {
        return taskList.size();
    }

    public List<Task> allTasks() {
        List<Task> tasks = new ArrayList<>();
        sql = "select * from tasks;";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int taskIdFromDb = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                Timestamp dateTimeCreation = resultSet.getTimestamp("date_time_creation");
                LocalDateTime localDateTime = dateTimeCreation.toLocalDateTime();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                localDateTime.format(formatter);
                tasks.add(new Task(taskIdFromDb, title, description, localDateTime));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return tasks;
    }
}
