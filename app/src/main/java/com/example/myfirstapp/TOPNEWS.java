package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TOPNEWS extends AppCompatActivity {
    public static final String key = "3240245b7fe448ec88b7537298e8ecef";
    RecyclerView rv;
    //TABBED TASK=
    TextView tododiary2,todonews2,todoquote2,todotask2;
    ImageView tododiaryimage2,todonewsimage2,todoquoteimage2,todotaskimage2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t_o_p_n_e_w_s);

        getdata();
         rv = (RecyclerView)findViewById(R.id.recycler);
         rv.setLayoutManager(new LinearLayoutManager(this));
        //TABBED =
        tododiary2 =(TextView)findViewById(R.id.tododiary2);
        todonews2 =(TextView)findViewById(R.id.todonews2);
        todoquote2 =(TextView)findViewById(R.id.todoquote2);
        todotask2 =(TextView)findViewById(R.id.todotasks2);


        tododiaryimage2 =(ImageView)findViewById(R.id.tododirayimage2);
        todonewsimage2 =(ImageView)findViewById(R.id.todonewsimage2);
        todoquoteimage2 =(ImageView)findViewById(R.id.todoquoteimage2);
        todotaskimage2 =(ImageView)findViewById(R.id.todotodoimage2);
        tabbed();
    }

    private void getdata(){
        Call<Jsonclass> call= newsapi.getservice().getlatestnews("in",key);
        call.enqueue(new Callback<Jsonclass>() {
            @Override
            public void onResponse(Call<Jsonclass> call, Response<Jsonclass> response) {
                Jsonclass alldata = response.body();
                List<Article> hello= alldata.getArticles();
              rv.setAdapter(new customadapter(hello, getApplicationContext()));
               Log.d(String.valueOf(alldata),"done");
            }

            @Override
            public void onFailure(Call<Jsonclass> call, Throwable t) {
                Log.d(String.valueOf(t),t.getMessage());
            }
        });
    }
    public void tabbed(){
        tododiary2.setVisibility(View.INVISIBLE);
        todonews2.setVisibility(View.VISIBLE);
        todoquote2.setVisibility(View.INVISIBLE);
        todotask2.setVisibility(View.INVISIBLE);

        tododiaryimage2.setVisibility(View.VISIBLE);
        todonewsimage2.setVisibility(View.VISIBLE);
        todoquoteimage2.setVisibility(View.VISIBLE);
        todotaskimage2.setVisibility(View.VISIBLE);

        tododiaryimage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tododiary2.setVisibility(View.VISIBLE);
                todonews2.setVisibility(View.INVISIBLE);
                todoquote2.setVisibility(View.INVISIBLE);
                todotask2.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(TOPNEWS.this,DiaryFront.class);
                startActivity(intent);


            }
        });
        todonewsimage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todonews2.setVisibility(View.VISIBLE);
                tododiary2.setVisibility(View.INVISIBLE);
                todoquote2.setVisibility(View.INVISIBLE);
                todotask2.setVisibility(View.INVISIBLE);


            }
        });
        todoquoteimage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todoquote2.setVisibility(View.VISIBLE);
                tododiary2.setVisibility(View.INVISIBLE);
                todonews2.setVisibility(View.INVISIBLE);
                todotask2.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(TOPNEWS.this,QUOTES.class);
                startActivity(intent);
            }
        });

        todotaskimage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todotask2.setVisibility(View.VISIBLE);
                tododiary2.setVisibility(View.INVISIBLE);
                todonews2.setVisibility(View.INVISIBLE);
                todoquote2.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(TOPNEWS.this,Tasks.class);
                startActivity(intent);
            }
        });


    }
}