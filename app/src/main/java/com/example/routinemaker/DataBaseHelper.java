package com.example.routinemaker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String START_TIME = "START_TIME";
    public static final String END_TIME = "END_TIME";
    public static final String START_PERIOD = "START_PERIOD";
    public static final String END_PERIOD = "END_PERIOD";
    public static final String TASK_AT_HAND = "TASK_AT_HAND";
    public static final String TASKS_TABLE = "TASKS_TABLE";
    public static final String DESCRIPTION = "DESCRIPTION";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "tasks.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement1 = "CREATE TABLE " + TASKS_TABLE + " " + "(ID INTEGER PRIMARY KEY AUTOINCREMENT," + START_TIME + " TEXT," + END_TIME + " TEXT,"
                + START_PERIOD + " TEXT," + END_PERIOD + " TEXT," + TASK_AT_HAND + " TEXT," + DESCRIPTION + " TEXT)";

        db.execSQL(createTableStatement1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(Task tsk) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(START_TIME,tsk.getStartTime());
        cv.put(END_TIME,tsk.getEndTime());
        cv.put(START_PERIOD,tsk.getStartPeriod());
        cv.put(END_PERIOD,tsk.getEndPeriod());
        cv.put(TASK_AT_HAND,tsk.getTaskAtHand());
        cv.put(DESCRIPTION,tsk.getDescription());

        long insert = db.insert(TASKS_TABLE,null,cv);
        if(insert == -1) return false;
        return true;
    }

    public ArrayList<Task> getTaskList(){
        ArrayList<Task> returnList = new ArrayList<>();
        String qs = "SELECT * FROM TASKS_TABLE";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(qs,null);
        if(cursor.moveToFirst()) {
            do{
                String s1 = cursor.getString(1);
                String s2 = cursor.getString(2);
                String s3 = cursor.getString(3);
                String s4 = cursor.getString(4);
                String s5 = cursor.getString(5);
                String s6 = cursor.getString(6);
                Task t1 = new Task(s1,s2,s3,s4,s5,s6);
                returnList.add(t1);

            }while (cursor.moveToNext());
        }
        else{
            return null;
        }

        cursor.close();
        db.close();
        return returnList;
    }

    public boolean updateOne(Task tsk,int p) {

        String qs = "SELECT * FROM TASKS_TABLE";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(qs,null);

        ContentValues cv = new ContentValues();
        cv.put(START_TIME,tsk.getStartTime());
        cv.put(END_TIME,tsk.getEndTime());
        cv.put(START_PERIOD,tsk.getStartPeriod());
        cv.put(END_PERIOD,tsk.getEndPeriod());
        cv.put(TASK_AT_HAND,tsk.getTaskAtHand());
        cv.put(DESCRIPTION,tsk.getDescription());

        if(cursor.moveToPosition(p)) {
            int i = cursor.getInt(0);
            long insert = db.update(TASKS_TABLE,cv,"ID =" + i,null);
            if(insert == -1) return false;}


        return true;
    }

    public boolean deleteOne(int p) {

        String qs = "SELECT * FROM TASKS_TABLE";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(qs,null);


        if(cursor.moveToPosition(p)) {
            int i = cursor.getInt(0);
            long delete = db.delete(TASKS_TABLE,"ID =" + i,null);
        }

        return true;
    }

}
