package net.javaguides.todoapp.model;

import java.io.Serializable;


public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id; // Add the id field

    private String firstName; 
    private String lastName;
    private String username;
    private String password;
   
	public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
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