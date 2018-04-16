package com.example.dell.filmera;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Dell on 10-04-2018.
 */

public class AdapterForCasting extends RecyclerView.Adapter<AdapterForCasting.UserViewHolder>{

   ArrayList<ApiClassForCast.DataCast> cast;
   Context context;
   AdapterForCasting(Context context,ArrayList<ApiClassForCast.DataCast> cast){
       this.context=context;
       this.cast=cast;
   }

    @Override
    public UserViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.layout_for_casting,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, final int position) {
           holder.name.setText(cast.get(position).name);
           String path=cast.get(position).profile_path;
        Picasso.get().load("https://image.tmdb.org/t/p/w500"+path).fit().into(holder.circledImageView);
        holder.circledImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(context,CastDetailActivity.class);
                j.putExtra("name",cast.get(position).name);
                j.putExtra("id",cast.get(position).id);
                j.putExtra("profile",cast.get(position).profile_path);
                context.startActivity(j);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cast.size();
    }
    public class UserViewHolder extends RecyclerView.ViewHolder{
       TextView name;
       CircleImageView circledImageView;
        public UserViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.textu);
            circledImageView=itemView.findViewById(R.id.circle_image_view);
        }
    }
}
