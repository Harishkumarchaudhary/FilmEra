package com.example.dell.filmera;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NewDetailofTv extends AppCompatActivity implements AdapterForSimilarTvShows.onGo {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_detailof_tv);
        Intent j=getIntent();
        Bundle bundle=new Bundle();
        int id=j.getIntExtra("id",-1);
        bundle.putInt("id",id);
        bundle.putString("title",j.getStringExtra("title"));
        bundle.putString("poster_path",j.getStringExtra("poster_path"));
        NewTvFragment fragmentSimilar=new NewTvFragment();
        fragmentSimilar.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container,fragmentSimilar).commit();
    }

    @Override
    public void go_on(Bundle bundle) {
        NewTvFragment fragmentSimilar=new NewTvFragment();
        fragmentSimilar.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container,fragmentSimilar).commit();
    }
}
