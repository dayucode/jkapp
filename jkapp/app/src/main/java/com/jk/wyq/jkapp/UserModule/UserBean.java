package com.jk.wyq.jkapp.UserModule;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.Mapping;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;
import com.litesuits.orm.db.enums.Relation;

import java.util.ArrayList;

/**
 * Created by wangyuqi on 2018/4/7.
 */

@Table("user")
public class UserBean {

    @PrimaryKey(AssignType.BY_MYSELF)
    @Column("name")
    public String name;

    @Column("password")
    public String password;

    // 积分
    @Column("point")
    public String point;

}
