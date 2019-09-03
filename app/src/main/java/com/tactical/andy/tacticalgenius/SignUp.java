package com.tactical.andy.tacticalgenius;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SignUp extends AppCompatActivity{


    EditText emailId, password;
    Button btnSignIn;
    TextView tvSignUp;
    FirebaseAuth mfirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        mfirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        btnSignIn = findViewById(R.id.button_register);
        tvSignUp = findViewById(R.id.textview_login);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {


            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {


                FirebaseUser mFirebaseUser = mfirebaseAuth.getCurrentUser();
                if (mFirebaseUser != null) {


                    Intent i = new Intent(SignUp.this, TacticalPage.class);
                    startActivity(i);
                    Toast.makeText(SignUp.this, "You Are Logged In", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(SignUp.this, "Logged Out", Toast.LENGTH_SHORT).show();
                }
            }
        };

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                if (email.isEmpty()) {

                    emailId.setError("Please enter email id");
                    emailId.requestFocus();
                } else if (pwd.isEmpty()) {

                    password.setError("Please enter your password");
                    password.requestFocus();

                } else if (email.isEmpty() && pwd.isEmpty()) {

                    Toast.makeText(SignUp.this, "Fields Are Empty", Toast.LENGTH_SHORT).show();
                } else if (!(email.isEmpty() && pwd.isEmpty())) {

                    mfirebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()) {

                                Toast.makeText(SignUp.this, "Login Error, Please Try Again", Toast.LENGTH_SHORT).show();

                            } else {
                                Intent homePage = new Intent(SignUp.this, TacticalPage.class);
                                startActivity(homePage);
                            }
                        }
                    });
                } else {
                    Toast.makeText(SignUp.this, "Error Occurred", Toast.LENGTH_SHORT).show();

                }
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homePage = new Intent(SignUp.this, MainActivity.class);
                startActivity(homePage);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mfirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}
