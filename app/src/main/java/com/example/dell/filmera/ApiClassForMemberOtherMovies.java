package com.example.dell.filmera;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Dell on 13-04-2018.
 */

public class ApiClassForMemberOtherMovies {
    ArrayList<MemberOtherMovies> cast;
    public class MemberOtherMovies{
        int id;
        String title;
        String poster_path;
    }

}
