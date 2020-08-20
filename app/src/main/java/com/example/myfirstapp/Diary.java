package com.example.myfirstapp;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Diary extends AppCompatActivity {
    EditText diarytitle,diarydescription;
    ImageView diaryimageview;
    String  currentdate;



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        diarytitle = (EditText) findViewById(R.id.diarytitle);
        diarydescription = (EditText) findViewById(R.id.diarydescription);
        diaryimageview = (ImageView) findViewById(R.id.diaryimageview);

        final HashMap<String,Object> data = new HashMap<>();
        final Calendar cal = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy");
        currentdate= simpleDateFormat.format(cal.getTime());




        diaryimageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(diarytitle.getText().length()==0 && diarydescription.getText().length()==0){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Please enter title and description",
                            Toast.LENGTH_SHORT);

                    toast.show();
                }
            else {

                data.put("title",diarytitle.getText().toString());
                data.put("description",diarydescription.getText().toString());
                data.put("currentdate",currentdate);

                FirebaseDatabase.getInstance().getReference().child("data").push().setValue(data);

                diarytitle.getText().clear();
                diarydescription.getText().clear();
                Intent intent = new Intent(Diary.this,DiaryFront.class);
                startActivity(intent);}
            }
        });

    }}


