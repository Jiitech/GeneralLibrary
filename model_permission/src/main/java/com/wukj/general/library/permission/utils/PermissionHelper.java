package com.wukj.general.library.permission.utils;

import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;

import java.util.List;

/**
 * CopyRight: wkj
 * Time：2017/2/6.14:17
 * Author: wkj
 * QQ:534098845
 * Descirption:
 */
public class PermissionHelper {

    private Object mObject;
    private int mRequestCode;
    private String[] mRequestPermission;

    private PermissionHelper(Object object) {
        this.mObject = object;
    }

    public static void requestPermissions(Activity activity, int requestCode, String requestPermission) {
        PermissionHelper
                .with(activity)
                .requestCode(requestCode)
                .requestPermissionList(requestPermission)
                .request();
    }

    public static void requestPermissions(Fragment fragment, int requestCode, String requestPermission) {
        PermissionHelper
                .with(fragment)
                .requestCode(requestCode)
                .requestPermissionList(requestPermission)
                .request();
    }

    //链式传递
    //1.1传递class类型
    public static PermissionHelper with(Activity activity) {
        return new PermissionHelper(activity);
    }

    public static PermissionHelper with(Fragment fragment) {
        return new PermissionHelper(fragment);
    }

    //1.2传递请求码
    public PermissionHelper requestCode(int requestCode) {
        this.mRequestCode = requestCode;
        return this;
    }

    //1.3传递请求权限数组
    public PermissionHelper requestPermissionList(String... requestPermission) {
        this.mRequestPermission = requestPermission;
        return this;
    }


    /**
     * 判断和发起请求
     */
    public void request() {

        //1.1判断是不是6.0以上的版本
        if (!PermissionUtils.isOverMarshmallow()) {
            PermissionUtils.excuteSuccesseedMethod(mObject,mRequestCode);
            return;
        }

        //1.2如果不是6.0以上的,执行成功的方法
        //1.3如果是6.0以上的
        List<String> deniedPermissions=PermissionUtils.getDeniedPermissions(mObject,mRequestPermission);
        //1.3.1判断权限是不是被授予过 如果授予过
        if (deniedPermissions.size()==0){
            PermissionUtils.excuteSuccesseedMethod(mObject,mRequestCode);
        }else{
            //1.3.2 如果没有授予过的权限
            ActivityCompat.requestPermissions(PermissionUtils.getActivity(mObject)
                    ,deniedPermissions.toArray(new String[deniedPermissions.size()])
                    ,mRequestCode);
        }

    }

    /**
     * 处理请求结果后结果
     */
    public static void requestPermissionResult(Object object,int requestCode,String[] permissions){

        List<String> deniedPermissions=PermissionUtils.getDeniedPermissions(object,permissions);

        //1.1判断请求结果都同意，执行成功的方法
        if (deniedPermissions.size()==0){
            PermissionUtils.excuteSuccesseedMethod(object,requestCode);
        }else{
            //1.2请求结果，其中包含没有同意的请求，执行失败的方法
            PermissionUtils.excuteFailMethod(object,requestCode);
        }

    }

}
