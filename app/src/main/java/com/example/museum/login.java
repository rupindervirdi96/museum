package com.example.museum;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class login extends AppCompatActivity implements View.OnClickListener, ValueEventListener, Serializable {
    Button regBtn, logBtn;
    EditText userId, pswd;
    DatabaseReference museumDB;
    Boolean loggedInUId;
    user u;
    String id, password;
    Intent mainView,register;
    String process;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        regBtn = findViewById(R.id.btnRegister);
        regBtn.setOnClickListener(this);
        logBtn = findViewById(R.id.btnLogin);
        logBtn.setOnClickListener(this);
        userId = findViewById(R.id.username);
        pswd = findViewById(R.id.password);
        museumDB = FirebaseDatabase.getInstance().getReference();
        mainView = new Intent(this, mainView.class);
        register=new Intent(this,register.class);
        loggedInUId=true;
    }

    @Override
    public void onClick(View v) {

        int btnId = v.getId();
        id = userId.getText().toString();

        switch (btnId) {
            case R.id.btnLogin:
                process="login";
                DatabaseReference musDB = FirebaseDatabase.getInstance().getReference().child("users").child(id);
                musDB.addValueEventListener(this);
                break;
            case R.id.btnRegister:
                process="register";
                Register();
                break;
        }
    }

    private void Register() {
        id = userId.getText().toString();
        password = pswd.getText().toString();

        if (id.trim().equals("") || password.trim().equals("")) {
            Toast.makeText(this, "One or more fields are empty", Toast.LENGTH_SHORT).show();
        } else {
            DatabaseReference mussDB = FirebaseDatabase.getInstance().getReference().child("users").child(id);
            mussDB.addValueEventListener(this);
        }
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        if(process.equals("login")) {
            try {
                id = dataSnapshot.child("userId").getValue().toString();
                password = pswd.getText().toString();
                String pswdStored = dataSnapshot.child("password").getValue().toString();
                String username = dataSnapshot.child("name").getValue().toString();
                Toast.makeText(this, pswdStored + "-" + password, Toast.LENGTH_SHORT).show();
                if (password.trim().equals(pswdStored.trim())) {
                    startActivity(mainView);
                    Toast.makeText(this, "Welcome " + username, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Wrong username or password", Toast.LENGTH_SHORT).show();//checks if the password is correct or not.
                }


            } catch (Exception e) {
                Toast.makeText(this, "Wrong Username or password", Toast.LENGTH_SHORT);//checks if the id does not exist.

            }
        }
        if(process.equals("register")){
            if(dataSnapshot.exists() && loggedInUId)
            {   userId.setText(null);
                Toast.makeText(this, "Username not available.", Toast.LENGTH_SHORT).show();
                loggedInUId=false;
            }
            else{
                id=userId.getText().toString();
                password = pswd.getText().toString();
                u=new user("name",id,password,"email","age");
                register.putExtra("user",u);
                startActivity(register);
            }
            return;
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
        Toast.makeText(this, "DB ERROR", Toast.LENGTH_SHORT).show();
    }
}
