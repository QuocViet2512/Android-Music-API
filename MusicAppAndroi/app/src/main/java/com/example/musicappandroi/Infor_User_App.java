package com.example.musicappandroi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Infor_User_App extends AppCompatActivity {
TextView IF_Name,IF_Phone;
Button Back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_user_app);
        IF_Name= findViewById(R.id.IF_Name);
        IF_Phone= findViewById(R.id.IF_Phone);
        Back = findViewById(R.id.IF_Back);
        if(MainActivity.getUser()!=null){
            IF_Name.setText("Name: "+MainActivity.getUser().getUserName());
            IF_Phone.setText("Phone:  "+MainActivity.getUser().getUserPhone());
        }
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}