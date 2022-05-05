package com.example.lootboard.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.lootboard.API.APIinterface;
import com.example.lootboard.API.RetroClient;
import com.example.lootboard.Adapter.gridadapter;
import com.example.lootboard.DB.DbHelper;
import com.example.lootboard.R;
import com.example.lootboard.data.dataModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class userFragment extends Fragment {

    View view;
    Bundle b;
    LinearLayout bottomSheet, closedrawer;
    BottomSheetBehavior bottomSheetBehavior;
    EditText name, discord;
    Button submit;
    private DbHelper mydb;
    APIinterface apIinterface;
    ArrayList<dataModel> discorduser = new ArrayList<>();
    ProgressDialog progressDialog;

    GridView gridView;

    com.google.android.material.floatingactionbutton.FloatingActionButton floatingActionButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_user, container, false);
        intialization();
        getDatafromDB();
        floatingActionButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        toogglebottomdrawr();
                    }
                }
        );
        closedrawer.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        toogglebottomdrawr();
                    }
                }
        );

        submit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        storeData();
                    }
                }
        );

        return view;


    }

    void intialization() {
        b = this.getArguments();
        if (b != null) {
        }
        bottomSheet = view.findViewById(R.id.bottom_drawer);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        floatingActionButton = view.findViewById(R.id.adduser);
        closedrawer = view.findViewById(R.id.clsdrw);
        name = view.findViewById(R.id.name);
        discord = view.findViewById(R.id.disid);
        submit = view.findViewById(R.id.submituser);
        mydb = new DbHelper(getContext());
        gridView = view.findViewById(R.id.usergrid);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("fetching");

    }

    private void toogglebottomdrawr() {

        if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);


        } else {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        }

    }

    private void storeData() {
        if (name.getText().length() != 0 && discord.getText().length() != 0) {
            if (mydb.insertUser(name.getText().toString(), discord.getText().toString())) {
                progressDialog.show();
                name.setText("");
                discord.setText("");
                getDatafromDB();
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                Toast.makeText(getActivity(), "success", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getActivity(), "Invalid details", Toast.LENGTH_SHORT).show();
        }
    }

    public void getDatafromDB() {

        discorduser = mydb.getUsernList();

        if (discorduser.size() != 0) {
            Log.d("check","Name : "+discorduser.get(0).getName() + "id : " +discorduser.get(0).getUser_id());
            fetchDataFromApi(discorduser);

        }
    }

    public void fetchDataFromApi(ArrayList<dataModel> list) {
        apIinterface = RetroClient.retroinit();
        Call<ArrayList<dataModel>> c = apIinterface.fetch_tokens(list);
        c.enqueue(new Callback<ArrayList<dataModel>>() {
            @Override
            public void onResponse(Call<ArrayList<dataModel>> call, Response<ArrayList<dataModel>> response) {
                if (response.code() == 200) {
                    progressDialog.dismiss();
                    if(response.body().size()!=0) {
                        ArrayList<dataModel> l = response.body();
                        gridView.setAdapter(new gridadapter(getContext(), l));

                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), "something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<dataModel>> call, Throwable t) {
                progressDialog.dismiss();
                Log.d("fail", "onFailure: " + t);
                Toast.makeText(getActivity(), "Server error", Toast.LENGTH_SHORT).show();
            }
        });
    }

}