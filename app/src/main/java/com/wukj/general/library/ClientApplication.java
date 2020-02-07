package com.wukj.general.library;

import android.app.Application;

import com.wukj.library.xslogger.manager.LogManager;

/**
 *
 * 项目名称：UILibrary
 * 创建时间：2018/11/17 下午8:27
 * 作者：Jonyker
 * 博客：https://www.jianshu.com/u/07642698e7f4
 * github：https://github.com/Jonyker
 * 修改人：Jonyker
 * 联系方式：QQ/534098845
 * 修改时间：2018/11/17 下午8:27
 * 备注：
 * 版本：V.1.0
 * 描述：
 * 1.
 * 2.
 * 3.
 */
public class ClientApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        LogManager.getManager(getApplicationContext()).registerCrashHandler();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        LogManager.getManager(getApplicationContext()).unregisterCrashHandler();
    }




}
