package com.example.maggram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.maggram.Domains.Contact;
import com.example.maggram.Domains.User;
import com.example.maggram.api.ServiceApi;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactActivity extends AppCompatActivity {

    private String login;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Contact> contactList;
    private RecyclerView.LayoutManager layoutManager;
    private final String URL = "http://10.0.2.2:8080";
    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    ServiceApi serviceApi = retrofit.create(ServiceApi.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        recyclerView = (RecyclerView) findViewById(R.id.rec_cont);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Context context = this;

        Bundle args = getIntent().getExtras();
        login = (String) args.get("login");

        Call<List<Contact>> listCall = serviceApi.getContacts(login);
        listCall.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                contactList = response.body();

                if (contactList != null && contactList.size() == 0){
                    contactList = new ArrayList<>();
                    contactList.add(new Contact(1L, login));
                }

                adapter = new ContactAdapter(context, contactList, login);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                showDialog("Ошибка", "Что-то пошло не так");
            }
        });

    }

    public void onClickMessage(View view){
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra("login", login);
        startActivity(intent);
    }

    public void onClickContactButton(View view){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        View layout = getLayoutInflater().inflate(R.layout.contact_add, null);

        alertDialog.setTitle("Введите имя контакта")
                .setView(layout)
                .setPositiveButton("Готово", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Map<String, String> fields = new HashMap<>();
                        EditText edt = (EditText) layout.findViewById(R.id.edtContAdd);
                        fields.put("login", edt.getText().toString());
                        Call<Contact> contactCall = serviceApi.addContact(login, fields);
                        contactCall.enqueue(new Callback<Contact>() {
                            @Override
                            public void onResponse(Call<Contact> call, Response<Contact> response) {
                                if (response.code() == 400){
                                    showDialog("Ошибка","Аккаунт либо уже добавлен, либо не существует");
                                }
                                else {
                                    showDialog("Успешно", "Контакт добавлен");
                                }
                            }

                            @Override
                            public void onFailure(Call<Contact> call, Throwable t) {
                                showDialog("Ошибка", "Что-то пошло не так");
                            }
                        });
                    }
                });
        alertDialog.create();
        alertDialog.show();
    }

    private void showDialog(String title, String message){
        AlertDialog.Builder a_builder = new AlertDialog.Builder(ContactActivity.this);
        a_builder.setTitle(title);
        a_builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Да", (dialog, which) -> dialog.cancel());
        a_builder.show();
    }
}