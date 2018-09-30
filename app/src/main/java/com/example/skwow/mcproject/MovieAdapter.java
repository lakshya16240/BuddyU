package com.example.skwow.mcproject;

import android.content.Context;
import android.media.midi.MidiOutputPort;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{

    private Context context;
    private List<Movie> movieList;

    public MovieAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_layout, null);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {

        Movie event = movieList.get(i);
        movieViewHolder.textViewTitle.setText(event.getTitle());
        movieViewHolder.textViewRating.setText(String.valueOf(event.getRating()));
        movieViewHolder.textViewLanguage.setText(event.getLanguage());
        movieViewHolder.imageView.setImageDrawable(context.getResources().getDrawable(event.getImage()));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textViewTitle, textViewLanguage, textViewRating;
        Button book;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewLanguage = itemView.findViewById(R.id.textViewLanguage);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            book = itemView.findViewById(R.id.book);
        }
    }
}
