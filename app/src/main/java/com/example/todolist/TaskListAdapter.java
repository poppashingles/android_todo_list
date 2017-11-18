package com.example.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 17/11/2017
 */

public class TaskListAdapter extends ArrayAdapter<Task> {

    public TaskListAdapter(Context context, ArrayList<Task> tasks) {
        super(context, 0, tasks);
    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent) {

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.task_item, parent, false);
        }

        Task currentTaskItem = getItem(position);

        TextView title = listItemView.findViewById(R.id.title_textview);

        title.setText(currentTaskItem.getTitle().toString());


        CheckBox checkbox = listItemView.findViewById(R.id.completed_checkbox);

        checkbox.setChecked(currentTaskItem.getCompleted());


        listItemView.setTag(currentTaskItem);

        return listItemView;

    }

}
