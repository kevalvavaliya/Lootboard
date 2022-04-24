package com.example.lootboard.API;

import android.util.Log;

import retrofit2.Retrofit;

public class RetroClient {

    //private static String url="http://139.59.30.134:8888/";
    private static String url="https://CandidInferiorFlash.harrykanani.repl.co";

    public static APIinterface retroinit(){

        Log.d("url",url);
        Retrofit retrofit= MyRetrofit.getRetrofit(url);
        APIinterface apIinterface = retrofit.create(APIinterface.class);
        return apIinterface;
    }

}