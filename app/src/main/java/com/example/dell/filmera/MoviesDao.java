package com.example.dell.filmera;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 25-03-2018.
 */

@Dao
public interface MoviesDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insertMovie(Api_class_for_popular.Data api_popular_data);
//    @Query("SELECT * FROM Data")
//    List<Api_class_for_popular.Data> getAllPopularMovies();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertfavourites(ApiClassForFavourites apiClassForFavourites);
    @Query("SELECT * FROM ApiClassForFavourites")
    List<ApiClassForFavourites> getFavourites();
//    @Insert
//    void insertAllMovies(Api_class_for_popular.results);
//    @Query("SELECT * FROM Api_class_for_popular.Data")
//    ArrayList<Api_class_for_popular.> getallpopularmovies();
}
