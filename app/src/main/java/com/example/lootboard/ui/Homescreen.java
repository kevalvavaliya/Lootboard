package com.example.lootboard.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lootboard.R;
import com.example.lootboard.ui.fragment.home_fragment;

public class Homescreen extends AppCompatActivity {

    FragmentTransaction ft;
    FragmentManager fm;
    ImageButton imgbtn;
    LinearLayout linearLayout;
    TextView toolbarText;
    androidx.appcompat.widget.Toolbar toolbar;
    public static int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        intialization();
        setFragment();

        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgbtn.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.moon).getConstantState())){
                    linearLayout.setBackgroundColor(getResources().getColor(R.color.primaeywhite));
                    toolbarText.setTextColor(getResources().getColor(R.color.primaryblack));
                    imgbtn.setImageResource(R.drawable.sun);
                    toolbar.setBackground(getResources().getDrawable(R.drawable.toolbarborderblack));

                }
                else{
                    imgbtn.setImageResource(R.drawable.moon);
                    linearLayout.setBackgroundColor(getResources().getColor(R.color.primaryblack));
                    toolbarText.setTextColor(getResources().getColor(R.color.primaeywhite));
                    toolbar.setBackground(getResources().getDrawable(R.drawable.toolbarborder));

                }
            }
        });
    }

    void intialization(){

        imgbtn = findViewById(R.id.modeswitch);
        linearLayout = findViewById(R.id.mainlayout);
        toolbarText = findViewById(R.id.toolbartitle);
        toolbar = findViewById(R.id.toolbr);
        i=0;
    }

    void setFragment(){
        fm = getSupportFragmentManager();
        ft  =fm.beginTransaction();
        ft.replace(R.id.homeFrame,new home_fragment());
        ft.commit();
    }

    @Override
    protected void onResume() {
        i=0;
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        setFragment();
        i++;
        if(i>1){
            super.onBackPressed();
        }
        else{
            Toast.makeText(this,"Press again to exit",Toast.LENGTH_SHORT).show();
        }

    }
}