package com.wukj.general.model.retrofit;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.wukj.general.common.fragment.SupListFragment;
import com.wukj.general.model.retrofit.api.DefaultValues;
import com.wukj.general.model.retrofit.api.Requests;
import com.wukj.general.model.retrofit.entity.Translation;
import com.wukj.general.utils.LogUtils;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFragment extends SupListFragment {

    public static final String[] TITLES = {"请求乘客列表", "请求乘客列表"};

    @Override
    protected int getCreateVID() {
        return R.layout.fragment_retrofit;
    }

    @Override
    protected void onInitV(@NonNull View view, @Nullable Bundle savedInstanceState) {

        //设置ListView为单选模式
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        // 给listView设置adapter显示列表
        setListAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, TITLES));
        //默认选中第一个item
        getListView().setItemChecked(0, true);

    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        switch (position){
            case 0:
                getArriveStations();
                break;
            case 1:
                // getArriveStations();
                break;
        }

    }


    public void getArriveStations() {


//        http://app.xintuyun.cn/service-mobile/machinesearch/pull_la
//        // yeringcitydes?brandId=&version=&cityId=&stationId=&terminalType=S4

        Requests request = getRetrofit(DefaultValues.BASE_SEARCH_URL);
        Call<Translation>call = request.getArriveStationList("", "", "37010001","370104002", "S4");
        call.enqueue(new Callback<Translation>() {
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {


                LogUtils.d(this.getClass(), "网络请求--成功:" + response.isSuccessful());

            }

            @Override
            public void onFailure(Call<Translation> call, Throwable throwable) {
                LogUtils.d(this.getClass(), "网络请求--失败:" + throwable.getMessage());

            }
        });

    }

    protected Requests getRetrofit(String baseURL) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                // 打印retrofit日志
                LogUtils.d(this.getClass(), "网络请求--内容:" + message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(20, TimeUnit.SECONDS)
                // 这里设置的超时，并不是整个传输超时，而是两个字节之间的超时，如果网络弱，数据大，就会卡住
                .readTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Requests.class);
    }

}
