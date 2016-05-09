package com.example.mynewapp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class MainActivity extends Activity {

    EditText usrEmail;
    EditText usrPass,usrMob;
    Button registerUsrBtn,goToLoginPg;
    String em,pass,mobi;
    Firebase ref;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        usrEmail=(EditText) findViewById(R.id.registerEmail);
        usrPass=(EditText) findViewById(R.id.registerPswrd);
        registerUsrBtn=(Button) findViewById(R.id.regBtn);
        tv=(TextView) findViewById(R.id.link_to_login);
        Firebase.setAndroidContext(this);

        ref = new Firebase("https://androtestapp.firebaseio.com/");

        registerUsrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                em=usrEmail.getText().toString();
                pass=usrPass.getText().toString();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                    // onClick of button perform this simplest code.
                if (em.matches(emailPattern)&&pass.length()>=5)
                {
                    ref.createUser(em, pass, new Firebase.ValueResultHandler<Map<String, Object>>() {
                        @Override
                        public void onSuccess(Map<String, Object> result) {
                            ref.child("user").child(result.get("uid").toString()).child("email").setValue(usrEmail.getText().toString());
                            ref.child("user").child(result.get("uid").toString()).child("password").setValue(usrPass.getText().toString());
                            System.out.println("Successfully created user account with uid: " + result.get("uid"));
                            Toast.makeText(MainActivity.this, "Successfully created account, now Log-In by clicking the button below.", Toast.LENGTH_LONG).show();
                            setFieldsNull();
                        }

                        @Override
                        public void onError(FirebaseError firebaseError) {
                            Toast.makeText(MainActivity.this, "Error creating user, please check the information filled above and try again.", Toast.LENGTH_LONG).show();
                            System.out.println("Error: " + firebaseError.toString());
                        }
                    });
                }
                else
                {
                    if(pass.length()<5)
                        Toast.makeText(getApplicationContext(), "Password too small, atleast 5 characters", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(), "Invalid Email Address", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(in);
            }
        });
    }
    void setFieldsNull(){
            usrPass.setText("");
    }
}
