package com.example.maggram;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maggram.Domains.Message;
import com.example.maggram.api.ServiceApi;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder>{
    private List<Message> messageList;
    private Context context;
    private final String URL = "http://10.0.2.2:8080";
    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    ServiceApi serviceApi = retrofit.create(ServiceApi.class);

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView loginView;
        TextView messageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cv2);
            loginView = itemView.findViewById(R.id.textViewLoginMessage);
            messageView = itemView.findViewById(R.id.textViewMessage);
        }
    }

    public ChatAdapter(Context context, List<Message> messageList){
        this.context = context;
        this.messageList = messageList;

    }

    @NonNull
    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.message, parent, false);
        ChatAdapter.ViewHolder viewHolder = new ChatAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.ViewHolder holder, int position) {
        holder.loginView.setText(messageList.get(position).getLoginFrom());
        holder.messageView.setText(messageList.get(position).getText());
    }



    @Override
    public int getItemCount() {
        return messageList.size();
    }
}
