package com.jk.dayu.jkapp.UserModule;

import java.io.Serializable;

import lombok.Data;

@Data
public class Doctor implements Serializable {
    private long did;
    private String username;
    private String password;
    private String introduce;
    private int role;
}