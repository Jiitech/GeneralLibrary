package com.jonyker.mvp.architecture.model.impl;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jonyker.mvp.architecture.model.WeatherModel;
import com.jonyker.mvp.architecture.model.entity.Weather;
import com.jonyker.mvp.architecture.presenter.OnWeatherListener;
import com.jonyker.mvp.architecture.utils.VolleyRequest;

/**
 * 项目名称：Android-MVP-Architecture
 * 类名称：com.jonyker.mvp.architecture.model.impl
 * 类描述：
 * 创建人：Jonyker
 * 创建时间：2017/6/20 0020 下午 1:47
 * 修改人：Jonyker
 * 修改时间：2017/6/20 0020 下午 1:47
 * 修改备注：
 * 版本：V.1.0
 */

public class WeatherModelImpl implements WeatherModel {

    @Override
    public void loadWeather(String cityNo, final OnWeatherListener listener) {
        /*数据层操作*/
        VolleyRequest.newInstance().newGsonRequest("http://www.weather.com.cn/data/sk/" + cityNo + ".html",
                Weather.class, new Response.Listener<Weather>() {
                    @Override
                    public void onResponse(Weather weather) {
                        if (weather != null) {
                            listener.onSuccess(weather);
                        } else {
                            listener.onError();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onError();
                    }
                });
    }


}
