package com.jk.dayu.jkapp.HealthModule;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

import lombok.Data;


@Data
@Table("TimeBean")
public class TimeBean {
    public long cid;

    @PrimaryKey(AssignType.AUTO_INCREMENT)
    public int id;

    @Column("name")
    public String name;

    @Column("time")
    public String time;

    @Column("tip")
    public String tip;
}
