package com.example.lootboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.lootboard.ui.Homescreen;

public class Splash extends AppCompatActivity implements Runnable {

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler = new Handler();
        handler.postDelayed(this,4000);
    }

    @Override
    public void run() {
        Intent i = new Intent(this, Homescreen.class);
        startActivity(i);
        finish();
    }
}