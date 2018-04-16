package com.example.dell.filmera;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
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

public class AdapterForSearch extends RecyclerView.Adapter<AdapterForSearch.UserViewHolder>{
    Context context;
    ArrayList<ApiClassForSearch.SearchData> arrayList;
    AdapterForSearch(Context context, ArrayList<ApiClassForSearch.SearchData> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.layout_for_search,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, final int position) {
           holder.textView.setText(arrayList.get(position).title);
           String path=arrayList.get(position).poster_path;
        Picasso.get().load("https://image.tmdb.org/t/p/w500/"+path).fit().into(holder.imageView);
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
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class UserViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public UserViewHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image);
            textView=itemView.findViewById(R.id.text3);
        }
    }
}
