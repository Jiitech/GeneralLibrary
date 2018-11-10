package com.wukj.model.permission.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * CopyRight: wkj
 * Time：2017/2/6.14:17
 * Author: wkj
 * QQ:534098845
 * Descirption:
 */
public class PermissionUtils {

    private PermissionUtils() {
        throw new UnsupportedOperationException();
    }

    /**
     * 判断当前版本是否超过6.0
     *
     * @return
     */
    public static boolean isOverMarshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    /**
     * 执行成功以后的方法
     *
     * @param reflectObject
     * @param requestCode
     */
    public static void excuteSuccesseedMethod(Object reflectObject, int requestCode) {

        /**
         * 获取当前class中的methods
         */
        Method[] methods = reflectObject.getClass().getDeclaredMethods();

        /**
         * 遍历所有的method
         */
        for (Method method :
                methods) {
            /**
             * 获取当前的method是否有打标记的注解
             */
            PermissionSucceed successedMetod = method.getAnnotation(PermissionSucceed.class);
            /**
             * 如果method包含有PermissionSucceed注解
             */
            if (successedMetod != null) {
                /**
                 * 获取注解中的requestCode是否和调用处给code一直就执行注解处的方法
                 */
                int methodCode = successedMetod.requestCode();
                /**
                 * 请求码一致
                 */
                if (methodCode == requestCode) {

                    excuteMethod(reflectObject, method);
                }
            }
        }
    }

    /**
     * 执行注解标记的方法
     *
     * @param reflectObject
     * @param method
     */
    private static void excuteMethod(Object reflectObject, Method method) {
        /**
         * 通过反射来执行方法
         */
        try {
            //允许执行私有的方法
            method.setAccessible(true);
//          method.invoke(reflectObject, new String[]{});
            method.invoke(reflectObject);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取没有同意的权限集合
     *
     * @param object
     * @param requestPermissions
     * @return
     */
    public static List<String> getDeniedPermissions(Object object, String[] requestPermissions) {

        List<String> deniedPermissions = new ArrayList<>();
        for (String permission :
                requestPermissions) {
            if (ContextCompat.checkSelfPermission(getActivity(object), permission) == PackageManager.PERMISSION_DENIED) {
                deniedPermissions.add(permission);
            }
        }
        return deniedPermissions;
    }

    /**
     * 获取Activity
     *
     * @param object
     * @return
     */
    public static Activity getActivity(Object object) {

        if (object instanceof Activity) {
            return (Activity) object;
        }

        if (object instanceof Fragment) {
            return ((Fragment) object).getActivity();
        }
        return null;
    }

    /**
     * 执行失败以后的方法
     *
     * @param reflectObject
     * @param requestCode
     */
    public static void excuteFailMethod(Object reflectObject, int requestCode) {

        /**
         * 获取当前class中的methods
         */
        Method[] methods = reflectObject.getClass().getDeclaredMethods();

        /**
         * 遍历所有的method
         */
        for (Method method :
                methods) {
            /**
             * 获取当前的method是否有打标记的注解
             */
            PermissionFail failMetod = method.getAnnotation(PermissionFail.class);
            /**
             * 如果method包含有PermissionSucceed注解
             */
            if (failMetod != null) {
                /**
                 * 获取注解中的requestCode是否和调用处给code一直就执行注解处的方法
                 */
                int methodCode = failMetod.requestCode();
                /**
                 * 请求码一致
                 */
                if (methodCode == requestCode) {

                    excuteMethod(reflectObject, method);
                }
            }
        }
    }

}
