package com.example.dell.filmera;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Callable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewDetailofMovie extends AppCompatActivity implements AdapterForSimilarMovies.onGo {
  ImageView imageView1,imageView2;
  TextView textView;
  RecyclerView casting,similarmovies,videos;
  ArrayList<ApiClassForCast.DataCast> arrayList=new ArrayList<>();
  ArrayList<ApiClassForSimilarMovies.SimilarData> similarDataArrayList=new ArrayList<>();
  ArrayList<ApiClassForVideos.Data4> videarraylist=new ArrayList<>();
  AdapterforMovieTrailors adapterforMovieTrailors;
  AdapterForSimilarMovies adapterForSimilarMovies;
  AdapterForCasting adapterForCasting;
  Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_detailof_movie);
        Intent j=getIntent();
        Bundle bundle=new Bundle();
        int id=j.getIntExtra("id",-1);
        bundle.putInt("id",id);
        bundle.putString("title",j.getStringExtra("title"));
        bundle.putString("poster_path",j.getStringExtra("poster_path"));
        FragmentSimilar fragmentSimilar=new FragmentSimilar();
        fragmentSimilar.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container,fragmentSimilar).commit();
    }

    @Override
    public void go_on(Bundle bundle) {
        FragmentSimilar fragmentSimilar=new FragmentSimilar();
        fragmentSimilar.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container,fragmentSimilar).addToBackStack("hey").commit();
    }
}
