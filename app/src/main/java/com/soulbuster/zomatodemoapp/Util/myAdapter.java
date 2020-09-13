package com.soulbuster.zomatodemoapp.Util;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.soulbuster.zomatodemoapp.Model.Restaurant;
import com.soulbuster.zomatodemoapp.R;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.MyViewHolder> {

    private static final String TAG = "myAdapter";
    ArrayList<Restaurant> restaurants;
    Context context;
    ItemClickListener listener;

    public myAdapter(ArrayList<Restaurant> restaurants, Context context,ItemClickListener listener) {
        this.restaurants = restaurants;
        this.context=context;
        this.listener=listener;
        Log.e(TAG, "Count in adapter "+restaurants.size());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {

        Restaurant currentRes = restaurants.get(position);

        myViewHolder.title.setText(currentRes.getName());
        myViewHolder.rating.setText(currentRes.getRating());

        Glide.with(context).load(currentRes.getImage()).centerCrop().into(myViewHolder.poster);

    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,rating;
        public ImageView poster;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.item_title);
            rating=(TextView)itemView.findViewById(R.id.item_rating);
            poster=(ImageView)itemView.findViewById(R.id.item_poster);
            itemView.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    listener.onItemClicked(getAdapterPosition());
                }
            });
        }
    }
}

