package com.wukj.general.multiload.entity;

import com.wukj.general.multiload.RateListener;

import java.io.Serializable;

/**
 * 项目名称：GeneralLibrary
 * 创建时间：2018/12/1 下午3:00
 * 作者：Jonyker
 * 博客：http://www.udevtech.com
 * github：https://github.com/Jonyker
 * 修改人：Jonyker
 * 联系方式：QQ/534098845
 * 修改时间：2018/12/1 下午3:00
 * 备注：
 * 版本：V.1.0
 * 描述：
 * 1.
 * 2.
 * 3.
 */
public class FileInfoEntity implements Serializable {

    // 线程ID
    private int threadId;
    // 下载资源的地址
    private String url;
    //下载资源的开始处
    private int start;
    //下载资源的结束处
    private int end;
    //资源已经的下载量
    private int finished;
    // 文件Id，用于标识文件
    private int fileId;
    // 文件的名称
    private String fileDir;
    // 文件的名称
    private String fileName;
    // 文件的长度，也就是大小
    private int length;

    private RateListener mRateListener;

    public FileInfoEntity() {
    }

    public FileInfoEntity(int threadId, String url, int start, int end, int length) {
        this.threadId = threadId;
        this.url = url;
        this.start = start;
        this.end = end;
        this.length = length;
    }

    public RateListener getmRateListener() {
        return mRateListener;
    }

    public void setmRateListener(RateListener mRateListener) {
        this.mRateListener = mRateListener;
    }

    public int getThreadId() {
        return threadId;
    }

    public void setThreadId(int threadId) {
        this.threadId = threadId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getFinished() {
        return finished;
    }

    public void setFinished(int finished) {
        this.finished = finished;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getFileDir() {
        return fileDir;
    }

    public void setFileDir(String fileDir) {
        this.fileDir = fileDir;
    }

    @Override
    public String toString() {
        return "FileInfoEntity{" +
                "threadId=" + threadId +
                ", url='" + url + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", finished=" + finished +
                ", fileId=" + fileId +
                ", fileDir='" + fileDir + '\'' +
                ", fileName='" + fileName + '\'' +
                ", length=" + length +
                ", mRateListener=" + mRateListener +
                '}';
    }
}
