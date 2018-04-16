package com.example.dell.filmera;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CastDetailofTv extends AppCompatActivity {
    CircleImageView circleImageView;
    TextView name, birthda, born_place, biography;
    RecyclerView recycler;
    ArrayList<ApiClassForTvMemberOtherShows.MemberOtherMovies> arrayList = new ArrayList<>();
    AdapterForTvMemberOtherMovies adapterForMemberOtherMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast_detail2);
        Intent j = getIntent();
        int id = j.getIntExtra("id", 0);
        String poster_path = j.getStringExtra("profile");
        String name_of_m = j.getStringExtra("name");
        circleImageView = findViewById(R.id.circle_image_view);
        recycler = findViewById(R.id.recycler);
        name = findViewById(R.id.name);
        birthda = findViewById(R.id.age);
        born_place = findViewById(R.id.born_place);
        biography = findViewById(R.id.biography);
        name.setText(name_of_m);
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + poster_path).fit().into(circleImageView);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/").addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_Interface retrofit_interface = retrofit.create(Retrofit_Interface.class);
        Call<ApiClassForMembers> call = retrofit_interface.getMemberDetails(id);
        call.enqueue(new Callback<ApiClassForMembers>() {
            @Override
            public void onResponse(Call<ApiClassForMembers> call, Response<ApiClassForMembers> response) {
                ApiClassForMembers apiClassForMembers = response.body();
                if (apiClassForMembers.birthday != null && !apiClassForMembers.birthday.isEmpty()) {
                    birthda.setText(apiClassForMembers.birthday);
                } else {
                    birthda.setText("Not Known");
                }
                born_place.setText(apiClassForMembers.place_of_birth);
                biography.setText(apiClassForMembers.biography);
            }

            @Override
            public void onFailure(Call<ApiClassForMembers> call, Throwable t) {
                Toast.makeText(CastDetailofTv.this, "OOPS AN ERROR OCCURED", Toast.LENGTH_SHORT).show();
            }
        });
        adapterForMemberOtherMovies = new AdapterForTvMemberOtherMovies(this, arrayList);
        recycler.setAdapter(adapterForMemberOtherMovies);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        Call<ApiClassForTvMemberOtherShows> call2 = retrofit_interface.getOtherTvShows(id);
        call2.enqueue(new Callback<ApiClassForTvMemberOtherShows>() {
            @Override
            public void onResponse(Call<ApiClassForTvMemberOtherShows> call, Response<ApiClassForTvMemberOtherShows> response) {
                ApiClassForTvMemberOtherShows apiClassForMembers = response.body();
                for (int i = 0; i < apiClassForMembers.cast.size(); i++) {
                    arrayList.add(apiClassForMembers.cast.get(i));

                }
                adapterForMemberOtherMovies.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ApiClassForTvMemberOtherShows> call, Throwable t) {
                Toast.makeText(CastDetailofTv.this, "OOPS AN ERROR OCCURED", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
