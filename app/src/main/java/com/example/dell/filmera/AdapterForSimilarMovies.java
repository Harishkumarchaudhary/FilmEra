package com.example.dell.filmera;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.media.CamcorderProfile.get;

/**
 * Created by Dell on 10-04-2018.
 */

public class AdapterForSimilarMovies extends RecyclerView.Adapter<AdapterForSimilarMovies.UserViewHolder> {
    Context context;
    ArrayList<ApiClassForSimilarMovies.SimilarData> arrayList;
    public interface onGo{
        void go_on(Bundle bundle);
    }
    onGo listener;
    AdapterForSimilarMovies(Context context, ArrayList<ApiClassForSimilarMovies.SimilarData> arrayList,onGo listener){
        this.context=context;
        this.arrayList=arrayList;
        this.listener=listener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.layout_for_similar ,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder holder, final int position) {
               holder.textView.setText(arrayList.get(position).title);
               String path=arrayList.get(position).poster_path;
        Picasso.get().load("https://image.tmdb.org/t/p/w500/"+path).fit().into(holder.imageView);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.image.setColorFilter(R.color.yellow);
                DatabaseBaseClass databaseBaseClass= Room.databaseBuilder(context.getApplicationContext(),DatabaseBaseClass.class,"Data")
                        .allowMainThreadQueries().build();
                final MoviesDao moviesDao=databaseBaseClass.getMovieDao();
                ApiClassForFavourites forDatabaseTable=new ApiClassForFavourites(arrayList.get(position).id,arrayList.get(position).title,arrayList.get(position).poster_path);
                moviesDao.insertfavourites(forDatabaseTable);
            }
        });
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putInt("id",arrayList.get(position).id);
                bundle.putString("title",arrayList.get(position).title);
                bundle.putString("poster_path",arrayList.get(position).poster_path);
               listener.go_on(bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        ImageView image;
        public UserViewHolder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text);
            imageView=itemView.findViewById(R.id.image);
            image=itemView.findViewById(R.id.image2);
        }
    }
}
