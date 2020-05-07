package dev.williamchung.test;

import dev.williamchung.models.Forum;
import dev.williamchung.repositories.ForumRepository;
import dev.williamchung.services.ForumService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * This is the JUnit test class for ForumService.
 */
@RunWith(MockitoJUnitRunner.class)
public class ForumServiceTest {

    public static final String FORUM_NAME = "Forum Name";
    @Mock
    private ForumRepository forumRepository;

    private ForumService forumService;

    private List<Forum> forums;

    private Forum forum;
    @Before
    public void init(){
        forumService = new ForumService();
        forumService.setForumRepository(forumRepository);
        forums = new ArrayList<>();
        forum = new Forum();
        forum.setId(1);
        forum.setForumName(FORUM_NAME);
    }

    /**
     * Test getAllForums method
     */
    @Test
    public void getAllForumsTest(){
        when(forumRepository.findAll()).thenReturn(forums);
        assertEquals(forumService.getAllForums(),forums);
    }

    /**
     * Test the getForumById method
     */
    @Test
    public void getForumByIdTest(){
        when(forumRepository.findById(1)).thenReturn(forum);
        Forum resultForum = forumService.getForumById("1");
        assertEquals(resultForum.getForumName(),forum.getForumName());
        assertEquals(resultForum.getId(),forum.getId());
    }
}
