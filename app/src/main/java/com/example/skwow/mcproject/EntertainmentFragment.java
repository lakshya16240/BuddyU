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
import java.util.ArrayList;
import java.util.List;

public class EntertainmentFragment extends Fragment {

    RecyclerView recyclerView;
    EntertainmentAdapter adapter;
    List<Entertainment> entertainmentlist;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        entertainmentlist = new ArrayList<>();
        View view = inflater.inflate(R.layout.movie_frament, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewEvent);

        entertainmentlist.add(
                new Entertainment(1, "Paintball", "book", "A sport in which players eliminate opponents by firing colours.", R.drawable.paintball)

        );
        entertainmentlist.add(
                new Entertainment(2, "PubG", "book", "Online multiplayer battle royale game", R.drawable.pubg)
        );

        entertainmentlist.add(

                new Entertainment(2, "Dinner", "book", "Dine out with your friends !", R.drawable.dinner)
        );
        adapter = new EntertainmentAdapter(getActivity(), entertainmentlist);

        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }
}
