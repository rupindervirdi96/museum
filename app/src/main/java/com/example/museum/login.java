package com.example.museum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class login extends AppCompatActivity implements View.OnClickListener {
Button regBtn,logBtn;
DatabaseReference museumDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        regBtn=findViewById(R.id.btnRegister);
        regBtn.setOnClickListener(this);
        logBtn=findViewById(R.id.btnLogin);
        logBtn.setOnClickListener(this);
        museumDB= FirebaseDatabase.getInstance().getReference("users");


    }

    @Override
    public void onClick(View v) {
                Intent mainView=new Intent(this, mainView.class);
                Intent register=new Intent(this, register.class);
        int btnId=v.getId();
        switch (btnId)
        {
            case R.id.btnLogin:
                startActivity(mainView);
                break;
            case R.id.btnRegister:
                startActivity(register);
                break;


        }
    }
}
