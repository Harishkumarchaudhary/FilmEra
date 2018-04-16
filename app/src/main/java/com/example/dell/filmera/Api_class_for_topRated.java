package com.example.dell.filmera;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Dell on 20-03-2018.
 */

public class Api_class_for_topRated {
       ArrayList<Data2> results;
       public class Data2{
           String original_title;
           int id;
           String poster_path;
           @SerializedName("vote_average")
           float rating;
       }
}
