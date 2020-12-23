package com.example.maggram.api;

import com.example.maggram.Domains.Auth;
import com.example.maggram.Domains.Contact;
import com.example.maggram.Domains.Message;
import com.example.maggram.Domains.User;

import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceApi {
    @FormUrlEncoded
    @POST("/api/auth/register")
    Call<User> createUser(@FieldMap Map<String, String> fields);

    @GET("/api/auth/login")
    Call<Auth> auth(@Query("login") String login, @Query("password") String password);

    @GET("/api/{login}/contact")
    Call<List<Contact>> getContacts(@Path("login") String login);

    @FormUrlEncoded
    @POST("/api/messages")
    Call<Message> createMessage(@FieldMap Map<String,String> fields);

    @GET("/api/messages/to/{loginTo}")
    Call<List<Message>> getMessagesByLoginTo(@Path("loginTo") String loginTo);

    @FormUrlEncoded
    @POST("/api/{author}/contact")
    Call<Contact> addContact(@Path("author") String author, @FieldMap Map<String, String> fields);



}
