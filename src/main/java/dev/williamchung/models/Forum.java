package dev.williamchung.models;

/**
 * This is the Forum model. Forums have a forum name.
 * This class contains the properties, getters, and setters.
 */
public class Forum {

    private Integer id;

    private String forumName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getForumName() {
        return forumName;
    }

    public void setForumName(String forumName) {
        this.forumName = forumName;
    }

}