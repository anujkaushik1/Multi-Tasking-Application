package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class CompletedTasks extends AppCompatActivity {
    RecyclerView completedrecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_tasks);
        completedrecyclerview=(RecyclerView)findViewById(R.id.completedrecyclerview);

        Intent intent = getIntent();
        ArrayList<String>completeddata= (ArrayList<String>) intent.getSerializableExtra("completeddata");
        ArrayList<String>completeddata1= (ArrayList<String>) intent.getSerializableExtra("pendingdata");
        ArrayList<String> currentdate = (ArrayList<String>) intent.getSerializableExtra("currentdate");
        completedrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        completedrecyclerview.setAdapter(new completedrecyclerlist(completeddata,completeddata1,currentdate));
    }
}