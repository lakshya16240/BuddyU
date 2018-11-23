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

import java.util.ArrayList;

public class ChatFragment extends Fragment {

    public static final String TAG = "ChatFragment";
    private Button sendButton;
    private TextView messageBox;
    private ArrayList<GroupMessage> messages = new ArrayList<>();
    private RecyclerView rv_Chat;
    private ChatAdapter chatAdapter;
    private static ChatFragment fragment;

    public static ChatFragment newInstance() {
        if(fragment == null)
            fragment = new ChatFragment();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view  = inflater.inflate(R.layout.fragment_chat,container,false);

        sendButton = view.findViewById(R.id.sendButton);
        messageBox = view.findViewById(R.id.messageBox);
        rv_Chat = view.findViewById(R.id.rv_chat);

        rv_Chat.setLayoutManager(new LinearLayoutManager(getActivity()));
        chatAdapter = new ChatAdapter(getActivity(),messages);

        rv_Chat.setAdapter(chatAdapter);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupMessage groupMessage = new GroupMessage(messageBox.getText().toString(),User.currentUser);
                groupMessage.setSelfMessage(true);
                messages.add(groupMessage);
                chatAdapter.setMessages(messages);
                chatAdapter.notifyDataSetChanged();
                messageBox.setText("");

            }
        });

        return view;
    }
}
