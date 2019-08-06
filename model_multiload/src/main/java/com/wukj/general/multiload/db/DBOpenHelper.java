package com.wukj.general.multiload.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 项目名称：GeneralLibrary
 * 创建时间：2018/12/1 下午2:49
 * 作者：Jonyker
 * 博客：http://www.udevtech.com
 * github：https://github.com/Jonyker
 * 修改人：Jonyker
 * 联系方式：QQ/534098845
 * 修改时间：2018/12/1 下午2:49
 * 备注：
 * 版本：V.1.0
 * 描述：
 * 1.
 * 2.
 * 3.
 */
public class DBOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "download.db";
    private static final int version = 1;

    private static StringBuffer sqlBuffer = new StringBuffer("create table thread_info(_id integer primary key autoincrement, " +
            "thread_id integer, url text, start integer, end integer, finished integer)");

    private static final String DROP_THREADINFO = "drop table if exists thread_info";

    public DBOpenHelper(Context context) {
        super(context, DB_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlBuffer.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_THREADINFO);
        db.execSQL(sqlBuffer.toString());
    }

}
