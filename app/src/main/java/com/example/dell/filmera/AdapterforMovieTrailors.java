package com.example.dell.filmera;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.autofill.AutofillValue;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Dell on 11-04-2018.
 */

public class AdapterforMovieTrailors extends RecyclerView.Adapter<AdapterforMovieTrailors.UserViewHolder> {
    ArrayList<ApiClassForVideos.Data4> arrayList;
    Context context;
    AdapterforMovieTrailors(Context context, ArrayList<ApiClassForVideos.Data4> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public AdapterforMovieTrailors.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.layout_for_similar ,parent,false);
        return new AdapterforMovieTrailors.UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterforMovieTrailors.UserViewHolder holder, final int position) {
        holder.textView.setText(arrayList.get(position).name);
        holder.image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.image2.setColorFilter(R.color.yellow);
            }
        });
        String s="http://img.youtube.com/vi/"+arrayList.get(position).key+""+"/mqdefault.jpg";
        Picasso.get().load(s).fit().into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,VideoActivity.class);
                intent.putExtra("key",arrayList.get(position).key);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class UserViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        ImageView image2;
        public UserViewHolder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text);
            imageView=itemView.findViewById(R.id.image);
            image2=itemView.findViewById(R.id.image2);
        }
    }
}
