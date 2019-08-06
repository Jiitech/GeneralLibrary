package com.jonyker.mvp.architecture.presenter;

import com.jonyker.mvp.architecture.model.entity.Weather;

/**
 * 项目名称：Android-MVP-Architecture
 * 类名称：com.jonyker.mvp.architecture.presenter
 * 类描述：
 * 1.Presenter层在Model层监听事件
 * 创建人：Jonyker
 * 创建时间：2017/6/20 0020 下午 1:32
 * 修改人：Jonyker
 * 修改时间：2017/6/20 0020 下午 1:32
 * 修改备注：
 * 版本：V.1.0
 */

public interface OnWeatherListener {

    /**
     * 监听Model层获取数据成功
     * @param weather
     */
    void onSuccess(Weather weather);

    /**
     * 监听Mdel层获取数据失败
     */
    void onError();


}
