package com.jonyker.mvp.architecture.model;

import com.jonyker.mvp.architecture.presenter.OnWeatherListener;

/**
 * 项目名称：Android-MVP-Architecture
 * 类名称：com.jonyker.mvp.architecture.model.entity
 * 类描述：
 * 1.从Presenter层获取参数到Model层
 * 2.在Model层请求网络数据
 * 3.获取处理后的结果数据传递到Presenter层
 * 4.向Presenter层暴露接口
 * 创建人：Jonyker
 * 创建时间：2017/6/20 0020 下午 1:31
 * 修改人：Jonyker
 * 修改时间：2017/6/20 0020 下午 1:31
 * 修改备注：
 * 版本：V.1.0
 */

public interface WeatherModel {

    /**
     * Model层请求数据的方法
     * @param cityNo    城市编号
     * @param listener  Presenter监听
     */
    void loadWeather(String cityNo, OnWeatherListener listener);

}
