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
 * Created by Dell on 19-03-2018.
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.UserViewHolder> {
    public interface onClick{
        void do_it(Api_class_for_popular.Data position);
    }
    onClick lisener;
    Context context;
    ArrayList<Api_class_for_popular.Data> arrayList;
    RecycleViewAdapter(Context context, ArrayList<Api_class_for_popular.Data> arrayList,onClick lisener){
        this.context=context;
        this.arrayList=arrayList;
        this.lisener=lisener;
    }
    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=(LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.layout_of_popular,parent,false);
        UserViewHolder userViewHolder=new UserViewHolder(view);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(final UserViewHolder holder, final int position) {
               String title=arrayList.get(position).title;
               holder.textView.setText(title);
               String image=arrayList.get(position).poster_path;
               holder.imageView.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       lisener.do_it(arrayList.get(position));
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
                     ApiClassForFavourites forDatabaseTab=new ApiClassForFavourites(arrayList.get(position).id,arrayList.get(position).title,arrayList.get(position).poster_path);
                     moviesDao.insertfavourites(forDatabaseTab);
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
            textView=view.findViewById(R.id.text);
            imageView=view.findViewById(R.id.image);
            image2=view.findViewById(R.id.image2);
        }
    }
}
