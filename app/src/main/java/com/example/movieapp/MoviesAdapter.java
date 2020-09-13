package com.example.movieapp;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    //private List<Movies> moviesList;

   // public MoviesAdapter(List<Movies> moviesList) {
        //this.moviesList = moviesList;
    //}

    public class ViewHolder extends RecyclerView.ViewHolder{
       public TextView idTv;
       public TextView titleTv;
       public ImageView movieIv;

       public ViewHolder(View itemView) {
           super(itemView);
           idTv = itemView.findViewById(R.id.movie_id);
           titleTv = itemView.findViewById(R.id.movie_title);
           movieIv = itemView.findViewById(R.id.movie_image);
       }
    }

    @NonNull
    @Override
    public MoviesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
