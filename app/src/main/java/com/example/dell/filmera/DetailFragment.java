package com.example.dell.filmera;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {
    int movie_id;
    RecyclerView recyclerView;
    AdapterForReviews adapterForReviews;
    ArrayList<ApiClassForReviews.Data3> data3ArrayList=new ArrayList<>();

    public DetailFragment() {
        // Required empty public constructor
    }
//    TextView textView;
//    ImageView imageView;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view=inflater.inflate(R.layout.fragment_detail, container, false);
//        textView=view.findViewById(R.id.name);
//        imageView=view.findViewById(R.id.avatar);
//        Bundle bundle=getArguments();
//        String name=bundle.getString("name");
//        String image=bundle.getString("avatar");
//        textView.setText(name);
//        String s="https://image.tmdb.org/t/p/w500/"+image;
//        Log.d("url",s);
//        Picasso.get().load(s).into(imageView);
        Bundle bundle=getArguments();
        movie_id=bundle.getInt("movie_id");
        adapterForReviews=new AdapterForReviews(getContext(),data3ArrayList);
        recyclerView=view.findViewById(R.id.listyu);
        recyclerView.setAdapter(adapterForReviews);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
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
                Toast.makeText(getContext(), "OOPS ,AN ERROR OCCURED !", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
