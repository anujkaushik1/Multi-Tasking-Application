package com.example.myfirstapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class quotesapi {
    private static final String url= "https://type.fit/";

    public static anuj anu= null;
    public static anuj getdata(){
        if(anu==null){
            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            anu = retrofit.create(anuj.class);
        }
        return anu;
    }


    public interface anuj{
        @GET("api/quotes")
        Call<List<QuotesResponse>> getquotes();

    }
}
