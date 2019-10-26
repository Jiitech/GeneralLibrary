package com.wukj.general.multiload.fragment;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.wukj.general.common.fragment.SupFragment;
import com.wukj.general.multiload.DownloadManager;
import com.wukj.general.multiload.R;
import com.wukj.general.multiload.RateListener;
import com.wukj.general.multiload.entity.FileInfoEntity;
import com.wukj.general.multiload.utils.InitThread;
import com.wukj.general.utils.LogUtils;

import java.io.File;
import java.text.DecimalFormat;

/**
 * 项目名称：GeneralLibrary
 * 创建时间：2018/12/1 下午2:34
 * 作者：Jonyker
 * 博客：https://www.jianshu.com/u/07642698e7f4
 * github：https://github.com/Jonyker
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


    private Button mBtn;

    @Override
    protected int getCreateVID() {
        return R.layout.fragment_multiload;
    }

    @Override
    protected void onInitV(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mBtn = view.findViewById(R.id.button);
        mBtn.setOnClickListener(new View.OnClickListener() {
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
        String apk_url = "https://qd.myapp.com/myapp/qqteam/Androidlite/qqlite_3.7.1.704_android_r110206_GuanWang_537057973_release_10000484.apk";
        return apk_url;
    }

    private class InitHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case DownloadManager.DOWNLOAD_MESSAGE:
                    FileInfoEntity fileInfo = (FileInfoEntity) msg.obj;
                    LogUtils.d(this.getClass(),"文件信息："+fileInfo.toString());
                    DownloadManager manager = new DownloadManager(getActivity());
                    manager.download(fileInfo,rateListener);
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



    private RateListener rateListener = new RateListener() {
        @Override
        public void onDownloadSize(float progress) {
            LogUtils.d(this.getClass(),"下载进度："+progress);

            mBtn.setText("下载("+new DecimalFormat("#0.00").format(progress)+"%)");

        }
    };

}
