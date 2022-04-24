package com.example.lootboard.data;

import com.google.gson.annotations.SerializedName;

public class dataModel {
    String points;
    String user_id;
    String guild_id;
    String name;

    public void setPoints(String points) {
        this.points = points;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getGuild_id() {
        return guild_id;
    }

    public void setGuild_id(String guild_id) {
        this.guild_id = guild_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoints() {
        return points;
    }

    public  String getUserID(){
        return  user_id;
    }


}
