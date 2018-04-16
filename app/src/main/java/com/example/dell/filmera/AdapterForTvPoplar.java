package com.example.dell.filmera;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Dell on 13-04-2018.
 */

public class AdapterForTvPoplar extends RecyclerView.Adapter<AdapterForTvPoplar.UserHolder> {
    ArrayList<ApiClassForTvPopular.Data> arrayList;
    Context context;
    AdapterForTvPoplar(Context context,ArrayList<ApiClassForTvPopular.Data> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }
    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.layout_of_popular,parent,false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserHolder holder, final int position) {
        String title=arrayList.get(position).name;
        holder.textView.setText(title);
        String image=arrayList.get(position).poster_path;
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                      //To Do
                Intent j=new Intent(context,NewDetailofTv.class);
                j.putExtra("id",arrayList.get(position).id);
                j.putExtra("title",arrayList.get(position).name);
                j.putExtra("poster_path",arrayList.get(position).poster_path);
                context.startActivity(j);
            }
        });
        String s="https://image.tmdb.org/t/p/w500/"+image;
        Picasso.get().load(s).fit().into(holder.imageView);
        holder.image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.image2.setColorFilter(R.color.yellow);
                DatabaseBaseClass databaseBaseClass= Room.databaseBuilder(context.getApplicationContext(),DatabaseBaseClass.class,"Data")
                        .allowMainThreadQueries().build();
                final MoviesDao moviesDao=databaseBaseClass.getMovieDao();
                ApiClassForFavourites forDatabaseTable=new ApiClassForFavourites(arrayList.get(position).id,arrayList.get(position).name,arrayList.get(position).poster_path);
                moviesDao.insertfavourites(forDatabaseTable);
            }
        });
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class UserHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView,image2;
        public UserHolder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text);
            imageView=itemView.findViewById(R.id.image);
            image2=itemView.findViewById(R.id.image2);
        }
    }
}
