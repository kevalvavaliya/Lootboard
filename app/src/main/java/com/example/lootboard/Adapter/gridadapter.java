package com.example.lootboard.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lootboard.DB.DbHelper;
import com.example.lootboard.R;
import com.example.lootboard.data.dataModel;

import java.util.ArrayList;

public class gridadapter extends BaseAdapter {
    Context context;
    ArrayList<dataModel> list;
    DbHelper mydb;
    ArrayList<String> colors =new ArrayList<String>(){
        {
            add("#ff9700");
            add("#fe5050");
            add("#3eaeff");
            add("#37c473");
        }
    };
    public gridadapter(Context context,ArrayList<dataModel> list){
        this.context = context;
        this.list = list;
        mydb = new DbHelper(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.griditem,viewGroup,false);
        }

        int index = (int)(Math.random() * colors.size());
        LinearLayout usercards;
        LinearLayout delbtn;
        usercards = view.findViewById(R.id.usercard);
        delbtn = view.findViewById(R.id.deletebtn);
        usercards.getBackground().setTint(Color.parseColor(colors.get(index)));


        TextView tokenview,nameview;
        tokenview =view.findViewById(R.id.token_view);
        nameview = view.findViewById(R.id.name_view);
        tokenview.setText(list.get(i).getPoints()+" Tokens \uD83D\uDCB0");
        nameview.setText(list.get(i).getName());
        delbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mydb.deleteUser(list.get(i).getUserID());
                Toast.makeText(context,list.get(i).getName() + " removed",Toast.LENGTH_SHORT).show();
                list.remove(i);
                notifyDataSetChanged();}
        });

        return  view;

    }
}
