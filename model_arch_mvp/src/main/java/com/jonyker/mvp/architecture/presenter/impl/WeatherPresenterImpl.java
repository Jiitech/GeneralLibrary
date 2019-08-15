package com.jonyker.mvp.architecture.presenter.impl;

import com.jonyker.mvp.architecture.model.WeatherModel;
import com.jonyker.mvp.architecture.model.entity.Weather;
import com.jonyker.mvp.architecture.model.impl.WeatherModelImpl;
import com.jonyker.mvp.architecture.presenter.OnWeatherListener;
import com.jonyker.mvp.architecture.presenter.WeatherPresenter;
import com.jonyker.mvp.architecture.ui.view.WeatherView;

/**
 * 项目名称：Android-MVP-Architecture
 * 类名称：com.jonyker.mvp.architecture.presenter.impl
 * 类描述：
 * 1.在Presenter层中处理View层和Model层
 * 创建人：Jonyker
 * 创建时间：2017/6/20 0020 下午 2:02
 * 修改人：Jonyker
 * 修改时间：2017/6/20 0020 下午 2:02
 * 修改备注：
 * 版本：V.1.0
 */

public class WeatherPresenterImpl implements WeatherPresenter , OnWeatherListener{


    private WeatherView weatherView;
    private WeatherModel WeatherModel;

    /**
     * 1.通过构造，将View层中的回调的引用放入Presenter层
     * 2.在构造中，new实例的方式将Model层的引用放入Presenter层
     * @param wView
     */
    public WeatherPresenterImpl(WeatherView wView){
        this.weatherView=wView;
        WeatherModel=new WeatherModelImpl();
    }


    @Override
    public void getWeather(String cityNo) {
        /**
         * View层显示Loading
         */
        weatherView.showLoading();

        /**
         * Model层请求网络数据，并且在Presenter层设置监听
         */
        WeatherModel.loadWeather(cityNo,this);

    }


    @Override
    public void onSuccess(Weather weather) {
        /**
         * 1.Model层监听到数据请求成功以后，通知View层隐藏Loading
         * 2.Model层将获取数据回调到Presenter层
         * 3.Presenter层将数据回调到View层
         */
        weatherView.hideLoading();
        weatherView.setWeather(weather);
    }

    @Override
    public void onError() {
        /**
         * Model层告诉Presenter层请求数据失败，View层隐藏Loading，显示错误信息
         */
        weatherView.hideLoading();
        weatherView.showError();
    }
}
