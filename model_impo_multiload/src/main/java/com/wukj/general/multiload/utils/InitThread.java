package com.wukj.general.multiload.utils;

import android.os.AsyncTask;
import android.os.Handler;

import com.wukj.general.multiload.entity.FileInfoEntity;
import com.wukj.general.utils.LogUtils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 项目名称：GeneralLibrary
 * 创建时间：2018/11/2 0002 下午 5:07
 * 作者：Jonyker
 * 博客：https://www.jianshu.com/u/07642698e7f4
 * github：https://github.com/Jonyker
 * 修改人：Jonyker
 * 联系方式：QQ/534098845
 * 修改时间：2018/11/2 0002 下午 5:07
 * 备注：
 * 版本：V.1.0
 * 描述：
 * 1.
 * 2.
 * 3.
 */
public class InitThread extends AsyncTask<FileInfoEntity, Integer, FileInfoEntity> {

    private Handler mHandler;
    private int DOWNLOAD_MESSAGE;

    public InitThread(Handler handler, int what) {
        this.mHandler = handler;
        this.DOWNLOAD_MESSAGE = what;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected FileInfoEntity doInBackground(FileInfoEntity... infoEntities) {

        FileInfoEntity infoEntity = infoEntities[0];

        // 连接网络文件
        HttpURLConnection connection = null;
        RandomAccessFile randomAccessFile = null;
        try {
            URL url = new URL(infoEntity.getUrl());
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(3000);
            connection.setRequestMethod("GET");

            connection.connect();
            LogUtils.d(this.getClass(),"获取文件信息--start");
            int length = -1;
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // 获取文件的长度
                length = connection.getContentLength();
            }
            LogUtils.d(this.getClass(),"文件长度-----："+length);
            LogUtils.d(this.getClass(),"获取文件信息--end");
            if (length <= 0) {
                return null;
            }
            // 在本地创建文件
            File dir = new File(infoEntity.getFileDir());
            if (!dir.exists()) {
                dir.mkdir();
            }
            File file = new File(dir,infoEntity.getFileName());
            // 设置文件长度
            randomAccessFile = new RandomAccessFile(file, "rwd");
            randomAccessFile.setLength(length);

            infoEntity.setLength(length);

            return infoEntity;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                randomAccessFile.close();
                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(FileInfoEntity infoEntity) {
        super.onPostExecute(infoEntity);

        mHandler.obtainMessage(DOWNLOAD_MESSAGE, infoEntity).sendToTarget();

    }


}
