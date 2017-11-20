package com.example.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddNewActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    Button button_add_new;
    EditText edittextNewDescription, edittextNewTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        myDb = new DatabaseHelper(this);
        button_add_new = findViewById(R.id.button_add_new);
        edittextNewDescription = findViewById(R.id.edittext_new_description);
        edittextNewTitle = findViewById(R.id.edittext_new_title);

        addData();
    }

    //  Add new task functions
    public void addData() {
        button_add_new.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isInserted = myDb.insertData(edittextNewTitle.getText().toString(), edittextNewDescription.getText().toString());
                        if(isInserted == true)
                            Toast.makeText(AddNewActivity.this, "Task added", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AddNewActivity.this, "Task could not be added", Toast.LENGTH_LONG).show();
                    }

                }
        );
    }
}
