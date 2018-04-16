package com.example.dell.filmera;

import java.util.ArrayList;

/**
 * Created by Dell on 16-04-2018.
 */

public class ApiClassForTvMemberOtherShows {
    ArrayList<ApiClassForTvMemberOtherShows.MemberOtherMovies> cast;
    public class MemberOtherMovies{
        int id;
        String name;
        String poster_path;
    }
}
