package com.wukj.uilibrary.model.favour;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * 项目名称：UILibrary
 * 创建时间：2018/11/7 0007 下午 4:11
 * 作者：Jonyker
 * 博客：https://www.jianshu.com/u/07642698e7f4
 * github：https://github.com/Jonyker
 * 修改人：Jonyker
 * 联系方式：QQ/534098845
 * 修改时间：2018/11/7 0007 下午 4:11
 * 备注：
 * 版本：V.1.0
 * 描述：
 * 1.防止由于内部持有导致的内存泄露
 * 2.
 * 3.
 */
public class WeakRefHandler extends Handler {
    private WeakReference<Callback> mWeakReference;

    public WeakRefHandler(Callback callback) {
        mWeakReference = new WeakReference<Callback>(callback);
    }

    public WeakRefHandler(Callback callback, Looper looper) {
        super(looper);
        mWeakReference = new WeakReference<Callback>(callback);
    }

    @Override
    public void handleMessage(Message msg) {
        if (mWeakReference != null && mWeakReference.get() != null) {
            Callback callback = mWeakReference.get();
            callback.handleMessage(msg);
        }
    }
}

