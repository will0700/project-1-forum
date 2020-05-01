package dev.williamchung.services;

import dev.williamchung.repositories.ThreadRepository;
import dev.williamchung.models.Thread;

import java.util.List;

public class ThreadService {
    private ThreadRepository threadRepository = new ThreadRepository();
    public List<Thread> getThreadsByForum(Integer forumId) {
        return threadRepository.findAllByForum(forumId);
    }
    public Thread getThreadById(String threadId) {
        Integer id = Integer.parseInt(threadId);
        return threadRepository.findById(id);
    }
}
