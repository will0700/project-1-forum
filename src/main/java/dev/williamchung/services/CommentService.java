package dev.williamchung.services;

import dev.williamchung.models.Comment;
import dev.williamchung.repositories.CommentRepository;

import java.util.List;

public class CommentService {
    private CommentRepository commentRepository = new CommentRepository();
    public List<Comment> getCommentsByThread(Integer threadId) {
        return commentRepository.findByComment(threadId);
    }
}
