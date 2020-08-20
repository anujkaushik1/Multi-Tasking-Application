package com.example.myfirstapp;


import androidx.annotation.RequiresApi;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;



import  	android.widget.Toolbar;




import androidx.drawerlayout.widget.DrawerLayout;


import android.os.Build;
import android.os.Bundle;



public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;

    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
      
        setActionBar(toolbar);
       // actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


    }


}