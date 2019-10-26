package com.wukj.general.multiload.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wukj.general.multiload.entity.FileInfoEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：GeneralLibrary
 * 创建时间：2018/12/1 下午7:33
 * 作者：Jonyker
 * 博客：https://www.jianshu.com/u/07642698e7f4
 * github：https://github.com/Jonyker
 * 修改人：Jonyker
 * 联系方式：QQ/534098845
 * 修改时间：2018/12/1 下午7:33
 * 备注：
 * 版本：V.1.0
 * 描述：
 * 1.
 * 2.
 * 3.
 */
public class ThreadDaoImpl implements ThreadDao {

    private DBOpenHelper sqliteDBHelper;

    public ThreadDaoImpl(Context context) {
        sqliteDBHelper = new DBOpenHelper(context);
    }
    private static StringBuffer sql_insert = new StringBuffer("insert into thread_info(thread_id, url, start, end, finished) values(?,?,?,?,?)");
    private static StringBuffer sql_delete_id = new StringBuffer("delete from thread_info where url = ? and thread_id = ?");
    private static StringBuffer sql_delete_url =new StringBuffer("delete from thread_info where url = ?");
    private static StringBuffer sql_update = new StringBuffer("update thread_info set finished = ? where url = ? and thread_id = ?");
    private static StringBuffer sql_select =new StringBuffer("select * from thread_info where url = ? and thread_id = ?");


    @Override
    public void insertThread(FileInfoEntity threadInfo) {
        SQLiteDatabase database = sqliteDBHelper.getWritableDatabase();
        Object[] objects = new Object[]{
                threadInfo.getThreadId(), threadInfo.getUrl(), threadInfo.getStart(), threadInfo.getEnd(), threadInfo.getFinished()
        };
        database.execSQL(sql_insert.toString(), objects);
        database.close();

    }

    @Override
    public void deleteThread(String url, int threadId) {
        SQLiteDatabase database = sqliteDBHelper.getWritableDatabase();
        Object[] objects = new Object[]{
                url, threadId
        };
        database.execSQL(sql_delete_id.toString(), objects);
        database.close();
    }

    @Override
    public void deleteThread(String url) {
        SQLiteDatabase database = sqliteDBHelper.getWritableDatabase();
        Object[] objects = new Object[]{
                url
        };
        database.execSQL(sql_delete_url.toString(), objects);
        database.close();
    }

    @Override
    public void updateThread(String url, int threadId, int finished) {
        SQLiteDatabase database = sqliteDBHelper.getWritableDatabase();
        Object[] objects = new Object[]{
                finished, url, threadId
        };
        database.execSQL(sql_update.toString(), objects);
        database.close();
    }

    @Override
    public List<FileInfoEntity> queryThread(String url) {
        SQLiteDatabase database = sqliteDBHelper.getWritableDatabase();
        List<FileInfoEntity> threadInfos = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from thread_info where url = ?", new String[]{url});
        while (cursor.moveToNext()) {
            FileInfoEntity threadInfo = new FileInfoEntity();
            threadInfo.setThreadId(cursor.getInt(cursor.getColumnIndex("thread_id")));
            threadInfo.setUrl(cursor.getString(cursor.getColumnIndex("url")));
            threadInfo.setStart(cursor.getInt(cursor.getColumnIndex("start")));
            threadInfo.setEnd(cursor.getInt(cursor.getColumnIndex("end")));
            threadInfo.setFinished(cursor.getInt(cursor.getColumnIndex("finished")));

            threadInfos.add(threadInfo);
        }
        cursor.close();
        database.close();
        return threadInfos;
    }

    @Override
    public boolean isThreadInfoExist(String url, int threadId) {
        SQLiteDatabase database = sqliteDBHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery(sql_select.toString(), new String[]{url, threadId + ""});

        boolean isExist = cursor.moveToNext();
        cursor.close();
        database.close();
        return isExist;
    }
}
