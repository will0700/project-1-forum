package dev.williamchung.repositories;

import dev.williamchung.models.Thread;
import dev.williamchung.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * ThreadRepository is an extension of the AbstractRepository and implements Repository interface.
 * each method is explained below.
 */
public class ThreadRepository extends AbstractRepository implements Repository<Thread, Integer> {
    /**
     * Queries the database for all the Thread objects with foreign key for one Forum.
     * @param forumId
     * is passed in by the Service.
     * @return
     * returns an ArrayList of all the Thread objects instantiated with the ResultSet.
     */
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

    /**
     * Query the database for one Thread by id.
     * @param id
     * is passed in by the service.
     * @return
     * returns one Thread instantiated from the ResultSet.
     */
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

    /**
     * We query the database and insert a new row into the Users table.
     * @param thread
     * is passed in by the Service.
     * We read each property of the thread with Getters and write those values into the preparedStatement's query.
     * @return
     * return the saved thread object after we set the id of the thread that the database generated.
     */
    @Override
    public Thread save(Thread thread) {
        Connection connection = null;
        try {
            connection = connectionUtil.getConnection();
            String sqlQuery = "INSERT INTO forum.threads (threadtitle, threadcontent, author_Id, forum_Id) VALUES (?, ?, ?, ?) RETURNING id;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, thread.getTitle());
            preparedStatement.setString(2, thread.getContent());
            preparedStatement.setInt(3, thread.getAuthorId());
            preparedStatement.setInt(4, thread.getForumId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                thread.setId(resultSet.getInt("id"));
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

    /**
     * We delete a row by thread('s id)
     * @param thread
     * passed in by the service
     */
    @Override
    public void delete(Thread thread) {
        Connection connection = null;
        try {
            connection = connectionUtil.getConnection();
            String sqlQuery1 = "DELETE FROM forum.comments WHERE thread_id = ?;" ;
            PreparedStatement preparedStatement1 = connection.prepareStatement(sqlQuery1);
            preparedStatement1.setInt(1, thread.getId());
            preparedStatement1.executeUpdate();
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
        try {
            connection = connectionUtil.getConnection();
            String sqlQuery2 = "DELETE FROM forum.threads WHERE id = ? ;";
            PreparedStatement preparedStatement2 = connection.prepareStatement((sqlQuery2));
            preparedStatement2.setInt(1, thread.getId());
            preparedStatement2.executeUpdate();
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
    }

    /**Below methods not overridden because unused
     *
     */
    @Override
    public List<Thread> findAll() {
        return null;
    }

    @Override
    public Thread update(Thread obj) {
        return null;
    }
}
