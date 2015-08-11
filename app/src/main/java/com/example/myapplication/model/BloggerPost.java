package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sondt on 10/08/2015.
 */
public class BloggerPost {
    public String id;
    public String url;
    public String title;

    @SerializedName("kind")
    public String type;
}
