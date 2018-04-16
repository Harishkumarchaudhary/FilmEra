package com.example.dell.filmera;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Dell on 21-03-2018.
 */

public class AdapterForReviews extends RecyclerView.Adapter<AdapterForReviews.UserHolder> {
    Context context;
    ArrayList<ApiClassForReviews.Data3> data3ArrayList;
    public AdapterForReviews(Context context, ArrayList<ApiClassForReviews.Data3> data3ArrayList) {
        this.context=context;
        this.data3ArrayList=data3ArrayList;
    }

    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.for_reviews_adapter,parent,false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(UserHolder holder, int position) {
        String author=data3ArrayList.get(position).author;
        String s="  :";
        author=author+s;
        holder.textView2.setText(author);
       String content=data3ArrayList.get(position).content;
        holder.textView.setText(content);
    }

    @Override
    public int getItemCount() {
        return data3ArrayList.size();
    }
    public class UserHolder extends RecyclerView.ViewHolder{
        TextView textView;
        TextView textView2;
        public UserHolder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text_view);
           textView2=itemView.findViewById(R.id.author);
        }
    }
}
