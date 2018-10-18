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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MovieFragment extends Fragment{

    RecyclerView recyclerView;
    MovieAdapter adapter;
    List<Movie> movieList ;
    private Button createEventBtn;

    private static RelativeLayout rl_eventFrom;  // todo: bad design

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        movieList = new ArrayList<>();
        final View view = inflater.inflate(R.layout.movie_frament, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        rl_eventFrom = view.findViewById(R.id.rl_eventForm);
        createEventBtn = view.findViewById(R.id.createEvent);

        createEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View root) {
                // todo: validate the data
                MyEvent event = new MyEvent(((Spinner)view.findViewById(R.id.sp_eventType)).getSelectedItem().toString(), ((EditText)view.findViewById(R.id.createEventVenue)).getText().toString(),((EditText)view.findViewById(R.id.createEventTime)).getText().toString(),0,((EditText)view.findViewById(R.id.createEventHeading)).getText().toString(),((EditText)view.findViewById(R.id.et_optionalDetails)).getText().toString(),User.currentUser.getUID());
                event.pushToDatabase();
            }
        });

        movieList.add(
                new Movie(1, "Iron Man", 4.4, "book", "Hindi", R.drawable.ironman)
        );
        movieList.add(
                new Movie(2, "Hunger Games", 4.4, "book", "English", R.drawable.hungergames)
        );

        movieList.add(
                new Movie(3, "Raazi", 4.4, "book", "English", R.drawable.raazi)
        );
        movieList.add(
                new Movie(1, "Iron Man", 4.4, "book", "Hindi", R.drawable.ironman)
        );
        movieList.add(
                new Movie(2, "Hunger Games", 4.4, "book", "English", R.drawable.hungergames)
        );

        movieList.add(
                new Movie(3, "Raazi", 4.4, "book", "English", R.drawable.raazi)
        );

        adapter = new MovieAdapter(getActivity(), movieList);

        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }

    public static void showEventForm(){
        rl_eventFrom.setVisibility(View.VISIBLE);
    }
}
