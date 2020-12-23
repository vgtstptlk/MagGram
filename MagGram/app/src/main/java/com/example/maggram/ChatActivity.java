package com.example.maggram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;

import com.example.maggram.Domains.Contact;
import com.example.maggram.Domains.Message;
import com.example.maggram.api.ServiceApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatActivity extends AppCompatActivity {

    private String login;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Message> messageList = new ArrayList<>();
    private RecyclerView.LayoutManager layoutManager;
    private final String URL = "http://10.0.2.2:8080";
    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    ServiceApi serviceApi = retrofit.create(ServiceApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        login = (String) getIntent().getExtras().get("login");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        recyclerView = (RecyclerView) findViewById(R.id.rec_chat);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Call<List<Message>> listCall = serviceApi.getMessagesByLoginTo(login);
        listCall.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                messageList = response.body();
                adapter = new ChatAdapter(ChatActivity.this, messageList);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {

            }
        });
    }

    private void showDialog(String title, String message){
        AlertDialog.Builder a_builder = new AlertDialog.Builder(ChatActivity.this);
        a_builder.setTitle(title);
        a_builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Да", (dialog, which) -> dialog.cancel());
        a_builder.show();
    }

}