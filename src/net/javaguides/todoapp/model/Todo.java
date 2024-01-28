package net.javaguides.todoapp.model;

import java.time.LocalDate;

public class Todo {
    private Long id;
    private String title;
    private String description;
    private LocalDate targetDate;
    private String status;
    private int user_id;

    protected Todo() {

    }

    public Todo(Long id, String title, String description, LocalDate targetDate, String status, int user_id) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.targetDate = targetDate;
        this.status = status;
        this.user_id = user_id;
    }

    public Todo(String title, String description, LocalDate targetDate, String status, int user_id) {
        super();
        this.title = title;
        this.description = description;
        this.targetDate = targetDate;
        this.status = status;
        this.user_id = user_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public int getUserId() {
    	return user_id;
    }
    public void setUserId(int user_id) {
    	this.user_id = user_id;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Todo other = (Todo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
