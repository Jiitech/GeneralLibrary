package com.wukj.generallibrary.model.bitmap.utils;

import android.os.AsyncTask;

import java.io.BufferedOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
public class HttpUtils extends AsyncTask<String, Integer, Byte> {

    private static ResultListener mResultListener;

    public static void setResultListener(ResultListener listener) {
        mResultListener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected Byte doInBackground(String... strings) {
        String _URL = strings[0];
        HttpURLConnection urlConnection = null;
        BufferedOutputStream out =null;
        try {
            final URL url = new URL(_URL);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(Byte aByte) {
        super.onPostExecute(aByte);
    }

    public void doRequest(String params) {
        this.execute(params);
    }

    public interface ResultListener {
        void onResult(byte bytes);
    }

}
