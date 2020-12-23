package com.vgtstptlk.gramrestapi.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class User{
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String login;
    @JsonIgnore
    private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User() {

    }

}
