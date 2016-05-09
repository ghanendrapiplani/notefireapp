package com.example.mynewapp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class UserProfile extends Activity {
    TextView profEmail;
    TextView profMob;
    Firebase ref;
    String id;
    String usr_name, usr_mobile;
    Button logoutBtn,prodPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userprofile);
        Firebase.setAndroidContext(this);
        logoutBtn=(Button) findViewById(R.id.btnLogout);
        prodPage=(Button) findViewById(R.id.btnProdPage);
        profEmail=(TextView)findViewById(R.id.profileName);
        profMob=(TextView)findViewById(R.id.profleMobileNumber);

        Bundle extras = getIntent().getExtras();
        ref = new Firebase("https://androtestapp.firebaseio.com/");

        if (extras != null) {
            id = extras.getString("user_uid");
            System.out.println(id);
        }

        Intent in = getIntent();
        String id = in.getStringExtra("user_uid");
        System.out.println("shaker--" + id);
         ref.child("user").child(id).child("email").addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot snapshot) {

                 usr_name = snapshot.getValue().toString();
                 System.out.println(usr_name);
                 profEmail.setText(usr_name);
             }

             @Override
             public void onCancelled(FirebaseError error) {
             }
         });
         ref.child("user").child(id).child("mobile").addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot snapshot) {
                 System.out.println("shaker---" + snapshot.getValue());
                 usr_mobile = snapshot.getValue().toString();
                 profMob.setText(usr_mobile);

             }

             @Override
             public void onCancelled(FirebaseError error) {
             }
         });

        prodPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goProdPage = new Intent(UserProfile.this, ProductPage.class);
                startActivity(goProdPage);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Shaker out");
                ref.unauth();
                ref.addAuthStateListener(new Firebase.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(AuthData authData) {
                        if (authData != null) {
                            // user is logged in
                        } else {
                            Toast.makeText(UserProfile.this, "Successfully logged out.", Toast.LENGTH_SHORT).show();
                            Intent goToRegisterPage = new Intent(UserProfile.this, MainActivity.class);
                            startActivity(goToRegisterPage);
                        }
                    }
                });
            }
        });
 
    }
}
