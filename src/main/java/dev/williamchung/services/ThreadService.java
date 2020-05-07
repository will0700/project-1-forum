package dev.williamchung.services;

import dev.williamchung.models.User;
import dev.williamchung.repositories.ThreadRepository;
import dev.williamchung.models.Thread;

import java.util.List;

public class ThreadService {
    private final ThreadRepository threadRepository = new ThreadRepository();
    public List<Thread> getThreadsByForum(Integer forumId) {
        return threadRepository.findAllByForum(forumId);
    }
    public Thread getThreadById(String threadId) {
        Integer id = Integer.parseInt(threadId);
        return threadRepository.findById(id);
    }
    public Thread postThread(String threadTitle, String threadContent, Integer authorId, String forumId){
        Integer forumIdInteger = Integer.parseInt(forumId);
        Thread thread = new Thread(threadTitle, threadContent, authorId, forumIdInteger);
        return threadRepository.save(thread);
    }

    public void deleteThreadById(String threadId, User user) {
        Thread thread = getThreadById(threadId);
        if (thread.getAuthorId() == user.getId()) {
            threadRepository.delete(thread);
        }
    }
}
