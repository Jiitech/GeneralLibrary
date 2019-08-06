package com.jonyker.mvp.architecture.presenter;

/**
 * 项目名称：Android-MVP-Architecture
 * 类名称：com.jonyker.mvp.architecture.presenter
 * 类描述：
 * 1.从View层获取参数
 * 2.处理Model过来的数据
 * 3.将处理以后的数据交给View层
 * 创建人：Jonyker
 * 创建时间：2017/6/20 0020 下午 1:49
 * 修改人：Jonyker
 * 修改时间：2017/6/20 0020 下午 1:49
 * 修改备注：
 * 版本：V.1.0
 */

public interface WeatherPresenter {

    /**
     * 从View层获取参数
     * @param cityNo 城市编号
     */
    void getWeather(String cityNo);

}
