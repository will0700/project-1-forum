package dev.williamchung.repositories;

import dev.williamchung.models.Forum;
import dev.williamchung.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ForumRepository extends AbstractRepository implements Repository<Forum, Integer> {

    @Override
    public Forum findById(Integer integer) {
        return null;
    }

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
