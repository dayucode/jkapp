package com.jk.dayu.jkapp.Interrogation;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	static final String DB_NAME = "DB";
	static final int VERSION = 1;
	public static final String TABLE_USER="Users";
	public static final String TABLE_CONDITION="Condition";
	public static final String TABLE_REPLY="Reply";




	public static final String CREATE_USER="create table "+TABLE_USER+"(" +
			"id integer primary key autoincrement," +
			"username text," +
			"password text," +
			"age integer," +
			"sex text," +
			"role integer default 0)";

	public static final String CREATE_CONDITION="create table "+TABLE_CONDITION+"(" +
			"id integer primary key autoincrement," +
			"symptoms text," +
			"time text," +
			"userid integer," +
			"detial text)";

	public static final String CREATE_REPLY="create table "+TABLE_REPLY+"(" +
			"id integer primary key autoincrement," +
			"content text," +
			"cid integer," +
			"uid integer)";

	private Context mContext;



	public DBHelper(Context context) {
		super(context, DB_NAME, null, VERSION);
		mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_USER);
		db.execSQL(CREATE_CONDITION);
		db.execSQL(CREATE_REPLY);
		db.execSQL("insert into Users(username,password,age,sex,role) values('admin','admin',30,'男',1)");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		System.out.println("======db update=====");
		db.execSQL("drop table if exists Users");
		db.execSQL("drop table if exists Condition");
		db.execSQL("drop table if exists Reply");
		onCreate(db);
	}

}
