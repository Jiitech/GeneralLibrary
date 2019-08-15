package com.jonyker.mvp.architecture;

import android.app.Application;

import com.jonyker.mvp.architecture.utils.VolleyRequest;

/**
 * 项目名称：Android-MVP-Architecture
 * 类名称：com.jonyker.mvp.architecture.common
 * 类描述：
 * 创建人：Jonyker
 * 创建时间：2017/6/20 0020 下午 1:25
 * 修改人：Jonyker
 * 修改时间：2017/6/20 0020 下午 1:25
 * 修改备注：
 * 版本：V.1.0
 */

public class MVPApplication extends Application{


    private static MVPApplication instance;

    public MVPApplication() {
        instance = this;
    }

    public static Application getContext() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        VolleyRequest.buildRequestQueue(this);
    }



}
