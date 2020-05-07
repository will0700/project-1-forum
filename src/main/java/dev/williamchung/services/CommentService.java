package dev.williamchung.services;

import dev.williamchung.models.Comment;
import dev.williamchung.models.User;
import dev.williamchung.repositories.CommentRepository;

import java.util.List;

public class CommentService {
    private CommentRepository commentRepository = new CommentRepository();

    public void setCommentRepository(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getCommentsByThread(Integer threadId) {
        return commentRepository.findByComment(threadId);
    }

    public Comment postComment(String commentContent, User author, String threadId){
        Integer threadIdInteger = Integer.parseInt(threadId);
        Comment comment = new Comment(commentContent, author.getId(), threadIdInteger);
        return commentRepository.save(comment);
    }


}
