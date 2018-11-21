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
    SportsAdapter adapter;
    List<Sports> sportslist;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sportslist = new ArrayList<>();
        View view = inflater.inflate(R.layout.movie_frament, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewEvent);

        sportslist.add(
                new Sports(1, "Football", "Sports block", "book", R.drawable.football)

        );
        sportslist.add(
                new Sports(2, "Badminton", "Sports block", "book", R.drawable.badminton)
        );


        adapter = new SportsAdapter(getActivity(), sportslist);

        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }
}
