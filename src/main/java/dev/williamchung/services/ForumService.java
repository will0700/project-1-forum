package dev.williamchung.services;

import dev.williamchung.models.Forum;
import dev.williamchung.repositories.ForumRepository;

import java.util.List;

public class ForumService {
    private ForumRepository forumRepository = new ForumRepository();

    public void setForumRepository(ForumRepository forumRepository) {
        this.forumRepository = forumRepository;
    }

    public List<Forum> getAllForums() {
        return forumRepository.findAll();
    }

    public Forum getForumById(String forumId) {
        Integer intId = Integer.parseInt(forumId);
        return forumRepository.findById(intId);
    }

}
