package com.jk.dayu.jkapp.Interrogation;

import lombok.Data;


@Data
public class Reply {

    private long rid;
    private String content;
    private long uid;
    private long did;
    private long cid;

    public Reply() {
    }

    public Reply(long rid, String content, long uid, long did, long cid) {
        this.rid = rid;
        this.content = content;
        this.uid = uid;
        this.did = did;
        this.cid = cid;
    }
}
