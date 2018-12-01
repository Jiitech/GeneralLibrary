package com.wukj.general.multiload.fragment;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.wukj.general.common.fragment.SupFragment;
import com.wukj.general.multiload.DownloadManager;
import com.wukj.general.multiload.R;
import com.wukj.general.multiload.entity.FileInfoEntity;
import com.wukj.general.multiload.utils.InitThread;

import java.io.File;

/**
 * 项目名称：GeneralLibrary
 * 创建时间：2018/12/1 下午2:34
 * 作者：Jonyker
 * 博客：http://www.udevtech.com
 * github：https://github.com/Jiitech
 * 修改人：Jonyker
 * 联系方式：QQ/534098845
 * 修改时间：2018/12/1 下午2:34
 * 备注：
 * 版本：V.1.0
 * 描述：
 * 1.
 * 2.
 * 3.
 */
public class FragmentMultiload extends SupFragment {



    @Override
    protected int getCreateVID() {
        return R.layout.fragment_multiload;
    }

    @Override
    protected void onInitV(@NonNull View view, @Nullable Bundle savedInstanceState) {

        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FileInfoEntity infoEntity = new FileInfoEntity();
                infoEntity.setUrl(getAPKFileURL());
                infoEntity.setFileDir(getAPKFilePath());
                infoEntity.setFileName("aaa.apk");
                new InitThread(new InitHandler(),DownloadManager.DOWNLOAD_MESSAGE)
                        .execute(infoEntity);

            }
        });


    }


    private String getAPKFilePath(){
        return Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"apks";
    }


    private String getAPKFileURL(){
        String apk_url = "https://dldir1.qq.com/weixin/android/weixin673android1360.apk";
        return apk_url;
    }

    private class InitHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case DownloadManager.DOWNLOAD_MESSAGE:
                    FileInfoEntity fileInfo = (FileInfoEntity) msg.obj;

                    DownloadManager manager = new DownloadManager(getActivity());
                    manager.download(fileInfo);
                    // 启动下载任务
//                    downloadTask = new DownloadTask(DownloadService.this, fileInfo);
//                    downloadTask.download();
                    break;
                case DownloadManager.DOWNLOAD_CANCEL:
                    FileInfoEntity fileCancelInfo = (FileInfoEntity) msg.obj;
//                    downloadTask = new DownloadTask(DownloadService.this);
//                    downloadTask.cancelDownload(fileCancelInfo);
                    break;
            }
        }
    }





}
