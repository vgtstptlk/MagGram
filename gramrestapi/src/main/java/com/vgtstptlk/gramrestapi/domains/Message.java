package com.vgtstptlk.gramrestapi.domains;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Message {
    @Id
    @GeneratedValue
    private Long id;

    @CreationTimestamp
    private Date timeStamp;

    private String loginFrom;
    private String loginTo;

    private String caption;

    public Message(String loginFrom, String loginTo, String caption) {
        this.loginFrom = loginFrom;
        this.loginTo = loginTo;
        this.caption = caption;
    }

    public Message() {
    }
}
