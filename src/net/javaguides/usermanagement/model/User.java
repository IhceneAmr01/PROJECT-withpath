package net.javaguides.usermanagement.model;

public class User {
    protected int id;
    protected String first_name;
    protected String last_name;
    protected String username;

    public User() {}

    public User(String first_name, String last_name, String username) {
        super();
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
    }

    public User(int id, String first_name, String last_name, String username) {
        super();
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFirstName() {
        return first_name;
    }
    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }
    public String getLastName() {
        return last_name;
    }
    public void setLastName(String last_name) {
        this.last_name = last_name;
    }
    public String username() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}