package com.example.todolist;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import java.io.Serializable;

/**
 * Created by user on 17/11/2017
 */

public class Task implements Serializable {

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
