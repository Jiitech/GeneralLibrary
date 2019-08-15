package com.wukj.generallibrary.model.volley;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Volley;
import com.android.volley.cache.BitmapImageCache;
import com.android.volley.cache.SimpleImageLoader;

/**
 *  项目名称：UILibrary
 *  创建时间：2018/11/17 上午11:59
 *  作者：Jonyker
 *  博客：http://www.udevtech.com
 *  github：https://github.com/Jonyker
 *  修改人：Jonyker
 *  联系方式：QQ/534098845
 *  修改时间：2018/11/17 上午11:59
 *  备注：
 *  版本：V.1.0
 *  描述：
 *  1.
 *  2.
 *  3.
 *  
 */
public class VolleyUtils {


    private static RequestQueue mRequestQueue;
    private static SimpleImageLoader mImageLoader;

    private VolleyUtils() {
        // no instances
    }

    public static void init(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
        mImageLoader = new SimpleImageLoader(mRequestQueue, BitmapImageCache.getInstance(null));
    }

    public static RequestQueue getRequestQueue() {
        if (mRequestQueue != null) {
            return mRequestQueue;
        } else {
            throw new IllegalStateException("RequestQueue not initialized");
        }
    }

    /**
     * Returns instance of ImageLoader initialized with {@see FakeImageCache} which effectively means
     * that no memory caching is used. This is useful for images that you know that will be show
     * only once.
     *
     * @return
     */
    public static SimpleImageLoader getImageLoader() {
        if (mImageLoader != null) {
            return mImageLoader;
        } else {
            throw new IllegalStateException("ImageLoader not initialized");
        }
    }
}
