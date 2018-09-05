package com.example.skwow.mcproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    public EditText emailEditText;
    public EditText passwordEditText;
    public Button signUpButton;

    private FirebaseAuth mAuth;
    private String TAG = "LOGIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        emailEditText = (EditText)findViewById(R.id.email);
        passwordEditText = (EditText)findViewById(R.id.password);
        signUpButton = (Button) findViewById(R.id.email_sign_up_button);
    }


    public void onSignInButtonClicked(View view)
    {
        Intent i = new Intent(getBaseContext(), LoginActivity.class);
        startActivity(i);
    }

    private boolean authenicateEmailAndPassword(String email, String password)
    {

        //todo: authenticate email and password
        return true;
    }

    private void onSuccessfulRegistration()
    {
        Intent i = new Intent(getBaseContext(), MainActivity.class);
        startActivity(i);
    }

    public void onRegister(View view)
    {
        String email = String.valueOf(emailEditText.getText());
        String password = String.valueOf(passwordEditText.getText());

        if(authenicateEmailAndPassword(email,password))
        {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if (task.isSuccessful())
                            {
                                FirebaseUser user = mAuth.getCurrentUser();
                                onSuccessfulRegistration();
                            }
                            else
                            {
                                Toast.makeText(SignUpActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
