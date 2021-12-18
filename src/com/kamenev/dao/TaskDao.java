package com.kamenev.dao;

import com.kamenev.entity.Status;
import com.kamenev.entity.Task;
import com.kamenev.exception.DaoException;
import com.kamenev.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskDao implements Dao<Integer, Task> {

    private static TaskDao INSTANCE = new TaskDao();
    private static TaskListDao taskListDao = TaskListDao.getInstance();
    private static String SAVE_SQL = "INSERT INTO task (task_list_id, description, status)\n" +
            "VALUES (?, ?, ?)";
    private static String FIND_ALL_SQL = "SELECT id, task_list_id, description, status FROM task";
    private static String FIND_BY_ID_SQL = FIND_ALL_SQL + " WHERE id = ?";
    private static String UPDATE_SQL = "UPDATE task SET\n" +
            "task_list_id = ?,\n" +
            "description = ?,\n" +
            "status = ?\n" +
            "WHERE id = ?";
    private static String DELETE_SQL = "DELETE FROM task WHERE id = ?";

    private TaskDao() {

    }

    @Override
    public Task save(Task task) {
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, task.getTaskList().getId());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setString(3, task.getStatus().toString());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                task.setId(resultSet.getLong("id"));
            }
            return task;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public Optional<Task> findById(Integer id) {
        try (Connection connection = ConnectionManager.open();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            Task task = null;
            if (resultSet.next()) {
                task = buildTask(resultSet);
            }
            return Optional.ofNullable(task);
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public void update(Task task) {
        try (Connection connection = ConnectionManager.open();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setLong(1, task.getTaskList().getId());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setString(3, task.getStatus().toString());
            preparedStatement.setLong(4, task.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public boolean delete(Integer id) {
        try (Connection connection = ConnectionManager.open();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate() > 1;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public List<Task> findAll() {
        try (Connection connection = ConnectionManager.open();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Task> tasks = new ArrayList<>();
            while (resultSet.next()) {
                tasks.add(buildTask(resultSet));
            }
            return tasks;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public static TaskDao getInstance() {
        return INSTANCE;
    }

    private Task buildTask(ResultSet resultSet) throws SQLException {
        return new Task(
                resultSet.getLong("id"),
                taskListDao.findById(resultSet.getInt("task_list_id"),
                        resultSet.getStatement().getConnection()).orElse(null),
                resultSet.getString("description"),
                Status.valueOf(resultSet.getString("status"))
        );
    }
}