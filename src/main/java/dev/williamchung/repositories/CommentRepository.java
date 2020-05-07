package dev.williamchung.repositories;

import dev.williamchung.models.Comment;
import dev.williamchung.models.Thread;
import dev.williamchung.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The CommentRepository accesses the Comments table in the database.
 * each method is explained below.
 */
public class CommentRepository extends AbstractRepository implements Repository<Comment, Integer> {
    /**
     * We query the database for a list of all the Comments by foreign key Thread id.
     * @param id
     * the Thread's id is passed in by the service.
     * @return
     * Return an ArrayList of all the Comments with foreign key matching the given Thread id.
     */
    public List<Comment> findByComment(Integer id) {
        Connection connection = null;
        List<Comment> comments = new ArrayList<Comment>();
        try {
            connection = connectionUtil.getConnection();
            String sqlQuery = "SELECT * FROM forum.comments WHERE thread_id = ? ;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            Comment tempComment = new Comment();
            tempComment.setId(resultSet.getInt("id"));
            tempComment.setComment(resultSet.getString("commentcontent"));
            tempComment.setAuthorId(resultSet.getInt("author_id"));
            tempComment.setThreadId(resultSet.getInt("thread_id"));
            comments.add(tempComment);
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
        return comments;
    }

    /**
     * We save a Comment object in the database
     * @param comment
     * a Comment object is passed in by the service. We Insert into the database Values pulled out by Getters.
     * @return
     * Return the saved Comment object, with its id updated with the database genereated id.
     */
    @Override
    public Comment save(Comment comment) {
        Connection connection = null;
        try {
            connection = connectionUtil.getConnection();
            String sqlQuery = "INSERT into forum.comments (commentcontent, author_Id, thread_Id) VALUES (?, ?, ?) RETURNING id;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, comment.getComment());
            preparedStatement.setInt(2, comment.getAuthorId());
            preparedStatement.setInt(3, comment.getThreadId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                comment.setId(resultSet.getInt("id"));
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
        return comment;
    }

    /**
     * Below methods are not overriden.
     */
    @Override
    public Comment findById(Integer integer) {
        return null;
    }

    @Override
    public List<Comment> findAll() {
        return null;
    }

    @Override
    public Comment update(Comment obj) {
        return null;
    }

    @Override
    public void delete(Comment obj) {

    }
}
