package dev.williamchung.repositories;

import dev.williamchung.models.Comment;
import dev.williamchung.models.Thread;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentRepository extends AbstractRepository implements Repository<Comment, Integer> {
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






    @Override
    public Comment findById(Integer integer) {
        return null;
    }

    @Override
    public List<Comment> findAll() {
        return null;
    }

    @Override
    public Comment save(Comment obj) {
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
