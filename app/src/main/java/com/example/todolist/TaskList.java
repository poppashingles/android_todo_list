package com.example.todolist;

import java.util.ArrayList;

/**
 * Created by user on 17/11/2017
 */

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    public ArrayList<Task> getList() {
        return taskList;
    }


}
