package com.example.maggram.Domains;


import com.google.gson.annotations.SerializedName;

public class Contact {
    @SerializedName("id")
    private Long id;
    @SerializedName("loginContact")
    private String login;

    public Contact(Long id, String login) {
        this.id = id;
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
