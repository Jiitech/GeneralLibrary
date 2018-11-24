package com.wukj.general.library.permission.sample;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.wukj.general.library.permission.utils.PermissionFail;
import com.wukj.general.library.permission.utils.PermissionHelper;
import com.wukj.general.library.permission.utils.PermissionSucceed;
import com.wukj.general.library.permission.R;

/**
 * 项目名称：GeneralLibrary
 * 创建时间：2018/11/2 0002 下午 5:07
 * 作者：Jonyker
 * 博客：http://www.udevtech.com
 * github：https://github.com/Jiitech
 * 修改人：Jonyker
 * 联系方式：QQ/534098845
 * 修改时间：2018/11/2 0002 下午 5:07
 * 备注：
 * 版本：V.1.0
 * 描述：
 * 1.
 * 2.
 * 3.
 */

public class PermissionActivity extends AppCompatActivity implements View.OnClickListener {


    private Button mCall;

    private static final int REQUEST_PHONE_CODE = 10012;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        mCall = this.findViewById(R.id.call);
        mCall.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (R.id.call == v.getId()){
            PermissionHelper
                    .with(PermissionActivity.this)
                    .requestCode(REQUEST_PHONE_CODE)
                    .requestPermissionList(new String[]{
                            Manifest.permission.CALL_PHONE
                            ,Manifest.permission.WRITE_CONTACTS})
                    .request();
        }

    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        Log.e("call","---------onRequestPermissionsResult:"+requestCode);

        PermissionHelper.requestPermissionResult(this,requestCode,permissions);

    }

    /**
     * 打电话成功
     */
    @PermissionSucceed(requestCode = REQUEST_PHONE_CODE)
    private void getPermissionYes() {

        Log.e("call","拨打成功");

    }

    /**
     * 打电话失败
     */
    @PermissionFail(requestCode = REQUEST_PHONE_CODE)
    private void getPermissionNo() {

        Log.e("call","拨打失败");

    }





}
