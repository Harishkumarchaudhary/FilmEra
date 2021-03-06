package com.example.dell.filmera;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewTvFragment extends Fragment {

    ImageView imageView1,imageView2;
    TextView textView;
    RecyclerView casting,similarmovies,videos;
    ArrayList<ApiClassForCast.DataCast> arrayList=new ArrayList<>();
    ArrayList<ApiClassForSimilarTvShows.SimilarData> similartvDataArrayList=new ArrayList<>();
    ArrayList<ApiClassForVideos.Data4> videarraylist=new ArrayList<>();
    AdapterforMovieTrailors adapterforMovieTrailors;
    AdapterForSimilarTvShows adapterForSimilarMovies;
    AdapterForCasting adapterForCasting;
    public NewTvFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_new_tv, container, false);
        Bundle bundle=getArguments();
        casting=view.findViewById(R.id.cast_members);
        similarmovies=view.findViewById(R.id.similar_movies);
        videos=view.findViewById(R.id.trailors);
        adapterForCasting=new AdapterForCasting(getContext(),arrayList);
        adapterForSimilarMovies=new AdapterForSimilarTvShows(getContext(),similartvDataArrayList, (AdapterForSimilarTvShows.onGo) getContext());
        casting.setAdapter(adapterForCasting);
        casting.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        similarmovies.setAdapter(adapterForSimilarMovies);
        similarmovies.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        String poster_path=bundle.getString("poster_path");
        String title=bundle.getString("title");
        int id=bundle.getInt("id",-1);
        Random random=new Random(256);
        imageView1=view.findViewById(R.id.image1);
        imageView2=view.findViewById(R.id.image2);
        textView=view.findViewById(R.id.texty);
        Picasso.get().load("https://image.tmdb.org/t/p/w500/"+poster_path).fit().into(imageView1);
        Picasso.get().load("https://image.tmdb.org/t/p/w500/"+poster_path).into(imageView2);
        textView.setText(title);
        Retrofit retrofit=new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("https://api.themoviedb.org/3/").build();
        Retrofit_Interface retrofit_interface=retrofit.create(Retrofit_Interface.class);
        Call<ApiClassForCast> call=retrofit_interface.gettvcast(id);
        call.enqueue(new Callback<ApiClassForCast>() {
            @Override
            public void onResponse(Call<ApiClassForCast> call, Response<ApiClassForCast> response) {
                ApiClassForCast apiClassForCast=response.body();
                for(int i=0;i<apiClassForCast.cast.size();i++){
                    arrayList.add(apiClassForCast.cast.get(i));
                }
                adapterForCasting.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ApiClassForCast> call, Throwable t) {
                Toast.makeText(getContext(), "OOPS AN ERROR OCCURED", Toast.LENGTH_SHORT).show();
            }
        });
        Call<ApiClassForSimilarTvShows> calll=retrofit_interface.getSimilarTvsows(id);
        calll.enqueue(new Callback<ApiClassForSimilarTvShows>() {
            @Override
            public void onResponse(Call<ApiClassForSimilarTvShows> call, Response<ApiClassForSimilarTvShows> response) {
                ApiClassForSimilarTvShows apiClassForSimilarTvShows=response.body();
                for(int i=0;i<apiClassForSimilarTvShows.results.size();i++){
                    similartvDataArrayList.add(apiClassForSimilarTvShows.results.get(i));
                }
                adapterForSimilarMovies.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ApiClassForSimilarTvShows> call, Throwable t) {
                Toast.makeText(getContext(), "OOPS AN ERROR OCCURED", Toast.LENGTH_SHORT).show();
            }
        });
        adapterforMovieTrailors=new AdapterforMovieTrailors(getContext(),videarraylist);
        videos.setAdapter(adapterforMovieTrailors);
        videos.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        Call<ApiClassForVideos> call3=retrofit_interface.getTvVideos(id);
        call3.enqueue(new Callback<ApiClassForVideos>() {
            @Override
            public void onResponse(Call<ApiClassForVideos> call, Response<ApiClassForVideos> response) {
                ApiClassForVideos apiClassForVideos=response.body();
                for(int i=0;i<apiClassForVideos.results.size();i++){
                    videarraylist.add(apiClassForVideos.results.get(i));
                }
                adapterforMovieTrailors.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ApiClassForVideos> call, Throwable t) {
                Toast.makeText(getContext(), "OOPS AN ERROR OCCURED", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
