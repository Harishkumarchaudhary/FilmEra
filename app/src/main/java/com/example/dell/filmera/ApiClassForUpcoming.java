package com.example.dell.filmera;

import java.util.ArrayList;

/**
 * Created by Dell on 11-04-2018.
 */

public class ApiClassForUpcoming {
    public ArrayList<UpcomingData> results;
    public class UpcomingData{
        public int id;
        public String title;
        public String poster_path;
        public String release_date;
    }
}
