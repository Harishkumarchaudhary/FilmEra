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
import java.util.List;

/**
 * Created by Dell on 12-04-2018.
 */

public class AdapterForFavourites extends RecyclerView.Adapter<AdapterForFavourites.UserViewHolder>  {
    Context context;
    List<ApiClassForFavourites> arrayList;
    public interface onClick{
        void do_that(ApiClassForFavourites position);
    }
    AdapterForFavourites.onClick onClickListener;
    AdapterForFavourites(Context context, List<ApiClassForFavourites> arrayList, AdapterForFavourites.onClick onClickListener){
        this.context=context;
        this.arrayList=arrayList;
        this.onClickListener=onClickListener;
    }

    @Override
    public AdapterForFavourites.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.layout_for_favourites,parent,false);
        AdapterForFavourites.UserViewHolder userViewHolder=new AdapterForFavourites.UserViewHolder(view);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(final AdapterForFavourites.UserViewHolder holder, final int position) {
        String s=arrayList.get(position).title;
        holder.textView.setText(s);
        String image_path=arrayList.get(position).poster_path;
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.do_that(arrayList.get(position));
            }
        });
        Picasso.get().load("https://image.tmdb.org/t/p/w500/"+image_path).fit().into(holder.imageView);
        holder.image2.setColorFilter(R.color.yellow);

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
