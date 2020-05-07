package dev.williamchung.services;

import dev.williamchung.models.User;
import dev.williamchung.repositories.ThreadRepository;
import dev.williamchung.models.Thread;

import java.util.List;

/**
 * This is the ThreadService class which liaises between the servlet and repository dao.
 * Each method is explained below.
 */
public class ThreadService {
    private ThreadRepository threadRepository = new ThreadRepository();

    public void setThreadRepository(ThreadRepository threadRepository) {
        this.threadRepository = threadRepository;
    }

    /**
     * Grabs the list of all Threads with the foreign key Forum id.
     * @param forumId
     * passed by Servlet.
     * @return
     * returns an ArrayList of all the Threads related to the Forum given by id.
     */
    public List<Thread> getThreadsByForum(Integer forumId) {
        return threadRepository.findAllByForum(forumId);
    }

    /**
     * Grabs a Forum by its id.
     * @param threadId
     * String passed by Servlet.
     * @return
     * returns a Thread object.
     */
    public Thread getThreadById(String threadId) {
        Integer id = Integer.parseInt(threadId);
        return threadRepository.findById(id);
    }

    /**
     * Inserts a Thread into the database.
     * @param threadTitle
     * @param threadContent
     * @param authorId
     * @param forumId
     * parameters are passed by the Servlet from the form data in the request.
     * @return
     * Returns a Thread object after the database successfully writes the row.
     */
    public Thread postThread(String threadTitle, String threadContent, Integer authorId, String forumId){
        Integer forumIdInteger = Integer.parseInt(forumId);
        Thread thread = new Thread(threadTitle, threadContent, authorId, forumIdInteger);
        return threadRepository.save(thread);
    }

    /**
     * Deletes the Thread row in the database, if the User is the author (mapped by foreign key author_id).
     * @param threadId
     * @param user
     * String id and User user provided by Servlet.
     */
    public void deleteThreadById(String threadId, User user) {
        Thread thread = getThreadById(threadId);
        if (thread.getAuthorId() == user.getId()) {
            threadRepository.delete(thread);
        }
    }
}
