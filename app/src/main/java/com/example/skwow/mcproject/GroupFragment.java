package com.example.skwow.mcproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class GroupFragment extends Fragment {


    public static final String TAG = "GroupFragment";
    private RecyclerView rv_groups;
    private ArrayList<Group> groups;
    static GroupsAdapter groupsAdapter;
    private static GroupFragment fragment;

    public GroupFragment() {
        // Required empty public constructor
    }

    public static GroupFragment newInstance(ArrayList<Group> groupsArrayList) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("Groups",groupsArrayList);
        if(fragment == null)
            fragment = new GroupFragment();

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_group, container, false);

        rv_groups = view.findViewById(R.id.rv_groups);
        groups = (ArrayList<Group>) getArguments().getSerializable("Groups");
        rv_groups.setLayoutManager(new LinearLayoutManager(getActivity()));

        groupsAdapter = new GroupsAdapter(getActivity(),groups);

        Log.d(TAG, "onCreateView: " + groups.size());

        rv_groups.setAdapter(groupsAdapter);

        return view;

    }

}
