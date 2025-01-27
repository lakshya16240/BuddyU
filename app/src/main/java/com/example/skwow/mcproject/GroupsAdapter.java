package com.example.skwow.mcproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.GroupViewHolder>{

    public static final String TAG = "GroupAdapter";
    private Context context;
    private ArrayList<MyEvent> groups;

    public GroupsAdapter(Context context, ArrayList<MyEvent> groups) {
        this.context = context;
        this.groups = groups;
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.list_item_group,viewGroup,false);
        return new GroupViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder groupViewHolder, int i) {
        groupViewHolder.tv_eventTitle.setText(groups.get(i).getHeading());
        groupViewHolder.tv_eventVenue.setText(groupViewHolder.tv_eventVenue.getText().toString() + groups.get(i).getVenue());
        groupViewHolder.tv_eventTiming.setText(groupViewHolder.tv_eventTiming.getText().toString() + groups.get(i).getTime());

        groupViewHolder.rl_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatFragment chatFragment = ChatFragment.newInstance(groups.get(i).getSalt());
                Log.d(TAG, "onClick: " + groups.get(i).getSalt());
                MainActivity mainActivity = (MainActivity)context;
                FragmentManager fm = mainActivity.getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, chatFragment);
                ft.commit();

            }
        });

    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    class GroupViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_eventTitle, tv_eventVenue, tv_eventTiming;
        private RelativeLayout rl_group;

        public GroupViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_eventTitle = itemView.findViewById(R.id.tv_eventTitle);
            tv_eventVenue = itemView.findViewById(R.id.tv_eventVenue);
            tv_eventTiming = itemView.findViewById(R.id.tv_eventTiming);
            rl_group = itemView.findViewById(R.id.rl_group);
        }
    }
}
