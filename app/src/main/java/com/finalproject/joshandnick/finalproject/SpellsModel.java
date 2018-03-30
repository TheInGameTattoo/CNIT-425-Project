package com.finalproject.joshandnick.finalproject;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Josh Longawa & Nick Martino
 */


//an araylist of objects MyObject
//serialize them with the name associated wit it in the JSON file

public class SpellsModel {
    @SerializedName("list")
    public ArrayList<MyObject> list;

    static public class MyObject {
        @SerializedName("name")
        public String name;
        @SerializedName("desc")
        public String desc;
        @SerializedName("page")
        public String page;
        @SerializedName("range")
        public String range;
        @SerializedName("components")
        public String components;
        @SerializedName("material")
        public String material;
        @SerializedName("ritual")
        public String ritual;
        @SerializedName("duration")
        public String duration;
        @SerializedName("concentration")
        public String concentration;
        @SerializedName("casting_time")
        public String castTime;
        @SerializedName("level")
        public String level;
        @SerializedName("school")
        public String school;
        @SerializedName("class")
        public String playerClass;
    }


}
