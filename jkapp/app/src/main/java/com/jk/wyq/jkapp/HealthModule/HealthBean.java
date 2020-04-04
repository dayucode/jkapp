package com.jk.wyq.jkapp.HealthModule;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by wangyuqi on 2018/3/1.
 */

@Table("health")
public class HealthBean {
    @PrimaryKey(AssignType.BY_MYSELF)
    @Column("name")
    public String name;

    @Column("date")
    public String date;

    // 基础测试
    // 年龄
    @Column("age")
    public String age;

    // 体重
    @Column("weight")
    public String weight;

    // 身高
    @Column("height")
    public String height;

    // BMI
    @Column("bmi")
    public String bmi;

    // 心跳
    @Column("beat")
    public String beat;

// 进阶测试

    // 高血压
    @Column("presshigh")
    public String presshigh;

    // 低血压
    @Column("presslow")
    public String presslow;

    // 血糖
    @Column("bloodsugar")
    public String bloodsugar;
}
