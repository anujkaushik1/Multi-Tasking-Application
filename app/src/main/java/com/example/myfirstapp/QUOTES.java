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

public class QUOTES extends AppCompatActivity {
    RecyclerView rv;
    //TABBED TASK=
    TextView tododiary3,todonews3,todoquote3,todotask3;
    ImageView tododiaryimage3,todonewsimage3,todoquoteimage3,todotaskimage3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_u_o_t_e_s);
        rv = (RecyclerView)findViewById(R.id.quotesrecyclerview);


        //TABBED =
        tododiary3 =(TextView)findViewById(R.id.tododiary3);
        todonews3 =(TextView)findViewById(R.id.todonews3);
        todoquote3 =(TextView)findViewById(R.id.todoquote3);
        todotask3 =(TextView)findViewById(R.id.todotasks3);


        tododiaryimage3 =(ImageView)findViewById(R.id.tododirayimage3);
        todonewsimage3 =(ImageView)findViewById(R.id.todonewsimage3);
        todoquoteimage3 =(ImageView)findViewById(R.id.todoquoteimage3);
        todotaskimage3 =(ImageView)findViewById(R.id.todotodoimage3);
        tabbed();
        rv.setLayoutManager(new LinearLayoutManager(this));
        Call<List<QuotesResponse>> hello = quotesapi.getdata().getquotes();
        hello.enqueue(new Callback<List<QuotesResponse>>() {
            @Override
            public void onResponse(Call<List<QuotesResponse>> call, Response<List<QuotesResponse>> response) {
                List<QuotesResponse> alldata=response.body();
                rv.setAdapter(new quotesrecyclerview(getApplicationContext(),alldata));
                Log.d(String.valueOf(response),"done");

            }

            @Override
            public void onFailure(Call<List<QuotesResponse>> call, Throwable t) {
                Log.d(t.getMessage(),"fail");

            }
        });
    }
    public void tabbed(){
        tododiary3.setVisibility(View.INVISIBLE);
        todonews3.setVisibility(View.INVISIBLE);
        todoquote3.setVisibility(View.VISIBLE);
        todotask3.setVisibility(View.INVISIBLE);

        tododiaryimage3.setVisibility(View.VISIBLE);
        todonewsimage3.setVisibility(View.VISIBLE);
        todoquoteimage3.setVisibility(View.VISIBLE);
        todotaskimage3.setVisibility(View.VISIBLE);

        tododiaryimage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tododiary3.setVisibility(View.VISIBLE);
                todonews3.setVisibility(View.INVISIBLE);
                todoquote3.setVisibility(View.INVISIBLE);
                todotask3.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(QUOTES.this,DiaryFront.class);
                startActivity(intent);


            }
        });
        todonewsimage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todonews3.setVisibility(View.VISIBLE);
                tododiary3.setVisibility(View.INVISIBLE);
                todoquote3.setVisibility(View.INVISIBLE);
                todotask3.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(QUOTES.this,TOPNEWS.class);
                startActivity(intent);


            }
        });
        todoquoteimage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todoquote3.setVisibility(View.VISIBLE);
                tododiary3.setVisibility(View.INVISIBLE);
                todonews3.setVisibility(View.INVISIBLE);
                todotask3.setVisibility(View.INVISIBLE);

            }
        });

        todotaskimage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todotask3.setVisibility(View.VISIBLE);
                tododiary3.setVisibility(View.INVISIBLE);
                todonews3.setVisibility(View.INVISIBLE);
                todoquote3.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(QUOTES.this,Tasks.class);
                startActivity(intent);
            }
        });
}
}