package com.example.maggram.Domains;

import com.google.gson.annotations.SerializedName;

public class Auth {

    @SerializedName("login")
    private String login;
    @SerializedName("result")
    private Boolean result;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
