package com.example.maggram.Domains;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.Date;


public class Message {

    @SerializedName("id")
    private Long id;
    @SerializedName("loginFrom")
    private String loginFrom;
    @SerializedName("loginTo")
    private String loginTo;
    @SerializedName("caption")
    private String text;
    @SerializedName("timeStamp")
    private Date timeStamp;

    public Message(String loginFrom, String loginTo, String text, Date timeStamp) {
        this.loginFrom = loginFrom;
        this.loginTo = loginTo;
        this.text = text;
        this.timeStamp = timeStamp;
    }

    public String getLoginFrom() {
        return loginFrom;
    }

    public void setLoginFrom(String loginFrom) {
        this.loginFrom = loginFrom;
    }

    public String getLoginTo() {
        return loginTo;
    }

    public void setLoginTo(String loginTo) {
        this.loginTo = loginTo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
