package com.example.skwow.mcproject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AccountFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public AccountFragment() {
        // Required empty public constructor
    }

    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_account, container, false);
        Button logoutBtn = root.findViewById(R.id.Logout);
        Button saveProfile = root.findViewById(R.id.saveProfile);
        TextView username = root.findViewById(R.id.user_name);
        username.setText(User.currentUser.getEmail());
        TextView eventCount = root.findViewById(R.id.event_count);
        eventCount.setText("events: " + User.currentUser.getEvents().size());

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });

        saveProfile.setOnClickListener((view) ->{
            onSave(root);
        });

        GridLayout ll = root.findViewById(R.id.GridLayout1);
        for (int i = 0; i < ll.getChildCount(); i++)
        {
            Button btn = (Button)ll.getChildAt(i);
            if(User.currentUser.getSportsInterests().contains(btn.getText().toString()))
            {
                btn.setTag("1");
                btn.setBackgroundResource(R.drawable.my_chip_selected);
            }

        }

        ll = root.findViewById(R.id.GridLayout2);
        for (int i = 0; i < ll.getChildCount(); i++)
        {
            Button btn = (Button)ll.getChildAt(i);
            if(User.currentUser.getMoviesInterests().contains(btn.getText().toString()))
            {
                btn.setTag("1");
                btn.setBackgroundResource(R.drawable.my_chip_selected);
            }
        }
        return root;
    }

    private void onSave(View view)
    {
        // todo: do this in a separated thread
        // todo: move the functionality of save to User class only, It will have to be called from many places in future
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");

        ArrayList<String> movies = new ArrayList<>();
        ArrayList<String> sports = new ArrayList<>();

        GridLayout ll = view.findViewById(R.id.GridLayout1);
        for (int i = 0; i < ll.getChildCount(); i++)
        {
            Button btn = (Button)ll.getChildAt(i);
            if(btn.getTag().equals("1"))
                sports.add(btn.getText().toString());
        }

        ll = view.findViewById(R.id.GridLayout2);
        for (int i = 0; i < ll.getChildCount(); i++)
        {
            Button btn = (Button)ll.getChildAt(i);
            if(btn.getTag().equals("1"))
                movies.add(btn.getText().toString());
        }

        User.currentUser.setSportsInterests(sports);
        User.currentUser.setMoviesInterests(movies);
        myRef.child(User.currentUser.getUID()).setValue(User.currentUser);
        Toast.makeText(getContext(), "Saved",Toast.LENGTH_LONG).show();
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
