package test;

import dev.williamchung.models.Comment;
import dev.williamchung.models.User;
import dev.williamchung.repositories.CommentRepository;
import dev.williamchung.services.CommentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceTest {

    @Mock
    private User user;
    @Mock
    private Comment mockedComment;
    @Mock
    private CommentRepository commentRepository;
    private CommentService commentService;

    private List<Comment> comments;


    @Before
    public void init(){
        commentService = new CommentService();
        commentService.setCommentRepository(commentRepository);
        when(user.getId()).thenReturn(1);

        comments = new ArrayList<>();
        comments.add(new Comment());
    }

    @Test
    public void testGetCommentsByThread(){
        when(commentRepository.findByComment(1)).thenReturn(comments);
        assertEquals(commentService.getCommentsByThread(1).size(),1);
    }

    @Test
    public void testPostComment(){
        Answer<Comment> answer = new Answer<Comment>() {
            public Comment answer(InvocationOnMock invocation) throws Throwable {
                comments.add(mockedComment);
                return mockedComment;
            }
        };
        when(commentRepository.save(any(Comment.class))).then(answer);

        when(commentRepository.findByComment(1)).thenReturn(comments);
        commentService.postComment("Loren epsum",user,"1");
        assertEquals(commentService.getCommentsByThread(1).size(),2);
    }
}
