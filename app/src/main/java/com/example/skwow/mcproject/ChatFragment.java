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
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatFragment extends Fragment {

    public static final String TAG = "ChatFragment";
    private Button sendButton;
    private DatabaseReference messagesDatabaseReference;
    private TextView messageBox;
    private ArrayList<Message> messages ;
    private RecyclerView rv_Chat;
    private ChatAdapter chatAdapter;
    private static ChatFragment fragment;
    private String eventId;

    public static ChatFragment newInstance(String eventId) {
        Log.d(TAG, "newInstance: " + eventId);
        Bundle bundle = new Bundle();
        bundle.putString("EventId",eventId);
        if(fragment == null)
            fragment = new ChatFragment();

        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view  = inflater.inflate(R.layout.fragment_chat,container,false);

        sendButton = view.findViewById(R.id.sendButton);
        messageBox = view.findViewById(R.id.messageBox);
        rv_Chat = view.findViewById(R.id.rv_chat);

        messages = new ArrayList<>();

        rv_Chat.setLayoutManager(new LinearLayoutManager(getActivity()));
        chatAdapter = new ChatAdapter(getActivity());

        eventId = getArguments().getString("EventId");

        messagesDatabaseReference = FirebaseDatabase.getInstance().getReference("Groups").child(eventId);

        messagesDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //TODO

                Group group = dataSnapshot.getValue(Group.class);
                if(group.getMessages().size() != 0) {
                    Log.d(TAG, "onDataChange: " + group.getMessages().size());
                    messages = group.getMessages();
                    chatAdapter.setMessages(group.getMessages());
                    chatAdapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        rv_Chat.setAdapter(chatAdapter);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupMessage groupMessage = new GroupMessage(messageBox.getText().toString(),User.currentUser);
                groupMessage.setSelfMessage(true);

                messages.add(new Message(messageBox.getText().toString(), User.currentUser.getEmail(), User.currentUser.getUID()));

                messagesDatabaseReference.child("messages").setValue(messages);
//                messages.add(groupMessage);
                chatAdapter.setMessages(messages);
                chatAdapter.notifyDataSetChanged();
                messageBox.setText("");




            }
        });

        return view;
    }
}
