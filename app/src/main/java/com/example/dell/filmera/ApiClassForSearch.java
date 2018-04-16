package com.example.dell.filmera;

import java.util.ArrayList;

/**
 * Created by Dell on 13-04-2018.
 */

public class ApiClassForSearch {
    public ArrayList<SearchData> results;
    public class SearchData{
        public int id;
        public String poster_path;
        public String title;
    }
}
