package com.example.myfirstapp;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class newsapi {
    public static final String url = "http://newsapi.org/v2/";
    public static final String key = "3240245b7fe448ec88b7537298e8ecef";

    public static anuj anu= null;
    public static anuj getservice(){
        if (anu==null){
            Retrofit retro = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
             anu = retro.create(anuj.class);


        }
        return anu;

    }

    public interface anuj{
        @GET("top-headlines")
        Call<Jsonclass> getlatestnews(@Query("country") String country,@Query("apiKey") String webkey );
    }
}
