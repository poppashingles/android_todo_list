package com.example.todolist;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refreshAdapter();
    }

    public void addNewTask(View button) {
        final TaskRepo myDb = new TaskRepo(this);

        AlertDialog newAlertDialog = new AlertDialog.Builder(MainActivity.this).create();
        newAlertDialog.setTitle("Add a new task");

        LinearLayout layout = new LinearLayout(MainActivity.this);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText titleBox = new EditText(MainActivity.this);
        titleBox.setHint("Title");
        layout.addView(titleBox);

        final EditText descriptionBox = new EditText(MainActivity.this);
        descriptionBox.setHint("Description");
        layout.addView(descriptionBox);

        newAlertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Add new task", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myDb.insertData(titleBox.getText().toString(), descriptionBox.getText().toString());
                MainActivity.this.refreshAdapter();
                dialog.dismiss();
            }
        });

        newAlertDialog.setView(layout);



        newAlertDialog.show();
    }


    private void refreshAdapter() {
        TaskRepo myDb = new TaskRepo(this);
        ArrayList<Task> tasks = myDb.getAllData();
        TaskListAdapter taskAdapter = new TaskListAdapter(this, tasks);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(taskAdapter);
    }


    public void getTask(View title) {
        final TaskRepo myDb = new TaskRepo(this);
        final Task selectedTask = (Task) title.getTag();

        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle(selectedTask.getTitle());
        alertDialog.setMessage(selectedTask.getDescription());




        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "DELETE",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        myDb.deleteTask(selectedTask.getId().toString());
                        MainActivity.this.refreshAdapter();
                        dialog.dismiss();
                    }
                });

        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "EDIT",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        final AlertDialog editAlertDialog = new AlertDialog.Builder(MainActivity.this).create();
                        editAlertDialog.setTitle("Edit " + selectedTask.getTitle());

                        LinearLayout layout = new LinearLayout(MainActivity.this);
                        layout.setOrientation(LinearLayout.VERTICAL);

                        final EditText titleBox = new EditText(MainActivity.this);
                        titleBox.setHint(selectedTask.getTitle());
                        layout.addView(titleBox);

                        final EditText descriptionBox = new EditText(MainActivity.this);
                        descriptionBox.setHint(selectedTask.getDescription());
                        layout.addView(descriptionBox);

                        editAlertDialog.setTitle("Edit");

                        editAlertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "UPDATE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                myDb.updateTask(selectedTask.getId().toString(), titleBox.getText().toString(),
                                        descriptionBox.getText().toString(), selectedTask.getCompleted());
                                MainActivity.this.refreshAdapter();
                                dialog.dismiss();
                            }
                        });

                        editAlertDialog.setView(layout);

                        editAlertDialog.show();
                    }
                });

        alertDialog.show();
    }

    public void onChecked(View view) {
        TaskRepo myDb = new TaskRepo(this);
        Task task = (Task) view.getTag();
        task.whenChecked();

        myDb.updateTask(task.getId().toString(), task.getTitle(), task.getDescription(), task.getCompleted());
    }

}
