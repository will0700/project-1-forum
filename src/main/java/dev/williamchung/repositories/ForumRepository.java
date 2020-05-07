package dev.williamchung.repositories;

import dev.williamchung.models.Forum;
import dev.williamchung.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * ForumRepository is an extension of the AbstractRepository and implements Repository interface.
 * each method is explained below.
 */
public class ForumRepository extends AbstractRepository implements Repository<Forum, Integer> {
    /**
     * We query the database for a Forum by id.
     * @param integer
     * is passed in by the Service.
     * @return
     * returns a Forum object instantiated with data from the ResultSet.
     */
    @Override
    public Forum findById(Integer integer) {
        Connection connection = null;
        Forum forum = null;
        try {
            connection = connectionUtil.getConnection();
            String sqlQuery = "SELECT * FROM forum.forums where id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, integer);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                forum = new Forum();
                forum.setId(resultSet.getInt("id"));
                forum.setForumName((resultSet.getString("forumname")));
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
        return forum;
    }

    /**
     * We query the database for all the Forum objects.
     * We create an ArrayList and add into it Forum objects instantiated from the ResultSet.
     * @return
     * We return the ArrayList of Forum objects.
     */
    @Override
    public List<Forum> findAll() {
        Connection connection = null;
        List<Forum> forums = new ArrayList<Forum>();
        try {
            connection = connectionUtil.getConnection();
            String sqlQuery = "SELECT * FROM forum.forums;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Forum tempForum = new Forum();
                tempForum.setForumName(resultSet.getString("forumname"));
                tempForum.setId(resultSet.getInt("id"));
                forums.add(tempForum);
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
        return forums;
    }

    /**
     * Below methods are not overriden or implemented.
     */
    @Override
    public Forum save(Forum obj) {
        return null;
    }

    @Override
    public Forum update(Forum obj) {
        return null;
    }

    @Override
    public void delete(Forum obj) {

    }
}
