package com.example.dell.filmera;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by Dell on 20-03-2018.
 */

public class Api_class_for_popular  {
    public ArrayList<Data> results;

    @Entity
     public static class Data{
         @PrimaryKey
                 @NonNull
         String title;
             int id;
         String poster_path;
    }
}
