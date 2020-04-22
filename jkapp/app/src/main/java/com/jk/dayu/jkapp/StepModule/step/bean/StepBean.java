package com.jk.dayu.jkapp.StepModule.step.bean;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

import lombok.Data;



@Data
@Table("step")
public class StepBean{

    long cid;

    // 指定自增，每个对象需要有一个主键
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    public int id;

    @Column("name")
    public String name;
    @Column("date")
    public String date;
    @Column("step")
    public String step;

    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public int getId() {
        return id;
    }

}
