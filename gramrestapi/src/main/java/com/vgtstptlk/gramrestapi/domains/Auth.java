package com.vgtstptlk.gramrestapi.domains;

import lombok.Data;

@Data
public class Auth {

    private String login;
    private Boolean result;

    public Auth(String login, boolean result) {
        this.login = login;
        this.result = result;
    }

}
