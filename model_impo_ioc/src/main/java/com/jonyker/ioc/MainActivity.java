package com.jonyker.ioc;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.jonyker.ioc.view.CheckNet;
import com.jonyker.ioc.view.OnClick;
import com.jonyker.ioc.view.ViewById;
import com.jonyker.ioc.view.ViewUtils;

/**
 * CopyRight: wkj
 * Time：2017/2/4.16:34
 * Author: wkj
 * QQ:534098845
 * Descirption:
 */
public class MainActivity extends AppCompatActivity {

    @ViewById(R.id.click_bt1)
    private Button mBtn1;
    @ViewById(R.id.click_bt2)
    private Button mBtn2;

    private boolean isClick=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewUtils.bondInit(this);

        mBtn1.setText("文字");

    }
    @OnClick({R.id.click_bt1,R.id.click_bt2})
    @CheckNet
    private void excuteMethod(){

        Log.e("annotation","执行方法");
        if (isClick){
            mBtn1.setBackgroundColor(Color.parseColor("#FFB38A"));
            mBtn2.setBackgroundColor(Color.parseColor("#00B38A"));
        }else{
            mBtn1.setBackgroundColor(Color.parseColor("#00B38A"));
            mBtn2.setBackgroundColor(Color.parseColor("#FFB38A"));
        }
        isClick=!isClick;

    }
}
