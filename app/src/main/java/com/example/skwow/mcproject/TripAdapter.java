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

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.TripViewHolder>{

    private Context context;
    private List<Trip> tripList;

    public TripAdapter(Context context, List<Trip> tripList) {
        this.context = context;
        this.tripList = tripList;
    }

    @NonNull
    @Override
    public TripAdapter.TripViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_layout_trip, null);
        return new TripAdapter.TripViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TripAdapter.TripViewHolder tripViewHolder, int i) {

        Trip event = tripList.get(i);
        tripViewHolder.textViewTitle.setText(event.getTitle());


        tripViewHolder.imageView.setImageDrawable(context.getResources().getDrawable(event.getImage()));
    }

    @Override
    public int getItemCount() {
        return tripList.size();
    }

    class TripViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textViewTitle, textViewVenue;
        Button book;

        public TripViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            book = itemView.findViewById(R.id.book);
        }
    }
}
