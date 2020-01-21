package com.wukj.general.model.retrofit.api;

import com.wukj.general.model.retrofit.entity.Translation;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 项目名称：AndroidGeneralLibrary
 * 创建时间：2019-08-31 11:51
 * 作者：Jonyker
 * 博客：https://www.jianshu.com/u/07642698e7f4
 * github：https://github.com/Jonyker
 * 修改人：Jonyker
 * 联系方式：QQ/534098845
 * 修改时间：2019-08-31 11:51
 * 备注：
 * 版本：V.1.0
 * 描述：
 * 1.
 * 2.
 * 3.
 */
public interface Requests {

    // @Headers("Keep-Alive: timeout=5000")
    // @Headers("Connection: close")
    @POST(DefaultValues.GET_ARRIVE_STATION_LIST_URL)
    Call<Translation> getArriveStationList(
              @Query("brandId") String brandId
            , @Query("version") String version
            , @Query("cityId") String cityId
            , @Query("stationId") String stationId
            , @Query("terminalType") String terminalType);

}
