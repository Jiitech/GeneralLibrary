package com.wukj.general.utils;

import android.util.Log;

/**
 * 项目名称：GeneralLibrary
 * 创建时间：2018/11/2 0002 下午 5:07
 * 作者：Jonyker
 * 博客：http://www.udevtech.com
 * github：https://github.com/Jiitech
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
public class LogUtils {

    private static final String DEFAULT_TAG = "LogUtils";
    public static boolean isDebug = true;

    public static void d(Class clazz,String printMsg){
        if (isDebug){
            String sampleName = clazz.getSimpleName();
            Log.e(DEFAULT_TAG,"["+sampleName+"]---: "+printMsg);
        }
    }

}
