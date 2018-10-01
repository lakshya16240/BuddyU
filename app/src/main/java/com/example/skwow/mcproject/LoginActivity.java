package com.example.skwow.mcproject;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LOGIN";
    public EditText emailEditText;
    public EditText passwordEditText;
    public Button signInButton;
    private TextView feedback;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        emailEditText = (EditText)findViewById(R.id.email);
        passwordEditText = (EditText)findViewById(R.id.password);
        signInButton = (Button) findViewById(R.id.email_sign_in_button);
        feedback = (TextView) findViewById(R.id.feedback);
        progressBar = (ProgressBar) findViewById(R.id.login_progress);
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null) {
            Toast.makeText(LoginActivity.this, "Already logged in", Toast.LENGTH_SHORT).show();
            onSuccessfulLogin(currentUser);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    /*
    Checks validity of Email and Password entered by the user.
    return: True if correct, else False
     */
    private boolean validateEmailAndPassword(String email, String password) {

        if (TextUtils.isEmpty(email)) {
            // Email field is empty
//            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            feedback.setText("Please enter email");
            feedback.setVisibility(View.VISIBLE);
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            // Password field empty
//            Toast.makeText(this, "Please enter a password", Toast.LENGTH_LONG).show();
            feedback.setText("Please enter a password");
            feedback.setVisibility(View.VISIBLE);
            return false;
        }
        if ( !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() ) {
//            Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
            feedback.setText("This email address is invalid");
            feedback.setVisibility(View.VISIBLE);
            return false;
        }

        return true;
    }

    /*
    Starts Main activity with user id
     */
    public void onSuccessfulLogin(FirebaseUser user) {

        // todo : do this in a separated thread
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users").child(user.getUid());

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User u = dataSnapshot.getValue(User.class);
                User.currentUser = u;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        Intent i = new Intent(getBaseContext(), MainActivity.class);
        //i.putExtra("PersonID", mAuth.getCurrentUser().getDisplayName());
        startActivity(i);
        finish();
    }

    public void onSignIn(View view) {

        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (validateEmailAndPassword(email,password)) {
            feedback.setText("");
            feedback.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success");
                        Toast.makeText(getApplicationContext(), "Authentication successful.", Toast.LENGTH_SHORT).show();
                        FirebaseUser user = mAuth.getCurrentUser();
                        onSuccessfulLogin(user);
                    }
                    else {
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        feedback.setText("Email & password is incorrect");
                        feedback.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void onRegisterButtonClicked(View view) {
        Intent i = new Intent(getBaseContext(), SignUpActivity.class);
        startActivity(i);
        finish();
    }
}

