package com.example.skwow.mcproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

public class SportsFragment extends Fragment{

    RecyclerView recyclerView;
    MovieAdapter adapter;
    List<Movie> sportslist = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.movie_frament, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        sportslist.add(
                new Movie(1, "Iron Man", 4.4, "book", "Hindi", R.drawable.ironman)
        );
        sportslist.add(
                new Movie(2, "Hunger Games", 4.4, "book", "English", R.drawable.hungergames)
        );


        adapter = new MovieAdapter(getActivity(), sportslist);

        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }
}
