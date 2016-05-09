package com.example.mynewapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class LoginActivity extends Activity {

    Firebase ref;
    EditText loginEmail,loginPass;
    Button loginUsr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Firebase.setAndroidContext(this);
        loginEmail=(EditText) findViewById(R.id.loginid);
        loginPass=(EditText) findViewById(R.id.loginpss);
        loginUsr=(Button) findViewById(R.id.btnLogin);
        ref = new Firebase("https://androtestapp.firebaseio.com/");

        loginUsr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ref.authWithPassword(loginEmail.getText().toString(), loginPass.getText().toString(), new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        Intent openProfile = new Intent(LoginActivity.this, NoteList.class);
                        System.out.println("New App ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                        openProfile.putExtra("user_uid", authData.getUid());
                        startActivity(openProfile);
                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        Toast.makeText(getApplicationContext(), "Failed to login,, please check email and password, try again later.", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });


    }
}
