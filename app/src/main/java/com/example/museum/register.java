package com.example.museum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity implements View.OnClickListener {
    Button btnBack,btnRegister;
    EditText name,username,password;
    DatabaseReference museumDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        btnBack=findViewById(R.id.returnToLogin);
        btnRegister=findViewById(R.id.btnRegister2);
        name=findViewById(R.id.name);
        username=findViewById(R.id.userIdRegister);
        password=findViewById(R.id.passwordRegister);
        btnBack.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        museumDB= FirebaseDatabase.getInstance().getReference("users");


    }

    @Override
    public void onClick(View v) {
                Intent login=new Intent(this,login.class);
                Intent main=new Intent(this,mainView.class);
        int btnId=v.getId();
        switch (btnId){
            case R.id.returnToLogin:
                startActivity(login);
                break;
            case R.id.btnRegister2:
                Register();
                startActivity(main);
                break;
        }
    }

    private void Register() {
        String nameInput=name.getText().toString();
        String id=username.getText().toString();
        String pswd=password.getText().toString();
        user u=new user(nameInput,id,pswd);
        museumDB.child(id).setValue(u);
        Toast.makeText(this,"User Registered",Toast.LENGTH_LONG).show();
        name.setText(null);
        password.setText(null);
        username.setText(null);

    }
}
