package com.jonyker.mvp.architecture.ui.view;

import com.jonyker.mvp.architecture.model.entity.Weather;

/**
 * 项目名称：Android-MVP-Architecture
 * 类名称：com.jonyker.mvp.architecture.ui.view
 * 类描述：
 * 1.在View层显示和隐藏Loading
 * 2.从Presenter层获取数据
 * 3.
 * 创建人：Jonyker
 * 创建时间：2017/6/20 0020 下午 1:56
 * 修改人：Jonyker
 * 修改时间：2017/6/20 0020 下午 1:56
 * 修改备注：
 * 版本：V.1.0
 */

public interface WeatherView {

    /**
     *显示Loading
     */
    void showLoading();

    /**
     * 隐藏Loading
     */
    void hideLoading();

    /**
     * 显示错误信息
     */
    void showError();

    /**
     * 从Presenter获取数据
     * @param weather 带有数据的天气实体类
     */
    void setWeather(Weather weather);
}
