package com.kamenev.dao;

import com.kamenev.entity.TaskList;
import com.kamenev.entity.TypeTaskList;
import com.kamenev.exception.DaoException;
import com.kamenev.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskListDao implements Dao<Integer, TaskList> {

    private static TaskListDao INSTANCE = new TaskListDao();
    private static UserDao userDao = UserDao.getInstance();
    private static final String SAVE_SQL = "INSERT INTO task_list\n" +
            "(type, date_creation, deadline, user_id)\n" +
            "VALUES (?, ?, ?, ?)";
    private static final String FIND_ALL_SQL = "SELECT id, type, date_creation, deadline, user_id FROM task_list";
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + " WHERE id = ?";
    private static final String UPDATE_SQL = "UPDATE task_list SET\n" +
            "type = ?,\n" +
            "date_creation = ?,\n" +
            "deadline = ?,\n" +
            "user_id = ?\n" +
            "WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM task_list WHERE id = ?";

    private TaskListDao() {

    }

    @Override
    public TaskList save(TaskList taskList) {
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, taskList.getType().toString());
            preparedStatement.setObject(2, taskList.getDateCreation());
            preparedStatement.setObject(3, taskList.getDeadline());
            preparedStatement.setInt(4, taskList.getUser().getId());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                taskList.setId(resultSet.getInt("id"));
            }
            return taskList;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public Optional<TaskList> findById(Integer id) {
        try (Connection connection = ConnectionManager.open();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            TaskList taskList = null;
            if (resultSet.next()) {
                taskList = buildTaskList(resultSet);
            }
            return Optional.ofNullable(taskList);
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public void update(TaskList taskList) {
        try (Connection connection = ConnectionManager.open();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, taskList.getType().toString());
            preparedStatement.setObject(2, taskList.getDateCreation());
            preparedStatement.setObject(3, taskList.getDeadline());
            preparedStatement.setInt(4, taskList.getUser().getId());
            preparedStatement.setInt(5, taskList.getId());

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
    public List<TaskList> findAll() {
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<TaskList> taskLists = new ArrayList<>();
            while (resultSet.next()) {
                taskLists.add(buildTaskList(resultSet));
            }
            return taskLists;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public static TaskListDao getInstance() {
        return INSTANCE;
    }

    private TaskList buildTaskList(ResultSet resultSet) throws SQLException {
        Timestamp dateCreation = resultSet.getTimestamp("date_creation");
        Timestamp deadline = resultSet.getTimestamp("deadline");
        return new TaskList(
                resultSet.getInt("id"),
                TypeTaskList.valueOf(resultSet.getString("type")),
                dateCreation != null ? dateCreation.toLocalDateTime() : null,
                deadline != null ? deadline.toLocalDateTime() : null,
                userDao.findById(resultSet.getInt("user_id"),
                        resultSet.getStatement().getConnection()).orElse(null)
        );
    }
}