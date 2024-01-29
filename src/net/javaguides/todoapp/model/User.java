package net.javaguides.todoapp.model;

import java.io.Serializable;


public class User implements Serializable {
	private static final long serialVersionUID = 1L;
    private int id; // Change the data type to int

    private String first_name; 
    private String last_name;
    private String username;
    private String password;
    private String role;
    private int score;

    public User() {
    }
    
    public User(int id, String first_name, String last_name, String username, String role, int score ) {
    	super();
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.role = role;
        this.score = score;
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
	public long getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
}