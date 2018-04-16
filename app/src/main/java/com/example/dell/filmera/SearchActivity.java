package com.example.dell.filmera;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {
RecyclerView recyclerView;
AdapterForSearch adapterForSearch;
ArrayList<ApiClassForSearch.SearchData> arrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Intent j=getIntent();
        String raw_query =j.getStringExtra("query");
        adapterForSearch=new AdapterForSearch(this,arrayList);
        recyclerView=findViewById(R.id.recycle);
        recyclerView.setAdapter(adapterForSearch);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/").addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_Interface retrofit_interface=retrofit.create(Retrofit_Interface.class);
        Call<ApiClassForSearch> call=retrofit_interface.getSearch(raw_query);
        call.enqueue(new Callback<ApiClassForSearch>() {
            @Override
            public void onResponse(Call<ApiClassForSearch> call, Response<ApiClassForSearch> response) {
                ApiClassForSearch apiClassForSearch=response.body();
                for(int i=0;i<apiClassForSearch.results.size();i++){
                    arrayList.add(apiClassForSearch.results.get(i));
                }
                adapterForSearch.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ApiClassForSearch> call, Throwable t) {
                Toast.makeText(SearchActivity.this, "OOPS AN ERROR OCCURED", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
