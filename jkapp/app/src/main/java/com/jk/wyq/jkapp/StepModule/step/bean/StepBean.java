package com.jk.wyq.jkapp.StepModule.step.bean;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by dylan on 2016/1/30.
 */

@Table("step")
public class StepBean{

    // 指定自增，每个对象需要有一个主键
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    public int id;

    @Column("name")
    public String name;
    @Column("date")
    public String date;
    @Column("step")
    public String step;

    @Override
    public String toString() {
        return "StepData{" +
                "id=" + id +
                "name="+name+
                ", date='" + date + '\'' +
                ", step='" + step + '\'' +
                '}';
    }
}
