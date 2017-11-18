package com.example.todolist;

/**
 * Created by user on 17/11/2017
 */

public class Task {

    private Integer id;
    private String title;
    private String description;
    private Boolean completed;

    public Task(Integer id, String title, String description, Boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getCompleted() {
        return completed;
    }
}
