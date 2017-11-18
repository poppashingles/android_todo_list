package com.example.todolist;

import java.util.ArrayList;

/**
 * Created by user on 17/11/2017
 */

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<Task>();

        taskList.add((new Task(1, "Shopping", "Shopping", false)));
        taskList.add((new Task(1, "Stuff", "Stuff", true)));
        taskList.add((new Task(1, "Things", "Things", false)));
        taskList.add((new Task(1, "Roustabouting", "Rousting about", true)));
    }

    public ArrayList<Task> getList() {
        return taskList;
    }

}
