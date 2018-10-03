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

public class EntertainmentAdapter extends RecyclerView.Adapter<EntertainmentAdapter.EntertainmentViewHolder>{

    private Context context;
    private List<Entertainment> entertainmentList;

    public EntertainmentAdapter(Context context, List<Entertainment> entertainmentList) {
        this.context = context;
        this.entertainmentList= entertainmentList;
    }

    @NonNull
    @Override
    public EntertainmentAdapter.EntertainmentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_layout_entertainment, null);
        return new EntertainmentAdapter.EntertainmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EntertainmentAdapter.EntertainmentViewHolder entertainmentViewHolder, int i) {

        Entertainment event = entertainmentList.get(i);
        entertainmentViewHolder.textViewTitle.setText(event.getTitle());
        entertainmentViewHolder.textViewDescription.setText(event.getDescription());


        entertainmentViewHolder.imageView.setImageDrawable(context.getResources().getDrawable(event.getImage()));
    }

    @Override
    public int getItemCount() {
        return entertainmentList.size();
    }

    class EntertainmentViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textViewTitle, textViewDescription;
        Button book;

        public EntertainmentViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            book = itemView.findViewById(R.id.book);
        }
    }
}

