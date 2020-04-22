package com.jk.dayu.jkapp.Interrogation;

import java.io.Serializable;

import lombok.Data;

@Data
public class Condition implements Serializable {

    private long cid;
    private String symptoms;
    private String time;
    private String details;
    private long uid;
    private long did;

    public Condition() {
    }

    public Condition(long cid, String symptoms, String time, String details, long uid, long did) {
        this.cid = cid;
        this.symptoms = symptoms;
        this.time = time;
        this.details = details;
        this.uid = uid;
        this.did = did;
    }
}
