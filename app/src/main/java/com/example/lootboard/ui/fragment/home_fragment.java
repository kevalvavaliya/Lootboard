package com.example.lootboard.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lootboard.R;
import com.example.lootboard.ui.Homescreen;


public class home_fragment extends Fragment {
    View view;
    androidx.cardview.widget.CardView card;
    FragmentTransaction ft;
    FragmentManager fm;
    Bundle bundle = new Bundle();
    userFragment usrfrg=  new userFragment();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_home_fragment, container, false);
        intialization();
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString("serverID","780066247601291285");
                usrfrg.setArguments(bundle);
                Homescreen.i = 0;
                ft.replace(R.id.homeFrame,usrfrg);
                ft.commit();
            }
        });

        return view;
    }

    void intialization(){
        card = view.findViewById(R.id.servercard);
        fm = getActivity().getSupportFragmentManager();
        ft  = fm.beginTransaction();
    }
}