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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class EventFragment extends Fragment{

    RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    private Button createEventBtn;

    private static RelativeLayout rl_eventFrom;  // todo: bad design

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.event_frament, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewEvent);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        rl_eventFrom = view.findViewById(R.id.rl_eventForm);
        createEventBtn = view.findViewById(R.id.createEvent);

        createEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View root) {
                // todo: validate the data
                MyEvent event = new MyEvent(((Spinner)view.findViewById(R.id.sp_eventType)).getSelectedItem().toString(), ((EditText)view.findViewById(R.id.createEventVenue)).getText().toString(),((EditText)view.findViewById(R.id.createEventTime)).getText().toString(),0,((EditText)view.findViewById(R.id.createEventHeading)).getText().toString(),((EditText)view.findViewById(R.id.et_optionalDetails)).getText().toString(),User.currentUser.getUID(),((EditText)view.findViewById(R.id.eventImageLink)).getText().toString());
                event.pushToDatabase();
                rl_eventFrom.setVisibility(View.GONE);
            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Events");
        FirebaseRecyclerAdapter<MyEvent,movieViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<MyEvent, movieViewHolder>
                (
                        MyEvent.class,R.layout.list_layout_event,movieViewHolder.class,mDatabase
                )
        {
            @Override
            protected void populateViewHolder(movieViewHolder viewHolder, MyEvent model, int position) {
                viewHolder.textViewTitle.setText(model.getHeading());
                viewHolder.textViewVenue.setText(model.getVenue()+" "+ model.getTime());
                Picasso.with(getContext()).load(model.getImageLink()).into(viewHolder.imageView);
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
