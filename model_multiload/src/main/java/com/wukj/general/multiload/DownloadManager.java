package com.wukj.general.multiload;

import android.content.Context;

import com.wukj.general.multiload.db.ThreadDao;
import com.wukj.general.multiload.db.ThreadDaoImpl;
import com.wukj.general.multiload.entity.FileInfoEntity;

import java.util.List;

/**
 * 项目名称：GeneralLibrary
 * 创建时间：2018/12/1 下午8:05
 * 作者：Jonyker
 * 博客：http://www.udevtech.com
 * github：https://github.com/Jiitech
 * 修改人：Jonyker
 * 联系方式：QQ/534098845
 * 修改时间：2018/12/1 下午8:05
 * 备注：
 * 版本：V.1.0
 * 描述：
 * 1.
 * 2.
 * 3.
 *  
 */
public class DownloadManager {


    public static final int DOWNLOAD_MESSAGE = 1;
    public static final int DOWNLOAD_CANCEL = 2;

    private ThreadDao mThreadDao;

    public DownloadManager(Context context) {
        mThreadDao = new ThreadDaoImpl(context);
    }

    public void download(FileInfoEntity infoEntity) {
        // 读取数据库的线程信息
        List<FileInfoEntity> threadInfos = mThreadDao.queryThread(infoEntity.getUrl());
        FileInfoEntity entity = null;
        if (threadInfos.size() == 0) {
            entity = new FileInfoEntity(0, infoEntity.getUrl(), 0, 0, infoEntity.getLength(), rateListener);
        } else {
            entity = threadInfos.get(0);
        }
        new DownloadThread(entity, mThreadDao).start();
    }

    private RateListener rateListener = new RateListener() {
        @Override
        public void onDownloadSize(int progress) {

        }
    };
}
