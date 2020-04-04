package com.jk.wyq.jkapp.HealthModule;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by wangyuqi on 2018/4/11.
 */

@Table("TimeBean")
public class TimeBean {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    public int id;

    @Column("name")
    public String name;

    @Column("time")
    public String time;

    @Column("tip")
    public String tip;

}
