package com.jonyker.mvp.architecture.model.entity;

/**
 * 项目名称：Android-MVP-Architecture
 * 类名称：com.jonyker.mvp.architecture.model.entity.WeatherInfo
 * 类描述：
 * 1.天气信息实体
 * 创建人：Jonyker
 * 创建时间：2017/6/20 0020 下午 1:31
 * 修改人：Jonyker
 * 修改时间：2017/6/20 0020 下午 1:31
 * 修改备注：
 * 版本：V.1.0
 */
public class WeatherInfo {
    private String city;
    private String cityid;
    private String temp;
    private String WD;
    private String WS;
    private String SD;
    private String WSE;
    private String time;
    private String njd;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWD() {
        return WD;
    }

    public void setWD(String WD) {
        this.WD = WD;
    }

    public String getWS() {
        return WS;
    }

    public void setWS(String WS) {
        this.WS = WS;
    }

    public String getSD() {
        return SD;
    }

    public void setSD(String SD) {
        this.SD = SD;
    }

    public String getWSE() {
        return WSE;
    }

    public void setWSE(String WSE) {
        this.WSE = WSE;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNjd() {
        return njd;
    }

    public void setNjd(String njd) {
        this.njd = njd;
    }
}