package com.example.maggram;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.maggram.Domains.Auth;
import com.example.maggram.Domains.User;
import com.example.maggram.api.ServiceApi;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    EditText editTextEmail, editTextPassword;
    String email, password;
    private final String URL = "http://10.0.2.2:8080";
    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    ServiceApi serviceApi = retrofit.create(ServiceApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
    }

    public void onClickSignIn(View view){
        editTextEmail = (EditText) findViewById(R.id.editTextTextEmailAddress);
        editTextPassword = (EditText) findViewById(R.id.editTextTextPassword);

        email = editTextEmail.getText().toString();
        password = editTextPassword.getText().toString();

        Call<Auth> authCall = serviceApi.auth(email, password);
        Intent intent = new Intent(this, ContactActivity.class);
        authCall.enqueue(new Callback<Auth>() {
            @Override
            public void onResponse(Call<Auth> call, Response<Auth> response) {
                int status = response.code();
                Auth auth = response.body();
                if (auth.getResult()){
                    intent.putExtra("login", email);
                    startActivity(intent);

                }
                else {
                    showDialog("ЛЕЕЕЕЕЕ НЭ ПРОЙДЕШЬ", "ТЫ ВВЕЛ НЕВЕРНЫЙ ПАРОЛЬ"+response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<Auth> call, Throwable t) {
                showDialog("Ошибка",t.getMessage());
            }
        });


    }

    public void onClickSignUp(View view) throws IOException {
        editTextEmail = (EditText) findViewById(R.id.editTextTextEmailAddress);
        editTextPassword = (EditText) findViewById(R.id.editTextTextPassword);

        email = editTextEmail.getText().toString();
        password = editTextPassword.getText().toString();

        Map<String, String> fields = new HashMap<>();
        fields.put("login",email);
        fields.put("password",password);

        Call<User> userCall = serviceApi.createUser(fields);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                int status = response.code();
                User user = response.body();
                if (status == 400){
                    showDialog("Ошибка", "Аккаунт уже существует");
                } else {
                    showDialog("Успешно","Аккаунт зарегистрирован");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                showDialog("Ошибка", "Возможно проблема с соедиенением");
            }
        });

    }

    private void showDialog(String title, String message){
        AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
        a_builder.setTitle(title);
        a_builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Да", (dialog, which) -> dialog.cancel());
        a_builder.show();
    }



}