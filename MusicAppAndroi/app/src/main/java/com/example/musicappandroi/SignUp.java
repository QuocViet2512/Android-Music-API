package com.example.musicappandroi;

import static com.example.lib.RetrofitClient.getRetrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lib.InterfaceResponsitory.Methods;
import com.example.lib.Models.SignUpModel;
import com.example.lib.Models.SignUpResultModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {

    EditText SU_User_Name,SU_Phone_Number,SU_Pass_Word;
    Button SU_But_Create,SU_But_Cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        SU_User_Name= findViewById(R.id.SU_User_Name);
        SU_Phone_Number = findViewById(R.id.SU_Phone_Number);
        SU_Pass_Word = findViewById(R.id.SU_Pass_Word);
        SU_But_Create = findViewById(R.id.SU_But_Create);
        SU_But_Cancel = findViewById(R.id.SU_But_Cancel);
        SU_But_Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp();
            }
        });
        SU_But_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    public void SignUp(){
        String strUserphone = SU_Phone_Number.getText().toString().trim();
        String strUserpass = SU_Pass_Word.getText().toString().trim();
        String strName = SU_User_Name.getText().toString().trim();
        SignUpModel signup = new SignUpModel();
        signup.setUserPhone(strUserphone);
        signup.setUserPassword(strUserpass);
        signup.setUserName(strName);
        Methods methods =getRetrofit().create(Methods.class);
        Call<SignUpResultModel> call = methods.SignUp(signup);
        call.enqueue(new Callback<SignUpResultModel>() {
            @Override
            public void onResponse(Call<SignUpResultModel> call, Response<SignUpResultModel> response) {
                if(response.isSuccessful()){
                    String result = response.body().getStatus();
                    if(result.equals(true)){
                        Toast.makeText(SignUp.this, "SignUp Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else {
                        Toast.makeText(SignUp.this, "Your Phone Number Is Exist", Toast.LENGTH_SHORT).show();
                    }


                }
            }

            @Override
            public void onFailure(Call<SignUpResultModel> call, Throwable t) {

            }
        });
    }
}