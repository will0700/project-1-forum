package dev.williamchung.models;


public class Thread {

    private Integer id;

    private String title;

    private String content;

    private Integer authorId;

    private Integer forumId;


    //Constructors
    public Thread(){};
    public Thread(String title, String content, Integer authorId, Integer forumId){
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.forumId = forumId;
    }


    //Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getForumId() {
        return forumId;
    }

    public void setForumId(Integer forumId) {
        this.forumId = forumId;
    }

}