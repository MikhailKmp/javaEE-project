package com.kamenev.dao;

import com.kamenev.entity.Gender;
import com.kamenev.entity.Role;
import com.kamenev.entity.User;
import com.kamenev.exception.DaoException;
import com.kamenev.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<Integer, User> {

    private static final UserDao INSTANCE = new UserDao();
    private static final String SAVE_SQL = "INSERT INTO users\n" +
            "(first_name, last_name, role, password, image, email, gender, date_of_birth)\n" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_ALL_SQL = "SELECT id, first_name, last_name, role, password,\n" +
            "image, email, gender, date_of_birth FROM users";
    private static String FIND_BY_ID_SQL = FIND_ALL_SQL + " WHERE id = ?";
    private static String UPDATE_SQL = "UPDATE users SET\n" +
            "first_name = ?,\n" +
            "last_name = ?,\n" +
            "role = ?,\n" +
            "password = ?,\n" +
            "image = ?,\n" +
            "email = ?,\n" +
            "gender = ?,\n" +
            "date_of_birth = ?\n" +
            "WHERE id = ?";
    private static String DELETE_SQL = "DELETE FROM users WHERE id = ?";

    private UserDao() {

    }

    @Override
    public User save(User user) {
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getRole().toString());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getImage());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setString(7, user.getGender().toString());
            preparedStatement.setObject(8, user.getDateOfBirth());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt("id"));
            }
            return user;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public Optional<User> findById(Integer id) {
        try (Connection connection = ConnectionManager.open();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            User user = null;
            if (resultSet.next()) {
                user = buildUser(resultSet);
            }
            return Optional.ofNullable(user);
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    @Override
    public void update(User user) {
        try (Connection connection = ConnectionManager.open();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getRole().toString());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getImage());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setString(7, user.getGender().toString());
            preparedStatement.setObject(8, user.getDateOfBirth());
            preparedStatement.setInt(9, user.getId());

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

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }

    }

    @Override
    public List<User> findAll() {
        try (Connection connection = ConnectionManager.open();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(buildUser(resultSet));
            }
            return users;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        Timestamp dateOfBirth = resultSet.getTimestamp("date_of_birth");
        return new User(
                resultSet.getInt("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("email"),
                resultSet.getString("password"),
                resultSet.getString("image"),
                dateOfBirth != null ? dateOfBirth.toLocalDateTime() : null,
                Role.valueOf(resultSet.getString("role")),
                Gender.valueOf(resultSet.getString("gender"))
        );
    }
}