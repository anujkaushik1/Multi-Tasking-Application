package com.example.myfirstapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DiaryFront extends AppCompatActivity {
    ArrayList<String> diarydatatitle = new ArrayList<>();
    ArrayList<String> diarydatadescription = new ArrayList<>();
    RecyclerView diaryfrontrecycler;
    ArrayList<String>  diarycurrentdate = new ArrayList<>();
    FloatingActionButton floatingActionButton;

    //TABBED TASK=
    TextView tododiary1,todonews1,todoquote1,todotask1;
    ImageView tododiaryimage1,todonewsimage1,todoquoteimage1,todotaskimage1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_front);
        diaryfrontrecycler = (RecyclerView)findViewById(R.id.diaryfrontrecycler);
        floatingActionButton =(FloatingActionButton)findViewById(R.id.floatingdiary);
        diaryfrontrecycler.setLayoutManager(new LinearLayoutManager(this));

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("data");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    diarymodelclass dmc = dataSnapshot1.getValue(diarymodelclass.class);
                    String hello = dmc.getTitle();
                    String hello1 = dmc.getDescription();
                   String hello2 = dmc.getCurrentdate();
                    diarydatatitle.add(hello);
                    diarydatadescription.add(hello1);
                    diarycurrentdate.add(hello2);
                }

                diaryfrontrecycler.setAdapter(new diaryrecycler(diarydatatitle,diarydatadescription, diarycurrentdate));


            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DiaryFront.this,Diary.class);
                startActivity(intent);

            }
        });

        //TABBED =
        tododiary1 =(TextView)findViewById(R.id.tododiary1);
        todonews1 =(TextView)findViewById(R.id.todonews1);
        todoquote1 =(TextView)findViewById(R.id.todoquote1);
        todotask1 =(TextView)findViewById(R.id.todotasks1);


        tododiaryimage1 =(ImageView)findViewById(R.id.tododirayimage1);
        todonewsimage1 =(ImageView)findViewById(R.id.todonewsimage1);
        todoquoteimage1 =(ImageView)findViewById(R.id.todoquoteimage1);
        todotaskimage1 =(ImageView)findViewById(R.id.todotodoimage1);
        tabbed();
    }

    public  void tabbed(){


        tododiary1.setVisibility(View.VISIBLE);
        todonews1.setVisibility(View.INVISIBLE);
        todoquote1.setVisibility(View.INVISIBLE);
        todotask1.setVisibility(View.INVISIBLE);

        tododiaryimage1.setVisibility(View.VISIBLE);
        todonewsimage1.setVisibility(View.VISIBLE);
        todoquoteimage1.setVisibility(View.VISIBLE);
        todotaskimage1.setVisibility(View.VISIBLE);

       tododiaryimage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tododiary1.setVisibility(View.VISIBLE);
                todonews1.setVisibility(View.INVISIBLE);
                todoquote1.setVisibility(View.INVISIBLE);
                todotask1.setVisibility(View.INVISIBLE);


            }
        });
        todonewsimage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todonews1.setVisibility(View.VISIBLE);
                tododiary1.setVisibility(View.INVISIBLE);
                todoquote1.setVisibility(View.INVISIBLE);
                todotask1.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(DiaryFront.this,TOPNEWS.class);
                startActivity(intent);

            }
        });
        todoquoteimage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todoquote1.setVisibility(View.VISIBLE);
                tododiary1.setVisibility(View.INVISIBLE);
                todonews1.setVisibility(View.INVISIBLE);
                todotask1.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(DiaryFront.this,QUOTES.class);
                startActivity(intent);
            }
        });

        todotaskimage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todotask1.setVisibility(View.VISIBLE);
                tododiary1.setVisibility(View.INVISIBLE);
                todonews1.setVisibility(View.INVISIBLE);
                todoquote1.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(DiaryFront.this,Tasks.class);
                startActivity(intent);
            }
        });





    }

}