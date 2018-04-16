package com.example.dell.filmera;

import java.util.ArrayList;

/**
 * Created by Dell on 10-04-2018.
 */

public class ApiClassForSimilarMovies {
    ArrayList<SimilarData> results;
    public class SimilarData{
        int id;
        String poster_path;
        String title;
    }
}
