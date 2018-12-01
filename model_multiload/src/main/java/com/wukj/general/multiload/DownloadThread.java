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
        // 向数据库插入线程信息
        if (!threadDao.isThreadInfoExist(fileEntity.getUrl(), fileEntity.getThreadId())) {
            threadDao.insertThread(fileEntity);
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

            File file = new File(fileEntity.getFileDir(), fileEntity.getFileName());
            randomAccessFile = new RandomAccessFile(file, "rwd");
            randomAccessFile.seek(start);

            // 开始下载
            int finished = fileEntity.getFinished();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_PARTIAL) {
                inputStream = connection.getInputStream();
                byte[] buffer = new byte[4 * 1024];
                int len = -1;
                long time = System.currentTimeMillis();
                while ((len = inputStream.read(buffer)) != -1) {
                    randomAccessFile.write(buffer, 0, len);
                    finished += len;
                    if ((System.currentTimeMillis() - time) > 500) {

                        time = System.currentTimeMillis();
                        int progress = finished / fileEntity.getLength() * 100;
                        fileEntity.getmRateListener().onDownloadSize(progress);

                    }
                    if (isPause) {
                        threadDao.updateThread(fileEntity.getUrl(), fileEntity.getThreadId(), finished);
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
