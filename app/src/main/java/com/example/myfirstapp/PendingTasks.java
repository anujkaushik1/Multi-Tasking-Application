package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class PendingTasks extends AppCompatActivity {
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_tasks);
        recyclerView=(RecyclerView)findViewById(R.id.pendingrecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Intent intent=getIntent();
      ArrayList<String> pendingdata= (ArrayList<String>) intent.getSerializableExtra("intentdata");
        ArrayList<String> deadlinedata  = (ArrayList<String>) intent.getSerializableExtra("intentdata1");
        ArrayList<String>currentdate= (ArrayList<String>) intent.getSerializableExtra("currentdate");
        ArrayList<Integer> databaseid= (ArrayList<Integer>) intent.getSerializableExtra("databaseid");
      recyclerView.setAdapter(new pendingrecyclerview(pendingdata,deadlinedata, currentdate, getApplicationContext(),databaseid));



    }
}