package dev.williamchung.repositories;

import dev.williamchung.models.User;
import dev.williamchung.utils.ConnectionUtil;
import dev.williamchung.utils.PostgresConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * UserRepository is an extension of the AbstractRepository and implements Repository interface.
 * each method is explained below.
 */
public class UserRepository extends AbstractRepository implements Repository<User, Integer> {
    /**
     * We connect to the database with the static connectionUtil, then query the database for
     * a row in the User table where username column matches the given string.
     * @param username
     * is passed in from the service.
     * @return
     * returns a User object instantiated with the query ResultSet.
     */
    public User findByUsername(String username){
        Connection connection = null;
        User user = null;
        try {
            connection = connectionUtil.getConnection();
            String sqlQuery = "SELECT * FROM forum.users WHERE username = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }

        }
        return user;
    }

    /**
     * We query the database for a User by an id given by the service.
     * @param id
     * is passed in from the service.
     * @return
     * returns a User object instantiated from the ResultSet.
     */
    @Override
    public User findById(Integer id){
        Connection connection = null;
        User user = null;
        try {
            connection = connectionUtil.getConnection();
            String sqlQuery = "SELECT * FROM forum.users WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
        return user;
    }

    /**
     * We query the database and insert a new row into the Users table.
     * @param user
     * is passed in by a Service.
     * We read each property of the user with Getters and write those values into the preparedStatement's query.
     * @return
     * return the saved user object after we set the id of the user that the database generated.
     */
    @Override
    public User save(User user) {
        Connection connection = null;
        try {
            connection = connectionUtil.getConnection();
            String sqlQuery = "INSERT INTO forum.users (username, password) VALUES (?, ?) RETURNING id;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
        return user;
    }


    /**
     * Below methods not given override implementation because not used
     */
    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User update(User obj) {
        return null;
    }

    @Override
    public void delete(User obj) {

    }
}
