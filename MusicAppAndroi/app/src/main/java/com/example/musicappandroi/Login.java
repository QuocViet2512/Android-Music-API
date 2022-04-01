package com.example.musicappandroi;

import static com.example.lib.RetrofitClient.getRetrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lib.InterfaceResponsitory.Methods;
import com.example.lib.Models.LoginModel;
import com.example.lib.Models.UserApp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    Button Login, SignUp,BTH_Login;
    EditText L_Pass_Word,L_Phone_Number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         Login = findViewById(R.id.BTNLogin);
         SignUp = findViewById(R.id.BTNSignup);
        L_Pass_Word = findViewById(R.id.L_Pass_Word);
        L_Phone_Number = findViewById(R.id.L_Phone_Number);
        BTH_Login = findViewById(R.id.BTH_Login);
        BTH_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.example.musicappandroi.Login.this, com.example.musicappandroi.SignUp.class);
                startActivity(intent);
            }
        });
        clickLogin();

    }
    private void clickLogin() {
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strUserphone = L_Phone_Number.getText().toString().trim();
                String strUserpass = L_Pass_Word.getText().toString().trim();
                LoginModel login = new LoginModel();
                login.setUserPhone(strUserphone);
                login.setUserPassword(strUserpass);
                Methods methods =getRetrofit().create(Methods.class);
                Call<UserApp> call = methods.Login(login);
                call.enqueue(new Callback<UserApp>() {
                    @Override
                    public void onResponse(Call<UserApp> call, Response<UserApp> response) {
                        if(response.isSuccessful()){
                            UserApp user = response.body();
                            if(user.getData() == null){
                                Toast.makeText(Login.this,"This Account Don't Exist",Toast.LENGTH_SHORT).show();
                            }else {

                                Toast.makeText(Login.this,"Welcome : "+user.getData().getUserName(),Toast.LENGTH_SHORT).show();
                                MainActivity.user = user.getData();
                                MainActivity.currentOption =3;
                                finish();

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<UserApp> call, Throwable t) {

                    }
                });


            }
        });

    }

}