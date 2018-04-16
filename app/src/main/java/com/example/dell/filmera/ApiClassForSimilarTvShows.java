package com.example.dell.filmera;

import java.util.ArrayList;

/**
 * Created by Dell on 16-04-2018.
 */

public class ApiClassForSimilarTvShows {
    ArrayList<ApiClassForSimilarTvShows.SimilarData> results;
    public class SimilarData{
        int id;
        String poster_path;
        String name;
    }
}
