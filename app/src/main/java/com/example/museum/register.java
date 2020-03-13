package com.example.museum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class register extends AppCompatActivity implements View.OnClickListener, ValueEventListener, Serializable {
    Button btnFinish, btnBack;
    EditText name, email, age;
    user u;
    DatabaseReference museumDB;
    Intent main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        age = findViewById(R.id.age);
        btnFinish = findViewById(R.id.btnFinish);
        btnBack = findViewById(R.id.btnBack);
        btnFinish.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        main = new Intent(this, mainView.class);
    }

    @Override
    public void onClick(View v) {
        Intent login = new Intent(this, login.class);
        u = (user) getIntent().getSerializableExtra("user");

        int btnId = v.getId();
        switch (btnId) {
            case R.id.btnBack:
                startActivity(login);
                break;
            case R.id.btnFinish:
                Save();
                break;
        }
    }

    private void Save() {
        museumDB = FirebaseDatabase.getInstance().getReference("users").child(u.getUserId());
        String Name = name.getText().toString();
        String Email = email.getText().toString();
        String Age = age.getText().toString();
        u.setName(Name);
        u.setEmail(Email);
        u.setAge(Age);
        museumDB.addValueEventListener(this);
        Toast.makeText(this, "Welcome " + u.getUserId(), Toast.LENGTH_SHORT).show();
        startActivity(main);

    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        museumDB.setValue(u);

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
