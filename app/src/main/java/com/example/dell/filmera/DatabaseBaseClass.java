package com.example.dell.filmera;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomMasterTable;

/**
 * Created by Dell on 25-03-2018.
 */

//@Database(entities = {Api_class_for_popular.Data.class},version = 1)
 @Database(entities = {ApiClassForFavourites.class},version = 1)
public abstract class DatabaseBaseClass extends RoomDatabase {
     abstract MoviesDao getMovieDao();
}
