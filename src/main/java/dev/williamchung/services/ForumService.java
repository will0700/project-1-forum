package dev.williamchung.services;

import dev.williamchung.models.Forum;
import dev.williamchung.repositories.ForumRepository;

import java.util.List;

public class ForumService {
    private ForumRepository forumRepository = new ForumRepository();
    public List<Forum> getAllForums(){
            return forumRepository.findAll();
        }
}
