package com.example.footprint.Model;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static final String API_URL = "https://api.unsplash.com/";
    private static final String ACCESS_KEY = "42csb7bNhhaC7zC9rEnWN7PZ8lNwAnyVjDHX87zNLwU";

    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static PhotoApi photoApi = retrofit.create(PhotoApi.class);

    public static PhotoApi getPhotoApi(){
        return photoApi;
    }
}
