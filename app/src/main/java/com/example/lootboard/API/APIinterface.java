package com.example.lootboard.API;

import com.example.lootboard.data.dataModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIinterface {

    @POST("/getdata")
    Call<ArrayList<dataModel>> fetch_tokens(@Body ArrayList<dataModel> users);
}
