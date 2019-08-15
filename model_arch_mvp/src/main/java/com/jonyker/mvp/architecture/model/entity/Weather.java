package com.jonyker.mvp.architecture.model.entity;

/**
 * 项目名称：Android-MVP-Architecture
 * 类名称：com.jonyker.mvp.architecture.model.entity.Weather
 * 类描述：
 * 1.接收天气信息实体类
 * 创建人：Jonyker
 * 创建时间：2017/6/20 0020 下午 1:31
 * 修改人：Jonyker
 * 修改时间：2017/6/20 0020 下午 1:31
 * 修改备注：
 * 版本：V.1.0
 */
public class Weather {
    private WeatherInfo weatherinfo;

    public WeatherInfo getWeatherinfo() {
        return weatherinfo;
    }

    public void setWeatherinfo(WeatherInfo weatherinfo) {
        this.weatherinfo = weatherinfo;
    }
}