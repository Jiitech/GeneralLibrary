package com.wukj.general.multiload;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import com.wukj.general.multiload.db.ThreadDao;
import com.wukj.general.multiload.entity.FileInfoEntity;
import com.wukj.general.utils.LogUtils;

/**
 * 项目名称：GeneralLibrary
 * 创建时间：2018/12/1 下午8:05
 * 作者：Jonyker
 * 博客：http://www.udevtech.com
 * github：https://github.com/Jonyker
 * 修改人：Jonyker
 * 联系方式：QQ/534098845
 * 修改时间：2018/12/1 下午8:05
 * 备注：
 * 版本：V.1.0
 * 描述：
 * 1.
 * 2.
 * 3.
 */
public class DownloadThread extends Thread {

    private FileInfoEntity fileEntity = null;
    private ThreadDao threadDao;

    private boolean isPause;
    private boolean isFinish;

    public DownloadThread(FileInfoEntity entity, ThreadDao dao) {
        this.fileEntity = entity;
        this.threadDao = dao;
    }

    @Override
    public void run() {

        if (fileEntity.getFinished() == fileEntity.getLength()) {
            threadDao.deleteThread(fileEntity.getUrl());
            LogUtils.d(this.getClass(), "已经下载完成，删除文件");
        }
        // 向数据库插入线程信息
        if (!threadDao.isThreadInfoExist(fileEntity.getUrl(), fileEntity.getThreadId())) {
            threadDao.insertThread(fileEntity);
            LogUtils.d(this.getClass(), "插入本地数据库");
        }
        HttpURLConnection connection = null;
        RandomAccessFile randomAccessFile = null;
        InputStream inputStream = null;
        try {
            URL url = new URL(fileEntity.getUrl());
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");

            int start = fileEntity.getStart() + fileEntity.getFinished();
            connection.setRequestProperty("Range", "bytes=" + start + "-" + fileEntity.getEnd());

            LogUtils.d(this.getClass(), "开始下载的位置：" + start);
            LogUtils.d(this.getClass(), "文件夹：" + fileEntity.getFileDir());
            LogUtils.d(this.getClass(), "文件名：" + fileEntity.getFileName());
            File file = new File(fileEntity.getFileDir(), fileEntity.getFileName());
            randomAccessFile = new RandomAccessFile(file, "rwd");
            randomAccessFile.seek(start);

            // 开始下载
            int finished = fileEntity.getFinished();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_PARTIAL) {
                inputStream = connection.getInputStream();
                byte[] buffer = new byte[64 * 1024];
                int len = -1;
                while ((len = inputStream.read(buffer)) != -1) {
                    randomAccessFile.write(buffer, 0, len);
                    finished += len;

                    float progress = ((float)finished )/ fileEntity.getLength() * 100;

                    fileEntity.getmRateListener().onDownloadSize(progress);

                    threadDao.updateThread(fileEntity.getUrl(), fileEntity.getThreadId(), finished);
                    if (isPause) {
                        return;
                    }
                }
                threadDao.deleteThread(fileEntity.getUrl(), fileEntity.getThreadId());
                LogUtils.d(this.getClass(), "finished: " + finished + ", and file length: " + fileEntity.getLength());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.disconnect();
                randomAccessFile.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public boolean isPause() {
        return isPause;
    }

    public void setPause(boolean pause) {
        isPause = pause;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }


}
