package com.example.dell.filmera;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Dell on 11-04-2018.
 */

public class AdapterForUpcoming extends RecyclerView.Adapter<AdapterForUpcoming.UserViewHolder>  {
    Context context;
    ArrayList<ApiClassForUpcoming.UpcomingData> arrayList;
    public interface onClick{
        void do_up(ApiClassForUpcoming.UpcomingData position);
    }
    AdapterForUpcoming.onClick onClickListener;
    AdapterForUpcoming(Context context, ArrayList<ApiClassForUpcoming.UpcomingData> arrayList, AdapterForUpcoming.onClick onClickListener){
        this.context=context;
        this.arrayList=arrayList;
        this.onClickListener=onClickListener;
    }



    @Override
    public AdapterForUpcoming.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.top_rated_of1_element_layout,parent,false);
        AdapterForUpcoming.UserViewHolder userViewHolder=new AdapterForUpcoming.UserViewHolder(view);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(final AdapterForUpcoming.UserViewHolder holder, final int position) {
        String s=arrayList.get(position).title;
        holder.textView.setText(s);
        String image_path=arrayList.get(position).poster_path;
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.do_up(arrayList.get(position));
            }
        });
        Picasso.get().load("https://image.tmdb.org/t/p/w500/"+image_path).fit().into(holder.imageView);
        holder.image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.image2.setColorFilter(R.color.yellow);
                DatabaseBaseClass databaseBaseClass= Room.databaseBuilder(context.getApplicationContext(),DatabaseBaseClass.class,"Data")
                        .allowMainThreadQueries().build();
                final MoviesDao moviesDao=databaseBaseClass.getMovieDao();
                ApiClassForFavourites forDatabaseTable=new ApiClassForFavourites(arrayList.get(position).id,arrayList.get(position).title,arrayList.get(position).poster_path);
                moviesDao.insertfavourites(forDatabaseTable);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    class UserViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView,image2;
        UserViewHolder(View view){
            super(view);
            textView=view.findViewById(R.id.text3);
            imageView=view.findViewById(R.id.image);
            image2=view.findViewById(R.id.image2);
        }
    }
}
