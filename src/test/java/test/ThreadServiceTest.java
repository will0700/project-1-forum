package test;

import dev.williamchung.models.Thread;
import dev.williamchung.models.User;
import dev.williamchung.repositories.ThreadRepository;
import dev.williamchung.services.ThreadService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ThreadServiceTest {
    @Mock
    private ThreadRepository threadRepository;

    private ThreadService threadService;

    private List<Thread> threads;
    private Thread thread;

    @Before
    public void init(){
        threadService = new ThreadService();
        threadService.setThreadRepository(threadRepository);
        threads = new ArrayList<>();
        thread = new Thread();
        thread.setId(1);
        thread.setForumId(1);
        threads.add(thread);
    }

    @Test
    public void getThreadsByForumTest(){
        when(threadRepository.findAllByForum(1)).thenReturn(threads);
        assertEquals(threadService.getThreadsByForum(1), threads);
    }

    @Test
    public void getThreadsByIdTest(){
        when(threadRepository.findById(1)).thenReturn(thread);
        assertEquals(threadService.getThreadById("1"), thread);
    }

    @Test
    public void postThreadTest(){
        assertEquals(threads.size(),1);
        Answer<Thread> answer = new Answer<Thread>() {
            public Thread answer(InvocationOnMock invocation) throws Throwable {
                Thread thread= new Thread();
                 threads.add(thread);
                 return thread;
            }
        };
        when(threadRepository.save(any(Thread.class))).then(answer);
        threadService.postThread("Title","Content",1,"1");
        assertEquals(threads.size(),2);
    }

}
