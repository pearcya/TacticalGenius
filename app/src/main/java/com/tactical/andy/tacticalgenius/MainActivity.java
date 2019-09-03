package com.tactical.andy.tacticalgenius;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.ProviderQueryResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    EditText emailId, password;
    Button btnSignUp;
    TextView tvSignIn;
    FirebaseAuth mfirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mfirebaseAuth = FirebaseAuth.getInstance();

        emailId = findViewById(R.id.edittext_username);
        password = findViewById(R.id.edittext_password);
        btnSignUp = findViewById(R.id.button_login);
        tvSignIn = findViewById(R.id.textview_register);

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, SignUp.class);
                startActivity(i);


            }

        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                if(email.isEmpty()){

                    emailId.setError("Please enter email id");
                    emailId.requestFocus();
                }

                else  if (pwd.isEmpty()){

                    password.setError("Please enter your password");
                    password.requestFocus();


                }else if (pwd.length()<6){

                    password.setError("Password must be 6 characters or more");
                    password.requestFocus();

                }

                else if(email.isEmpty()  && pwd.isEmpty()){

                    Toast.makeText(MainActivity.this, "Fields Are Empty", Toast.LENGTH_SHORT).show();
                }
                else if(!(email.isEmpty()  && pwd.isEmpty())){


                    mfirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<com.google.firebase.auth.AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<com.google.firebase.auth.AuthResult> task) {

                            if(!task.isSuccessful()  ){

                                Toast.makeText(MainActivity.this, "Unsuccessful: User exist or email is incorrect", Toast.LENGTH_SHORT).show();

                            }
                            else{



                                startActivity(new Intent(MainActivity.this,TacticalPage.class));


                            }
                        }
                    });
                }
                else{
                    Toast.makeText(MainActivity.this, "Error Occurred", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }
}
