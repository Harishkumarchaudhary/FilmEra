package com.example.dell.filmera;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class TvBlankFragment extends Fragment {

    RecyclerView recyclerView,recycle2,recycle3;
    AdapterForTvOnTheAir adapterForTvOnTheAir;
    AdapterForTvTopRated adapterForTvTopRated;
    AdapterForTvPoplar adapterForTvPoplar;
    ArrayList<ApiClassForTvOnTheAir.Data> arrayList=new ArrayList<>();
    ArrayList<ApiClassForTvPopular.Data> arrayList2=new ArrayList<>();
    ArrayList<ApiClassForTvTopRated.Data> arrayList3=new ArrayList<>();

    public TvBlankFragment() {
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
        View view= inflater.inflate(R.layout.fragment_tv_blank, container, false);
        recyclerView=view.findViewById(R.id.recycle);
        recycle2=view.findViewById(R.id.recycle2);
        recycle3=view.findViewById(R.id.recycle3);
        adapterForTvOnTheAir=new AdapterForTvOnTheAir(getContext(),arrayList);
        adapterForTvPoplar=new AdapterForTvPoplar(getContext(),arrayList2);
        adapterForTvTopRated=new AdapterForTvTopRated(getContext(),arrayList3);
        recyclerView.setAdapter(adapterForTvOnTheAir);
        recycle2.setAdapter(adapterForTvPoplar);
        recycle3.setAdapter(adapterForTvTopRated);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        recycle2.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        recycle3.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/").addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_Interface retrofit_interface=retrofit.create(Retrofit_Interface.class);
        Call<ApiClassForTvOnTheAir> call=retrofit_interface.getTvOnTheAir();
        call.enqueue(new Callback<ApiClassForTvOnTheAir>() {
            @Override
            public void onResponse(Call<ApiClassForTvOnTheAir> call, Response<ApiClassForTvOnTheAir> response) {
                ApiClassForTvOnTheAir apiClassForTvOnTheAir=response.body();
                for(int i=0;i<apiClassForTvOnTheAir.results.size();i++){
                    arrayList.add(apiClassForTvOnTheAir.results.get(i));
                }
                adapterForTvOnTheAir.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ApiClassForTvOnTheAir> call, Throwable t) {
                Toast.makeText(getContext(), "OOPS AN ERROR OCCURED", Toast.LENGTH_SHORT).show();
            }
        });
        Call<ApiClassForTvPopular> call1=retrofit_interface.getTvPopular();
        call1.enqueue(new Callback<ApiClassForTvPopular>() {
            @Override
            public void onResponse(Call<ApiClassForTvPopular> call, Response<ApiClassForTvPopular> response) {
                ApiClassForTvPopular apiClassForTvPopular=response.body();
                for(int i=0;i<apiClassForTvPopular.results.size();i++){
                    arrayList2.add(apiClassForTvPopular.results.get(i));
                }
                adapterForTvPoplar.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ApiClassForTvPopular> call, Throwable t) {
                Toast.makeText(getContext(), "OOPS AN ERROR OCCURED", Toast.LENGTH_SHORT).show();
            }
        });
        Call<ApiClassForTvTopRated> call2=retrofit_interface.getTvTopRated();
        call2.enqueue(new Callback<ApiClassForTvTopRated>() {
            @Override
            public void onResponse(Call<ApiClassForTvTopRated> call, Response<ApiClassForTvTopRated> response) {
                ApiClassForTvTopRated apiClassForTvTopRated=response.body();
                for(int i=0;i<apiClassForTvTopRated.results.size();i++){
                    arrayList3.add(apiClassForTvTopRated.results.get(i));
                }
                adapterForTvTopRated.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ApiClassForTvTopRated> call, Throwable t) {
                Toast.makeText(getContext(), "OOPS AN ERROR OCCURED", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
