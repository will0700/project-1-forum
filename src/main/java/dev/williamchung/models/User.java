package dev.williamchung.models;

public class User {

    private Integer id;
    private String username;
    private String password;

    //Constructor
    public User() {}
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //Methods
    public String toString(){
        System.out.println("User id:" + getId() + ", email: " + getUsername());
        return "User id:" + getId() + ", email: " + getUsername();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}