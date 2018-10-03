package com.example.skwow.mcproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SportsAdapter extends RecyclerView.Adapter<com.example.skwow.mcproject.SportsAdapter.SportsViewHolder>{



        private Context context;
        private List<Sports> sportsList;

        public SportsAdapter(Context context, List<Sports> sportsList) {
            this.context = context;
            this.sportsList= sportsList;
        }

        @NonNull
        @Override
        public com.example.skwow.mcproject.SportsAdapter.SportsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.list_layout_sports, null);
            return new com.example.skwow.mcproject.SportsAdapter.SportsViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull com.example.skwow.mcproject.SportsAdapter.SportsViewHolder sportsViewHolder, int i) {

            Sports event = sportsList.get(i);
            sportsViewHolder.textViewTitle.setText(event.getTitle());
            sportsViewHolder.textViewVenue.setText(String.valueOf(event.getVenue()));
            sportsViewHolder.imageView.setImageDrawable(context.getResources().getDrawable(event.getImage()));
        }

        @Override
        public int getItemCount() {
            return sportsList.size();
        }

        class SportsViewHolder extends RecyclerView.ViewHolder{

            ImageView imageView;
            TextView textViewTitle, textViewVenue, textViewRating;
            Button book;

            public SportsViewHolder(@NonNull View itemView) {
                super(itemView);

                imageView = itemView.findViewById(R.id.imageView);
                textViewTitle = itemView.findViewById(R.id.textViewTitle);
                textViewVenue = itemView.findViewById(R.id.textViewVenue);

                book = itemView.findViewById(R.id.book);
            }
        }
    }


