package com.example.everythingapplication.RestAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetrofit {
    public static Retrofit retrofit;
    public static String URL = "https://fakestoreapi.com/";
    public Retrofit getRetrofit() {
        if(retrofit==null)
        {
            retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
