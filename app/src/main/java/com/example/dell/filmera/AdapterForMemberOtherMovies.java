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

public class AdapterForMemberOtherMovies extends RecyclerView.Adapter<AdapterForMemberOtherMovies.UserViewHoldy> {
    ArrayList<ApiClassForMemberOtherMovies.MemberOtherMovies> arrayList;
    Context context;
    AdapterForMemberOtherMovies(Context context,ArrayList<ApiClassForMemberOtherMovies.MemberOtherMovies> arrayList){
        this.arrayList=arrayList;
        this.context=context;
    }
    @NonNull
    @Override
    public UserViewHoldy onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.top_rated_of1_element_layout,parent,false);
        return new UserViewHoldy(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserViewHoldy holder, final int position) {
        String s=arrayList.get(position).title;
        holder.textView.setText(s);
        String image_path=arrayList.get(position).poster_path;
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(context,NewDetailofMovie.class);
                j.putExtra("id",arrayList.get(position).id);
                j.putExtra("title",arrayList.get(position).title);
                j.putExtra("poster_path",arrayList.get(position).poster_path);
                context.startActivity(j);
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
                ApiClassForFavourites apiClassForFavourites=new ApiClassForFavourites(arrayList.get(position).id,arrayList.get(position).title,arrayList.get(position).poster_path);
                moviesDao.insertfavourites(apiClassForFavourites);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    class UserViewHoldy extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView,image2;
        public UserViewHoldy(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text3);
            imageView=itemView.findViewById(R.id.image);
            image2=itemView.findViewById(R.id.image2);
        }
        }
    }
