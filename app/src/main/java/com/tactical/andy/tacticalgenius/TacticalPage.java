package com.tactical.andy.tacticalgenius;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class TacticalPage extends MainActivity {

    EditText gk1, gk2, gk3, def1, def2, def3, def4, def5, def6, def7, def8, mid1, mid2, mid3, mid4, mid5, mid6, mid7, mid8, for1, for2, for3, for4, for5;
    Button btnLogout, save;
    FirebaseAuth mFirebaseAuth;
    FirebaseAuth.AuthStateListener mAuthStateListener;
    DatabaseReference databaseReference;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tacticalpage);

        gk1 = findViewById(R.id.goalkeeper1);
        gk2 = findViewById(R.id.goalkeeper2);
        gk3 = findViewById(R.id.goalkeeper3);
        def1 = findViewById(R.id.defender1);
        def2 = findViewById(R.id.defender2);
        def3 = findViewById(R.id.defender3);
        def4 = findViewById(R.id.defender4);
        def5 = findViewById(R.id.defender5);
        def6 = findViewById(R.id.defender6);
        def7 = findViewById(R.id.defender7);
        def8 = findViewById(R.id.defender8);
        mid1 = findViewById(R.id.midfielder1);
        mid2 = findViewById(R.id.midfielder2);
        mid3 = findViewById(R.id.midfielder3);
        mid4 = findViewById(R.id.midfielder4);
        mid5 = findViewById(R.id.midfielder5);
        mid6 = findViewById(R.id.midfielder6);
        mid7 = findViewById(R.id.midfielder7);
        mid8 = findViewById(R.id.midfielder8);
        for1 = findViewById(R.id.forward1);
        for2 = findViewById(R.id.forward2);
        for3 = findViewById(R.id.forward3);
        for4 = findViewById(R.id.forward4);
        for5 = findViewById(R.id.forward5);
        save = findViewById(R.id.buttonsave);
        btnLogout = findViewById(R.id.log_out);




        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user_id = mfirebaseAuth.getCurrentUser().getUid();
                DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("User").child(user_id);

                String goal1 = gk1.getText().toString();
                String goal2 = gk2.getText().toString();
                String goal3 = gk3.getText().toString();

                String defe1 = def1.getText().toString();
                String defe2 = def2.getText().toString();
                String defe3 = def3.getText().toString();
                String defe4 = def4.getText().toString();
                String defe5 = def5.getText().toString();
                String defe6 = def6.getText().toString();
                String defe7 = def7.getText().toString();
                String defe8 = def8.getText().toString();

                String midf1 = mid1.getText().toString();
                String midf2 = mid2.getText().toString();
                String midf3 = mid3.getText().toString();
                String midf4 = mid4.getText().toString();
                String midf5 = mid5.getText().toString();
                String midf6 = mid6.getText().toString();
                String midf7 = mid7.getText().toString();
                String midf8 = mid8.getText().toString();

                String forw1 = for1.getText().toString();
                String forw2 = for2.getText().toString();
                String forw3 = for3.getText().toString();
                String forw4 = for4.getText().toString();
                String forw5 = for5.getText().toString();

                Map map = new HashMap();
                map.put("GoalKeeper 1", goal1);
                map.put("GoalKeeper 2", goal2);
                map.put("GoalKeeper 3", goal3);


                map.put("Defender 1", defe1);
                map.put("Defender 2", defe2);
                map.put("Defender 3", defe3);
                map.put("Defender 4", defe4);
                map.put("Defender 5", defe5);
                map.put("Defender 6", defe6);
                map.put("Defender 7", defe7);
                map.put("Defender 8", defe8);

                map.put("Midfielder 1", midf1);
                map.put("Midfielder 2", midf2);
                map.put("Midfielder 3", midf3);
                map.put("Midfielder 4", midf4);
                map.put("Midfielder 5", midf5);
                map.put("Midfielder 6", midf6);
                map.put("Midfielder 7", midf7);
                map.put("Midfielder 8", midf8);


                map.put("Forward 1", forw1);
                map.put("Forward 2", forw2);
                map.put("Forward 3", forw3);
                map.put("Forward 4", forw4);
                map.put("Forward 5", forw5);



                current_user_db.setValue(map);

                Toast.makeText(TacticalPage.this, "Data has been saved", Toast.LENGTH_SHORT).show();

            }
            

        });







        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();

                Intent mainPage = new Intent(TacticalPage.this, MainActivity.class);
                startActivity(mainPage);

                Toast.makeText(TacticalPage.this, "Logged Out", Toast.LENGTH_SHORT).show();

            }


        });




    }






    }


