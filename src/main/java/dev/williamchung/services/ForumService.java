package dev.williamchung.services;

import dev.williamchung.models.Forum;
import dev.williamchung.repositories.ForumRepository;

import java.util.List;

/**
 * This is the ForumService class which liaises between the servlet and repository dao.
 * Each method is explained below.
 */
public class ForumService {
    private ForumRepository forumRepository = new ForumRepository();

    public void setForumRepository(ForumRepository forumRepository) {
        this.forumRepository = forumRepository;
    }

    /**
     * Queries the database for a List of all the Forums.
     * @return
     */
    public List<Forum> getAllForums() {
        return forumRepository.findAll();
    }

    /**
     * Queries the database for a Forum by id.
     * @param forumId
     * passed by the Servlet
     * @return
     * returns a Forum object.
     */
    public Forum getForumById(String forumId) {
        Integer intId = Integer.parseInt(forumId);
        return forumRepository.findById(intId);
    }

}
