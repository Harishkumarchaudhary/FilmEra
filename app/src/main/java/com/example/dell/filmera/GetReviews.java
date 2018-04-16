package com.example.dell.filmera;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetReviews extends AppCompatActivity {
    int movie_id;
    RecyclerView recyclerView;
    AdapterForReviews adapterForReviews;
    ArrayList<ApiClassForReviews.Data3> data3ArrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_reviews);
        Intent k=getIntent();
        movie_id=k.getIntExtra("movie_id",0);
        adapterForReviews=new AdapterForReviews(this,data3ArrayList);
        recyclerView=findViewById(R.id.listyu);
        recyclerView.setAdapter(adapterForReviews);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        Retrofit retrofit=new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("https://api.themoviedb.org/3/").build();
        Retrofit_Interface retrofit_interface=retrofit.create(Retrofit_Interface.class);
        Call<ApiClassForReviews> call=retrofit_interface.getresponse3(movie_id);
        call.enqueue(new Callback<ApiClassForReviews>() {
            @Override
            public void onResponse(Call<ApiClassForReviews> call, Response<ApiClassForReviews> response) {
                ApiClassForReviews apiClassForReviews=response.body();
                for(int i=0;i<apiClassForReviews.results.size();i++) {
                    data3ArrayList.add(apiClassForReviews.results.get(i));
                }
                adapterForReviews.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ApiClassForReviews> call, Throwable t) {
                Toast.makeText(GetReviews.this, "OOPS ,AN ERROR OCCURED !", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
