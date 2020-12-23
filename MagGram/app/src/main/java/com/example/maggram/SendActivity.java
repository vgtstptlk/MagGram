package com.example.maggram;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.maggram.Domains.Message;
import com.example.maggram.api.ServiceApi;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SendActivity extends AppCompatActivity {

    private String loginTo, loginFrom;
    private final String URL = "http://10.0.2.2:8080";
    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    ServiceApi serviceApi = retrofit.create(ServiceApi.class);

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        loginTo = (String) getIntent().getExtras().get("loginTo");
        loginFrom = (String) getIntent().getExtras().get("loginFrom");
        Button button = (Button) findViewById(R.id.btnSend);
        button.setText(button.getText() + " " + loginTo);
    }

    public void onClick(View view){
        EditText editText = (EditText) findViewById(R.id.editTextTextMultiLine2);
        Map<String, String> fields = new HashMap<>();
        fields.put("loginFrom", loginFrom);
        fields.put("loginTo", loginTo);
        fields.put("caption", editText.getText().toString());
        Call<Message> messageCall = serviceApi.createMessage(fields);
        messageCall.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                showDialog("Info", "Сообщение отправлено");
                finish();
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                showDialog("Ошибка", "Произошла ошибка");
            }
        });
    }

    private void showDialog(String title, String message){
        AlertDialog.Builder a_builder = new AlertDialog.Builder(SendActivity.this);
        a_builder.setTitle(title);
        a_builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Да", (dialog, which) -> dialog.cancel());
        a_builder.show();
    }
}