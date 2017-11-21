package com.example.todolist;

import android.app.LauncherActivity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by user on 17/11/2017
 */

public class TaskRepo extends SQLiteOpenHelper {

    private static final int VERSION = 3;
    private static final String DATABASE_NAME = "tasks.db";
    private static final String TABLE_NAME = "tasks_table";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "TITLE";
    private static final String COL_3 = "DESCRIPTION";
    private static final String COL_4 = "COMPLETED";

    public TaskRepo(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,TITLE TEXT,DESCRIPTION TEXT,COMPLETED BOOLEAN)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String title, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, title);
        contentValues.put(COL_3, description);
        contentValues.put(COL_4, false);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public ArrayList<Task> getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Task> tasks = new ArrayList<Task>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor != null)
        {
            if (cursor.moveToFirst()) {
                do  {

                    String completedStr = cursor.getString(3);

                    boolean completed = completedStr.equals("1");

                    tasks.add(new Task(cursor.getInt(0), cursor.getString(1), cursor.getString(2), completed));
                } while(cursor.moveToNext());
            }
            cursor.close();
        }

        db.close();

        return tasks;

    }

    public void deleteTask(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("delete from " + TABLE_NAME + " where id='"+id+"'");
    }

    public boolean updateTask(String id, String title, String description, Boolean checked) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, title);
        contentValues.put(COL_3, description);
        contentValues.put(COL_4, checked);
        int result = db.update(TABLE_NAME, contentValues, "id= ?", new String[] {id} );
        return result != -1;
    }



}
