package com.example.skwow.mcproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EventFragment extends Fragment{

    RecyclerView recyclerView;
    public static final String TAG = "EventFragment";
    private DatabaseReference mDatabase;
    private DatabaseReference groupDatabaseReference;
    private Button createEventBtn;

    static ArrayList<MyEvent> groups = new ArrayList<>();

    private static RelativeLayout rl_eventFrom;  // todo: bad design

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.event_frament, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewEvent);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        rl_eventFrom = view.findViewById(R.id.rl_eventForm);
        createEventBtn = view.findViewById(R.id.createEvent);

        createEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View root) {
                // todo: validate the data
                MyEvent event = new MyEvent(((Spinner)view.findViewById(R.id.sp_eventType)).getSelectedItem().toString(),
                                            ((EditText)view.findViewById(R.id.createEventVenue)).getText().toString(),
                                            ((EditText)view.findViewById(R.id.createEventTime)).getText().toString(),
                                            0,((EditText)view.findViewById(R.id.createEventHeading)).getText().toString(),
                                            ((EditText)view.findViewById(R.id.et_optionalDetails)).getText().toString(),
                                            User.currentUser.getUID(),
                                            ((EditText)view.findViewById(R.id.eventImageLink)).getText().toString());
                event.pushToDatabase();
                rl_eventFrom.setVisibility(View.GONE);
                User.currentUser.addEvent(event);
            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Events");
        FirebaseRecyclerAdapter<MyEvent,movieViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<MyEvent, movieViewHolder>(MyEvent.class,R.layout.list_layout_event,movieViewHolder.class,mDatabase)
        {
            @Override
            protected void populateViewHolder(movieViewHolder viewHolder, MyEvent model, int position) {
                viewHolder.textViewTitle.setText(model.getHeading());
                viewHolder.textViewVenue.setText(model.getVenue()+" "+ model.getTime());
                if(model.getImageLink().length()!=0)
                    Picasso.with(getContext()).load(model.getImageLink()).into(viewHolder.imageView);

                Log.d(TAG, "populateViewHolder: " + User.currentUser.getUID());
                if(model.getCreatedBy().equals(User.currentUser.getUID()))
                    viewHolder.book.setVisibility(View.INVISIBLE);

                viewHolder.book.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int flag = 0;
                        for(int i=0;i<User.currentUser.getEvents().size();i++){
                            if(User.currentUser.getEvents().get(i).getSalt().equals(model.getSalt())){
                                flag = 1;
                            }
                        }

                        if(flag == 0) {
                            User.currentUser.addEvent(model);
                            model.addUser(User.currentUser);
                        }
                        groups.add(model);
                    }
                });
            }
        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);
        return view;
    }

    public static class movieViewHolder extends RecyclerView.ViewHolder {

        View mView;
        ImageView imageView;
        TextView textViewTitle, textViewVenue;
        Button book;

        public movieViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            imageView = itemView.findViewById(R.id.imageView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewVenue = itemView.findViewById(R.id.eventVenue);
            book = itemView.findViewById(R.id.book);
        }

    }

    public static void toggleVisiblity(){
        if(rl_eventFrom.getVisibility()== View.GONE)
            rl_eventFrom.setVisibility(View.VISIBLE);
        else if(rl_eventFrom.getVisibility()== View.VISIBLE)
            rl_eventFrom.setVisibility(View.GONE);
    }
}