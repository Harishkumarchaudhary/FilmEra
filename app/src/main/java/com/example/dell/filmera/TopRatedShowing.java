package com.example.dell.filmera;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TopRatedShowing extends AppCompatActivity {
    RecyclerView recyclerView;
    AdapterForTopRated adapterForTopRated;
    ArrayList<Api_class_for_topRated.Data2> arrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_rated_showing);
        recyclerView=findViewById(R.id.recycle);
     //   adapterForTopRated=new AdapterForTopRated(this,arrayList);
        recyclerView.setAdapter(adapterForTopRated);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/movie/").addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_Interface retrofit_interface=retrofit.create(Retrofit_Interface.class);
        Call<Api_class_for_topRated> call=retrofit_interface.getresponse2();
        call.enqueue(new Callback<Api_class_for_topRated>() {
            @Override
            public void onResponse(Call<Api_class_for_topRated> call, Response<Api_class_for_topRated> response) {
                     Api_class_for_topRated api_class_for_topRated=response.body();
                     for(int i=0;i<api_class_for_topRated.results.size();i++){
                         arrayList.add(api_class_for_topRated.results.get(i));
                     }
                     adapterForTopRated.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Api_class_for_topRated> call, Throwable t) {
                Log.d("err",t.getMessage());
                Toast.makeText(TopRatedShowing.this,"OOPS ERROR OCCURED !",Toast.LENGTH_LONG).show();
            }
        });
    }
}
