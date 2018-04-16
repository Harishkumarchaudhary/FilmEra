package com.example.dell.filmera;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

public class FavouriteShowing extends AppCompatActivity implements AdapterForFavourites.onClick {
   RecyclerView recyclerView;
    AdapterForFavourites adapterForFavourites;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_showing);
        recyclerView=findViewById(R.id.recycle);
        DatabaseBaseClass databaseBaseClass= Room.databaseBuilder(getApplicationContext(),DatabaseBaseClass.class,"Data").allowMainThreadQueries().build();
        MoviesDao moviesDao=databaseBaseClass.getMovieDao();
        List<ApiClassForFavourites> list=moviesDao.getFavourites();
        if(list!=null) {
            adapterForFavourites =new AdapterForFavourites(this,list, (AdapterForFavourites.onClick) this);
            recyclerView.setAdapter(adapterForFavourites);
            recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        }
        else{
            Toast.makeText(this, "NO FAVOURITES TILL NOW :(", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void do_that(ApiClassForFavourites position) {
        Intent j=new Intent(this,NewDetailofMovie.class);
        j.putExtra("poster_path",position.poster_path);
        j.putExtra("id",position.id);
        j.putExtra("title",position.title);
        startActivity(j);
    }
}
