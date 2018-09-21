package com.example.skwow.mcproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
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

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SIGNUP";
    public EditText emailEditText;
    public EditText passwordEditText;
    public Button signUpButton;
    private TextView feedback;
    public ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        emailEditText = (EditText)findViewById(R.id.email);
        passwordEditText = (EditText)findViewById(R.id.password);
        signUpButton = (Button) findViewById(R.id.email_sign_up_button);
        feedback = (TextView) findViewById(R.id.feedback);
        progressBar = (ProgressBar) findViewById(R.id.signup_progress);
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    public void onSignInButtonClicked(View view) {
        Intent i = new Intent(getBaseContext(), LoginActivity.class);
        finish();
        startActivity(i);
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
    private void onSuccessfulRegistration() {
        Intent i = new Intent(getBaseContext(), MainActivity.class);
//        i.putExtra("PersonID", mAuth.getCurrentUser().getDisplayName());
        finish();
        startActivity(i);
    }

    public void onRegister(View view) {

        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if(validateEmailAndPassword(email,password)) {
            feedback.setText("");
            feedback.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            progressBar.setVisibility(View.GONE);
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                onSuccessfulRegistration();
                            }
                            else {
                                Toast.makeText(SignUpActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
