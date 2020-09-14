package com.example.movieapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private List<Movies> moviesList;

    public MoviesAdapter(List<Movies> moviesList) {
        this.moviesList = moviesList;
    }

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
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.items_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.ViewHolder holder, int position) {
        Movies movies = moviesList.get(position);
        TextView idTextView = holder.idTv;
        idTextView.setText(movies.getId());
        TextView titleTextView = holder.titleTv;
        titleTextView.setText(movies.getTitle());


    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

}
