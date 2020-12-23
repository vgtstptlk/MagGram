package com.example.maggram.Domains;

import com.google.gson.annotations.SerializedName;

import java.security.MessageDigest;
import java.time.LocalDateTime;


// (I already said that because of this project I will burn in hell)

/**
 * User Domain
 */
public class User {
    @SerializedName("id")
    private Long id;
    @SerializedName("login")
    private String login;

    public User(Long id, String login) {
        this.id = id;
        this.login = login;
    }
}
