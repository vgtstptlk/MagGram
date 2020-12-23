package com.example.maggram;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maggram.Domains.Contact;
import com.example.maggram.api.ServiceApi;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private String loginFrom;
    private List<Contact> contactList;
    private Context context;
    private final String URL = "http://10.0.2.2:8080";
    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    ServiceApi serviceApi = retrofit.create(ServiceApi.class);

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView login;
        ImageView imageView;
        Button button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cv);
            login = itemView.findViewById(R.id.textView3);
            imageView = itemView.findViewById(R.id.imageView2);
            button = itemView.findViewById(R.id.btnMessage);
        }
    }

    public ContactAdapter(Context context, List<Contact> contactList, String loginFrom){
         this.context = context;
         this.contactList = contactList;
         this.loginFrom = loginFrom;

    }

    @NonNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.contact, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, int position) {
        String login;
        login = contactList.get(position).getLogin();
        holder.login.setText(login);
        holder.imageView.setImageResource(R.drawable.man);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactAdapter.this.context, SendActivity.class);
                intent.putExtra("loginTo", login);
                intent.putExtra("loginFrom", loginFrom);
                holder.button.getContext().startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return contactList.size();
    }
}
