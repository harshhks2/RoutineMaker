package com.example.routinemaker;

import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView taskRecView;
    private static ArrayList<Task> tasks;
    private RecViewAdapter adapter = new RecViewAdapter(this);
    private static View cardViewKeeper;
    private static View activityKeeper;
    private Button goBack;
    private Button done;
    private Button goBack2;
    private Button done2;
    private static Spinner spinnerHour;
    private static Spinner spinnerMinute;
    private static Spinner spinnerPeriod;
    private TextView taskToDo;
    private static TextView activityName;
    private static TextView activityDescription;
    private static int pos;
    private static int place;
    private static String desc;

    public static int getPlace() {
        return place;
    }

    public static void setPlace(int place) {
        MainActivity.place = place;
    }

    public static int getPos() {
        return pos;
    }

    public static void setPos(int pos) {
        MainActivity.pos = pos;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        taskRecView = findViewById(R.id.recView);



        DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
        tasks = dataBaseHelper.getTaskList();
        if(tasks == null){
            tasks = new ArrayList<>();
            Task t1 = new Task("06:00","11:30","AM","PM","Do This","None");
            tasks.add(t1);
            boolean b = dataBaseHelper.addOne(t1);
        }

        adapter.setTasks(tasks);


        taskRecView.setAdapter(adapter);
        taskRecView.setLayoutManager(new LinearLayoutManager(this));

        cardViewKeeper = findViewById(R.id.cardKeeper);
        activityKeeper = findViewById(R.id.activityKeeper);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Task t1 = new Task("06:00","11:30","AM","PM","Do This","None");
                System.out.println(t1.toString());
                tasks.add(t1);


                adapter.setTasks(tasks);

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                boolean b = dataBaseHelper.addOne(t1);
            }
        });

        spinnerHour = findViewById(R.id.spinnerHour);
        spinnerMinute = findViewById(R.id.spinnerMinute);
        spinnerPeriod = findViewById(R.id.spinnerPeriod);

        goBack = findViewById(R.id.btnGoBack);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCvVisible(false);
            }
        });
        done = findViewById(R.id.btnDone);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = spinnerHour.getSelectedItem().toString();
                String s2 = spinnerMinute.getSelectedItem().toString();
                String s3 = spinnerPeriod.getSelectedItem().toString();
                Task t1;
                if(place == 1) { t1 = new Task(s1+":"+s2,tasks.get(pos).getEndTime(),s3,tasks.get(pos).getEndPeriod(),tasks.get(pos).getTaskAtHand(),tasks.get(pos).getDescription()); }
                else { t1 = new Task(tasks.get(pos).getStartTime(),s1+":"+s2,tasks.get(pos).getStartPeriod(),s3,tasks.get(pos).getTaskAtHand(),tasks.get(pos).getDescription()); }
                tasks.set(pos,t1);
                adapter.setTasks(tasks);


                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                boolean b = dataBaseHelper.updateOne(t1,pos);


                setCvVisible(false);

            }
        });

        goBack2 = findViewById(R.id.btnGoBack2);
        goBack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAkVisible(false);

            }
        });

        activityName = findViewById(R.id.editTextActivity);
        activityDescription = findViewById((R.id.editTextDescription));

        done2 = findViewById(R.id.btnDone2);
        done2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String s1 = activityName.getText().toString();
               String s2 = activityDescription.getText().toString();
               Task t1 = new Task(tasks.get(pos).getStartTime(),tasks.get(pos).getEndTime(),tasks.get(pos).getStartPeriod(),tasks.get(pos).getEndPeriod(),s1,s2);
                tasks.set(pos,t1);
                adapter.setTasks(tasks);
                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                boolean b = dataBaseHelper.updateOne(t1,pos);
                setAkVisible(false);

                View vu = MainActivity.this.getCurrentFocus();
                if(vu != null) {
                    InputMethodManager imm=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(vu.getWindowToken(),0);
                }

            }
        });
        cardViewKeeper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCvVisible(false);
            }
        });
        activityKeeper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAkVisible(false);
            }
        });

    }

    public static void setCvVisible(boolean b)
    {

        if(b) cardViewKeeper.setVisibility(View.VISIBLE);
        else cardViewKeeper.setVisibility(View.GONE);

    }
    public static void setAkVisible(boolean b)
    {

        if(b) activityKeeper.setVisibility(View.VISIBLE);
        else activityKeeper.setVisibility(View.GONE);


    }

    public static void setDesc() {

        String s1 = tasks.get(pos).getTaskAtHand();
        String s2 = tasks.get(pos).getDescription();
        activityName.setText(s1);
        activityDescription.setText(s2);
    }

    public static void setSpinner() {

        int i;
        String s1,s2;
        if(place == 1) {
            s1 = tasks.get(pos).getStartTime();
            s2 = tasks.get(pos).getStartPeriod();
        }
        else {
            s1 = tasks.get(pos).getEndTime();
            s2 = tasks.get(pos).getEndPeriod();
        }
        spinnerHour.setSelection(Integer.parseInt(s1.substring(0,2))-1);
        spinnerMinute.setSelection(Integer.parseInt(s1.substring(3)));
        if(s2.equals("AM")) i=0;
        else i=1;
        spinnerPeriod.setSelection(i);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}