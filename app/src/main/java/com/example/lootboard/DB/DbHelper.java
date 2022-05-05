package com.example.lootboard.DB;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.lootboard.data.dataModel;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String USERS_TABLE_NAME = "users";
    public static final String USERS_COLUMN_DISCORDID = "discordid";
    public static final String USERS_COLUMN_NAME = "user_name";
    public DbHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table users " +
                        "( user_name varchar,discordid varchar primary key)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    public boolean insertUser (String name, String discordid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_name", name);
        contentValues.put("discordid",discordid);
        db.insert("users", null, contentValues);
        return true;
    }

    public ArrayList<dataModel> getUsernList() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<dataModel> usern = new ArrayList<dataModel>();
        Cursor res =  db.rawQuery( "select * from users", null );

        if(res.moveToFirst()){
            do{
                dataModel d = new dataModel();
                String n = res.getString(0);
                d.setName(n);
                String id = res.getString(1);
                d.setUser_id(id);
                usern.add(d);
            }
            while (res.moveToNext());
        }

        return usern;
    }

//    public ArrayList<String> getUserIDList() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        ArrayList<String> useridlist = new ArrayList<String>();
//        Cursor res =  db.rawQuery( "select discordid from users", null );
//        if(res.moveToFirst()){
//            do{
//                String n = res.getString(0);
//                useridlist.add(n);
//            }
//            while (res.moveToNext());
//        }
//
//        return useridlist;
//    }


    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, USERS_TABLE_NAME);
        return numRows;
    }

//    public boolean updateContact (Integer id, String name, String phone, String email, String street,String place) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", name);
//        contentValues.put("phone", phone);
//        contentValues.put("email", email);
//        contentValues.put("street", street);
//        contentValues.put("place", place);
//        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
//        return true;
//    }
//
    public Integer deleteUser (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("users",
                "discordid = ? ",
                new String[] {id});

    }

//    public ArrayList<String> getAllCotacts() {
//        ArrayList<String> array_list = new ArrayList<String>();
//
//        //hp = new HashMap();
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res =  db.rawQuery( "select * from contacts", null );
//        res.moveToFirst();
//
//        while(res.isAfterLast() == false){
//            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
//            res.moveToNext();
//        }
//        return array_list;
//    }

}
