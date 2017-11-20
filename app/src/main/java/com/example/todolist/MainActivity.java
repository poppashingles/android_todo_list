package com.example.todolist;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        DatabaseHelper myDb = new DatabaseHelper(this);


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
}
