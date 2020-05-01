package dev.williamchung.repositories;

import dev.williamchung.models.Thread;
import dev.williamchung.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ThreadRepository extends AbstractRepository implements Repository<Thread, Integer> {
    public List<Thread> findAllByForum(Integer forumId) {
        Connection connection = null;
        List<Thread> threads = new ArrayList<Thread>();
        try {
            connection = connectionUtil.getConnection();
            String sqlQuery = "SELECT * FROM forum.threads WHERE forum_id = ? ;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, forumId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Thread tempThread = new Thread();
                tempThread.setId(resultSet.getInt("id"));
                tempThread.setContent(resultSet.getString("threadcontent"));
                tempThread.setTitle(resultSet.getString("threadtitle"));
                tempThread.setAuthorId(resultSet.getInt("author_id"));
                tempThread.setForumId(resultSet.getInt("forum_id"));
                threads.add(tempThread);
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
        return threads;
    }

    @Override
    public Thread findById(Integer id) {
        Connection connection = null;
        Thread thread = null;
        try {
            connection = connectionUtil.getConnection();
            String sqlQuery = "SELECT * FROM forum.threads WHERE id = ? ;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                thread = new Thread();
                thread.setId(resultSet.getInt("id"));
                thread.setTitle(resultSet.getString("threadtitle"));
                thread.setContent(resultSet.getString("threadcontent"));
                thread.setAuthorId(resultSet.getInt("author_id"));
                thread.setForumId(resultSet.getInt("forum_id"));
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
        return thread;
    }


    //Override methods not implemented because unused
    @Override
    public List<Thread> findAll() {
        return null;
    }

    @Override
    public Thread save(Thread obj) {
        return null;
    }

    @Override
    public Thread update(Thread obj) {
        return null;
    }

    @Override
    public void delete(Thread obj) {

    }
}
