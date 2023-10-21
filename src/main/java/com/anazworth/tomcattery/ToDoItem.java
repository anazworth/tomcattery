package com.anazworth.tomcattery;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TODO_ITEMS")
public class ToDoItem {

    @Id
    private Long id;
    private String task;
    private Boolean completed;
    private String dateCreated;
    private String dateCompleted;

    public ToDoItem() {
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(String dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    @Override
    public String toString() {
        return "ToDoItem{" +
                "id=" + id +
                ", task='" + task + '\'' +
                ", completed=" + completed +
                ", dateCreated='" + dateCreated + '\'' +
                ", dateCompleted='" + dateCompleted + '\'' +
                '}';
    }
}
