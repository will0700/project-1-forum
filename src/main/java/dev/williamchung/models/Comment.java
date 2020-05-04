package dev.williamchung.models;


public class Comment {

    private Integer id;

    private String comment;

    private Integer authorId;

    private Integer threadId;

    public Comment(){}
    public Comment(String commentContent, Integer authorId, Integer threadId){
        this.comment = commentContent;
        this.authorId = authorId;
        this.threadId = threadId;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getThreadId() {
        return threadId;
    }

    public void setThreadId(Integer threadId) {
        this.threadId = threadId;
    }

}