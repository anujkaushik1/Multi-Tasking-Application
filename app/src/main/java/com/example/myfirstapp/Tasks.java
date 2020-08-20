package com.example.myfirstapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Tasks extends AppCompatActivity {
    FloatingActionButton fb;
    CardView cv;
    Button b;
    EditText title, description;
    TextView deadline, pending, completed;
    DatePickerDialog.OnDateSetListener listener;
    int day;
    int month;
    int year;
    String date;
    RelativeLayout relativeLayout;
    ArrayList<String> pendingarraylist = new ArrayList<>();
    ArrayList<String> completedarraylist = new ArrayList<>();
    sqlitetask hello;
    SQLiteDatabase db;
    String currentdate;
    SimpleDateFormat simpleDateFormat;
    ArrayList<String> currentdatearraylist = new ArrayList<>();

    ArrayList<Integer> databaseid = new ArrayList<>();

    String newcurrentdate;

    //TABBED TASK=
    TextView tododiary4, todonews4, todoquote4, todotask4;
    ImageView tododiaryimage4, todonewsimage4, todoquoteimage4, todotaskimage4;
    String testingdate;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        fb = (FloatingActionButton) findViewById(R.id.fb);
        cv = (CardView) findViewById(R.id.cv);
        b = (Button) findViewById(R.id.taskbutton);
        title = (EditText) findViewById(R.id.title);
        deadline = (TextView) findViewById(R.id.deadline);
        description = (EditText) findViewById(R.id.description);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativelayout);
        pending = (TextView) findViewById(R.id.pending);
        completed = (TextView) findViewById(R.id.completed);


        cv.setVisibility(View.INVISIBLE);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cv.setVisibility(View.VISIBLE);

            }
        });


        //TABBED =
        tododiary4 = (TextView) findViewById(R.id.tododiary4);
        todonews4 = (TextView) findViewById(R.id.todonews4);
        todoquote4 = (TextView) findViewById(R.id.todoquote4);
        todotask4 = (TextView) findViewById(R.id.todotasks4);


        tododiaryimage4 = (ImageView) findViewById(R.id.tododirayimage4);
        todonewsimage4 = (ImageView) findViewById(R.id.todonewsimage4);
        todoquoteimage4 = (ImageView) findViewById(R.id.todoquoteimage4);
        todotaskimage4 = (ImageView) findViewById(R.id.todotodoimage4);
        tabbed();


        buttonpressed();
        calender();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M/d ");
        newcurrentdate = sdf.format(new Date());
        pending();
        completed();


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void calender() {
        final Calendar cal = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("yyyy/M/d ");
        currentdate = simpleDateFormat.format(cal.getTime());
        System.out.println(currentdate);
        deadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH);
                day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Tasks.this, android.R.style.Theme_DeviceDefault_Light, listener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.DKGRAY));
                datePickerDialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP);

                datePickerDialog.show();
            }
        });
        listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                System.out.println(year + " " + month + "" + day);
                date = year + "/" + month + "/" + day;
                testingdate= year+"/" + month + "/" + day;


                 deadline.setText(date);
            }
        };
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cv.setVisibility(View.GONE);
            }
        });

    }


    public void databaseinsert() {

        hello = new sqlitetask(getApplicationContext());

        db = hello.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("stitle", String.valueOf(title.getText()));
        values.put("sdeadline", (String) deadline.getText());
        values.put("scurrentdate", currentdate);

        db.insert("todo", null, values);


    }

    public void databasefetch() {
        //fetch=
        hello = new sqlitetask(getApplicationContext());
        db = hello.getReadableDatabase();
        String columns[] = {"_id", "stitle", "sdeadline", "scurrentdate"};
        Cursor c = db.query("todo", columns, null, null, null, null, null);


        while (c.moveToNext()) {
            databaseid.add(c.getInt(0));
            pendingarraylist.add(c.getString(1));
            completedarraylist.add(c.getString(2));
            currentdatearraylist.add(c.getString(3));
        }

    }


    public void buttonpressed() {

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (title.getText().length() != 0 && deadline.length() != 0) {
                    databaseinsert();
                    title.getText().clear();
                    description.getText().clear();

                    cv.setVisibility(View.GONE);
                } else {
                    Toast toast = Toast.makeText(Tasks.this,
                            "Please enter title and deadline",
                            Toast.LENGTH_SHORT);

                    toast.show();
                    System.out.println("please add title");
                }
                System.out.println("new curreent date="+newcurrentdate);
                System.out.println("deadline =" +testingdate);


            }
        });

    }

    public void pending() {
        pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseid.clear();
                pendingarraylist.clear();
                completedarraylist.clear();
                currentdatearraylist.clear();
                databasefetch();
                Intent intent = new Intent(Tasks.this, PendingTasks.class);
                intent.putExtra("intentdata", pendingarraylist);
                intent.putExtra("currentdate", currentdatearraylist);   //currentdate
                intent.putExtra("intentdata1", completedarraylist);     //deadline
                intent.putExtra("databaseid", databaseid  );            //databaseid
                startActivity(intent);

            }
        });
    }

    public void completed() {
        completed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseid.clear();
                pendingarraylist.clear();
                completedarraylist.clear();
                currentdatearraylist.clear();
                databasefetch();

                    if(newcurrentdate==testingdate) {

                        Intent intent1 = new Intent(Tasks.this, CompletedTasks.class);
                        intent1.putExtra("completeddata", completedarraylist);
                        intent1.putExtra("pendingdata", pendingarraylist);  //pending
                        intent1.putExtra("currentdate", currentdatearraylist); //currentdate
                        startActivity(intent1);
                    }
                    else{
                        Toast toast = Toast.makeText(Tasks.this,
                                "Please enter details first",
                                Toast.LENGTH_SHORT);

                        toast.show();
                    }


            }
        });
    }
    public void tabbed(){
        tododiary4.setVisibility(View.INVISIBLE);
        todonews4.setVisibility(View.INVISIBLE);
        todoquote4.setVisibility(View.INVISIBLE);
        todotask4.setVisibility(View.VISIBLE);

        tododiaryimage4.setVisibility(View.VISIBLE);
        todonewsimage4.setVisibility(View.VISIBLE);
        todoquoteimage4.setVisibility(View.VISIBLE);
        todotaskimage4.setVisibility(View.VISIBLE);

        tododiaryimage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tododiary4.setVisibility(View.VISIBLE);
                todonews4.setVisibility(View.INVISIBLE);
                todoquote4.setVisibility(View.INVISIBLE);
                todotask4.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(Tasks.this,DiaryFront.class);
                startActivity(intent);


            }
        });
        todonewsimage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todonews4.setVisibility(View.VISIBLE);
                tododiary4.setVisibility(View.INVISIBLE);
                todoquote4.setVisibility(View.INVISIBLE);
                todotask4.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(Tasks.this,TOPNEWS.class);
                startActivity(intent);



            }
        });
        todoquoteimage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todoquote4.setVisibility(View.VISIBLE);
                tododiary4.setVisibility(View.INVISIBLE);
                todonews4.setVisibility(View.INVISIBLE);
                todotask4.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(Tasks.this,QUOTES.class);
                startActivity(intent);

            }
        });

        todotaskimage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todotask4.setVisibility(View.VISIBLE);
                tododiary4.setVisibility(View.INVISIBLE);
                todonews4.setVisibility(View.INVISIBLE);
                todoquote4.setVisibility(View.INVISIBLE);

            }
        });


    }

}
