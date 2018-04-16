package com.example.dell.filmera;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Dell on 11-04-2018.
 */

public class ForDatabaseTable {
    int id;
    String title;
    String poster_path;
    public ForDatabaseTable( int id, String title, String poster_path) {
        this.id = id;
        this.title = title;
        this.poster_path = poster_path;
    }

}
