package com.example.todolist;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refreshAdapter();
    }

    //  Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_add_new) {
            Intent intent = new Intent(this, AddNewActivity.class);
            startActivity(intent);
        }

        return true;
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
//        ArrayList<Task> tasks = new ArrayList<>();
//        final TaskListAdapter taskListAdapter = new TaskListAdapter(this, tasks);

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
                                myDb.updateTask(selectedTask.getId().toString(), titleBox.getText().toString(), descriptionBox.getText().toString());
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




}
