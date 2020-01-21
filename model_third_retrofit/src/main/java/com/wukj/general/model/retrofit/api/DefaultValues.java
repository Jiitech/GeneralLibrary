package com.wukj.general.model.retrofit.api;

/**
 * 项目名称：AndroidGeneralLibrary
 * 创建时间：2020-01-21 09:40
 * 作者：Jonyker
 * 博客：https://www.jianshu.com/u/07642698e7f4
 * github：https://github.com/Jonyker
 * 修改人：Jonyker
 * 联系方式：QQ/534098845
 * 修改时间：2020-01-21 09:40
 * 备注：
 * 版本：V.1.0
 * 描述：
 * 1.
 * 2.
 * 3.
 */
public class DefaultValues {

    // 超时时间
    public final  static int TIME_OUT_CONNECT = 10000;

    // 读写时间
    public final  static int TIME_OUT_READ_WRITE = 10000;


    // Service-Mobile地址
    public final static String BASE_SEARCH_URL = "http://app.xintuyun.cn/";
    // 项目名称
    private final static String PROJECT_MOBILE_XT = "service-mobile/";
    // 获取到达城市列表
    public final static String GET_ARRIVE_STATION_LIST_URL = PROJECT_MOBILE_XT + "machinesearch/pull_layeringcitydes";


}
