package com.example.todolist;

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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TaskRepo myDb = new TaskRepo(this);


//      Listview
//      TaskList taskList = new TaskList();
        ArrayList<Task> tasks = myDb.getAllData();
        TaskListAdapter taskAdapter = new TaskListAdapter(this, tasks);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(taskAdapter);

//        TaskList taskList = new TaskList();
//        ArrayList<Task> tasks = taskList.getList();
//        TaskListAdapter taskAdapter = new TaskListAdapter(this, tasks);
//        ListView listView = findViewById(R.id.list);
//        listView.setAdapter(taskAdapter);
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
//                        Intent intent = new Intent(MainActivity.this, AddNewActivity.class);
//                        startActivity(intent);
                        myDb.deleteTask(selectedTask.getId().toString());
//                        taskListAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });

        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "EDIT",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        alertDialog.show();
    }


}
