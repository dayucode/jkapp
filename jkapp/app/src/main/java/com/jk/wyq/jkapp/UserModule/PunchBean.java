package com.jk.wyq.jkapp.UserModule;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by wangyuqi on 2018/4/19.
 */

public class PunchBean {
    // 指定自增，每个对象需要有一个主键
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    public int id;

    @Column("name")
    public String name;
    @Column("date")
    public String date;

}
