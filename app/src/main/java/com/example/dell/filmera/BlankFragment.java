package com.example.dell.filmera;


import android.annotation.SuppressLint;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {
    RecyclerView recyclerView,recycle2,recycle3;
    RecycleViewAdapter recycleViewAdapter;
    AdapterForTopRated adapterForTopRated;
    AdapterForUpcoming adapterForUpcoming;
    TextView text;
    ArrayList<ApiClassForUpcoming.UpcomingData> upcomingDataArrayList=new ArrayList<>();
    ArrayList<Api_class_for_popular.Data> arrayList=new ArrayList<>();
    ArrayList<Api_class_for_topRated.Data2> data2ArrayList=new ArrayList<>();
    public BlankFragment() {
        // Required empty public constructor
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_blank, container, false);
        recyclerView=view.findViewById(R.id.recycle);
        recycle2=view.findViewById(R.id.recycle2);
        recycle3=view.findViewById(R.id.recycle3);
        adapterForTopRated=new AdapterForTopRated(getContext(),data2ArrayList, (AdapterForTopRated.onClick) getContext());
        recycle2.setAdapter(adapterForTopRated);
        recycle2.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
//        DatabaseBaseClass databaseBaseClass= Room.databaseBuilder(getContext().getApplicationContext(),DatabaseBaseClass.class,"Data")
//                .allowMainThreadQueries().build();
//        final MoviesDao moviesDao=databaseBaseClass.getMovieDao();
//        arrayList.clear();
//        arrayList.addAll(moviesDao.getAllPopularMovies());
        recycleViewAdapter=new RecycleViewAdapter(getContext(),arrayList, (RecycleViewAdapter.onClick) getContext());
        recyclerView.setAdapter(recycleViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://api.themoviedb.org/3/").addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_Interface retrofit_interface=retrofit.create(Retrofit_Interface.class);
        Call<Api_class_for_popular> call=retrofit_interface.getresponse();
        call.enqueue(new Callback<Api_class_for_popular>() {
            @Override
            public void onResponse(Call<Api_class_for_popular> call, Response<Api_class_for_popular> response) {
                Api_class_for_popular api_classForpopular =response.body();
                for(int i = 0; i< api_classForpopular.results.size(); i++){
                    arrayList.add(api_classForpopular.results.get(i));
                   // moviesDao.insertMovie(arrayList.get(i));
                }
                recycleViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Api_class_for_popular> call, Throwable t) {
                Toast.makeText(getContext(), "OOPS , NO INTERNET  !!", Toast.LENGTH_SHORT).show();
            }
        });
        Call<Api_class_for_topRated> call1=retrofit_interface.getresponse2();
        call1.enqueue(new Callback<Api_class_for_topRated>() {
            @Override
            public void onResponse(Call<Api_class_for_topRated> call, Response<Api_class_for_topRated> response) {
                    Api_class_for_topRated api_class_for_topRated=response.body();
                    for(int i=0;i<api_class_for_topRated.results.size();i++){
                        data2ArrayList.add(api_class_for_topRated.results.get(i));
                    }
                    adapterForTopRated.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Api_class_for_topRated> call, Throwable t) {

            }
        });
        adapterForUpcoming=new AdapterForUpcoming(getContext(),upcomingDataArrayList, (AdapterForUpcoming.onClick) getContext());
        recycle3.setAdapter(adapterForUpcoming);
        recycle3.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        Call<ApiClassForUpcoming> call2=retrofit_interface.getUpcoming();
        call2.enqueue(new Callback<ApiClassForUpcoming>() {
            @Override
            public void onResponse(Call<ApiClassForUpcoming> call, Response<ApiClassForUpcoming> response) {
                ApiClassForUpcoming apiClassForUpcoming=response.body();
                for(int i=0;i<apiClassForUpcoming.results.size();i++){
                    upcomingDataArrayList.add(apiClassForUpcoming.results.get(i));
                }
                adapterForUpcoming.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ApiClassForUpcoming> call, Throwable t) {
                Toast.makeText(getContext(), "OOPS AN ERROR OCCURED !!", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_blank, container, false);
    }

}
