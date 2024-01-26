package net.javaguides.todoapp.model;

import java.io.Serializable;


public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id; // Add the id field

    private String first_name; 
    private String last_name;
    private String username;
    private String password;
   
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
	public void setId(long id) {
		this.id = id;
	}
	public void setIdAndUsername(long id2, String username2) {
		// TODO Auto-generated method stub
		
	}
	
}