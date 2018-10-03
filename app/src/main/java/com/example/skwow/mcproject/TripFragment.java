package com.example.skwow.mcproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class TripFragment extends Fragment {

    RecyclerView recyclerView;
    TripAdapter adapter;
    List<Trip> tripList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.movie_frament, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        tripList.add(
                new Trip(1, "Manali", "book", 4.5, R.drawable.manali)
        );
        tripList.add(
                new Trip(1, "Goa", "book", 4.5, R.drawable.goa)
        );

        tripList.add(
                new Trip(1, "Kashmir", "book", 4.5, R.drawable.kashmir)
        );



        adapter = new TripAdapter(getActivity(), tripList);

        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }
}
