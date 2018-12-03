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
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EventFragment extends Fragment{

    RecyclerView recyclerView;
    public static final String TAG = "EventFragment";
    private DatabaseReference mDatabase;
    private Button createEventBtn;

    static ArrayList<MyEvent> groups = new ArrayList<>();

    private static RelativeLayout rl_eventFrom;  // todo: bad design

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.event_frament, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewEvent);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Spinner categorySpinner =   view.findViewById(R.id.sp_eventType);
        Spinner typeSpinner =   view.findViewById(R.id.sp_eventsubType);
        Spinner populateSpinner =   view.findViewById(R.id.sp_eventmoviepopulate);

        rl_eventFrom = view.findViewById(R.id.rl_eventForm);
        createEventBtn = view.findViewById(R.id.createEvent);
        ArrayAdapter<String> movieAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.movies_events_array));
        ArrayAdapter<String> sportsAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.sports_events_array));
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayList<String> movienames = new ArrayList<>();
                movienames.add("Suggestions");
                for(Movie m : Movie.allMovies)
                    movienames.add(m.name);
                ArrayAdapter<String> moviePopulateAdapter  = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item, movienames);

                if(categorySpinner.getSelectedItem().toString().equals("Movie"))
                {
                    typeSpinner.setVisibility(View.VISIBLE);
                    populateSpinner.setVisibility(View.VISIBLE);
                    typeSpinner.setAdapter(movieAdapter);
                    populateSpinner.setAdapter(moviePopulateAdapter);
                }
                else if(categorySpinner.getSelectedItem().toString().equals("Sports"))
                {
                    typeSpinner.setVisibility(View.VISIBLE);
                    populateSpinner.setVisibility(View.GONE);
                    typeSpinner.setAdapter(sportsAdapter);
                }
                else
                {
                    typeSpinner.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                typeSpinner.setVisibility(View.GONE);
            }
        });

        populateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View v, int i, long l) {
                if(populateSpinner.getSelectedItemPosition() == 0)
                {
                    ((EditText)view.findViewById(R.id.createEventVenue)).setText("");
                    ((EditText)view.findViewById(R.id.createEventTime)).setText("");
                    ((EditText)view.findViewById(R.id.createEventHeading)).setText("");
                    ((EditText)view.findViewById(R.id.eventImageLink)).setText("");
                }
                else
                {
                    String mn = populateSpinner.getSelectedItem().toString();
                    for(Movie m : Movie.allMovies)
                    {
                        if (mn.equals(m.name))
                        {
                            ((EditText)view.findViewById(R.id.createEventVenue)).setText(m.venue);
                            ((EditText)view.findViewById(R.id.createEventTime)).setText(m.timing);
                            ((EditText)view.findViewById(R.id.createEventHeading)).setText(m.name);
                            ((EditText)view.findViewById(R.id.eventImageLink)).setText(m.imageLink);
                            break;
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        createEventBtn.setOnClickListener(root -> {
            // todo: validate the data

            if (categorySpinner.getSelectedItemPosition() == 0)
            {
                Toast.makeText(getContext(), "please select category 1st",Toast.LENGTH_LONG).show();
                return;
            }
            String eventType = typeSpinner.getSelectedItemPosition() == 0 ?"default": typeSpinner.getSelectedItem().toString();
            String venue = ((EditText)view.findViewById(R.id.createEventVenue)).getText().toString();
            if(venue.equals(""))
            {
                Toast.makeText(getContext(), "venue can't be emply",Toast.LENGTH_LONG).show();
                return;
            }

            String timing =((EditText)view.findViewById(R.id.createEventTime)).getText().toString();
            if(timing.equals(""))
            {
                Toast.makeText(getContext(), "timing can't be emply",Toast.LENGTH_LONG).show();
                return;
            }
            String title = ((EditText)view.findViewById(R.id.createEventHeading)).getText().toString();
            if(title.equals(""))
            {
                Toast.makeText(getContext(), "title can't be emply",Toast.LENGTH_LONG).show();
                return;
            }
            String details = ((EditText)view.findViewById(R.id.et_optionalDetails)).getText().toString();
            String imageLink = ((EditText)view.findViewById(R.id.eventImageLink)).getText().toString();

            MyEvent event = new MyEvent(eventType, venue, timing,0,title , details,User.currentUser.getUID(), imageLink,"");
            event.pushToDatabase();
            rl_eventFrom.setVisibility(View.GONE);
            User.currentUser.addEvent(event);

            Notification newNotification = new Notification(User.currentUser.getEmail() /*todo: change this to username after setting username*/,eventType,"created a new Event. Interested?");
            DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Notification");
            myRef.setValue(newNotification);

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
