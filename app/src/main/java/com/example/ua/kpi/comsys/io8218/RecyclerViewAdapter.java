package com.example.ua.kpi.comsys.io8218;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Film> mData; //listFilm

    public RecyclerViewAdapter(Context mContext, List<Film> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_films, parent, false);
        MyViewHolder vHolder = new MyViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Film film = mData.get(position);

        holder.name.setText(mData.get(position).getFilmName());
        holder.year.setText(mData.get(position).getFilmYear());
        holder.type.setText(mData.get(position).getFilmType());
        Glide.with(mContext).load(mData.get(position).getFilmPoster()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView year;
        private TextView type;
        private ImageView image;
        LinearLayout parentLayout;

        public MyViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name_film);
            year = itemView.findViewById(R.id.year_film);
            type = itemView.findViewById(R.id.type_film);
            image = itemView.findViewById(R.id.image_film);
        }
    }

    public void filterList(ArrayList<Film> filteredList) {
        mData = filteredList;
        notifyDataSetChanged();
    }
}
