package com.vgtstptlk.gramrestapi.domains;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Contact {
    @Id
    @GeneratedValue
    private Long id;
    private String author;
    private String loginContact;

    public Contact(String author, String loginContact) {
        this.author = author;
        this.loginContact = loginContact;
    }

    public Contact() {

    }
}
