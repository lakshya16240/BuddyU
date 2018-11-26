package com.example.skwow.mcproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NotificationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NotificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    public NotificationFragment() {
        // Required empty public constructor
    }

    public static NotificationFragment newInstance(String param1, String param2) {
        NotificationFragment fragment = new NotificationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_notification, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewNotification);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(User.currentUser.getUID()).child("notifications");
        FirebaseRecyclerAdapter<Notification,NotificationFragment.notificationViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Notification, notificationViewHolder>(Notification.class,R.layout.list_layout_notification,notificationViewHolder.class,mDatabase) {
            @Override
            protected void populateViewHolder(notificationViewHolder viewHolder, Notification model, int position) {
                viewHolder.sender.setText(model.getSentBy());
                viewHolder.eType.setText(model.getTopic());
                viewHolder.msg.setText(model.getMsg());
            }
        };
//
        recyclerView.setAdapter(firebaseRecyclerAdapter);
        return view;
    }

    public static class notificationViewHolder extends RecyclerView.ViewHolder
    {
        TextView sender, msg, eType;
        public notificationViewHolder(@NonNull View itemView) {
            super(itemView);
            sender = itemView.findViewById(R.id.notiSender);
            eType = itemView.findViewById(R.id.notiType);
            msg = itemView.findViewById(R.id.notiMsg);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
