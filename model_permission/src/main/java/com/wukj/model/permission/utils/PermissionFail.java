package com.wukj.model.permission.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * CopyRight: wkj
 * Time：2017/2/6.14:17
 * Author: wkj
 * QQ:534098845
 * Descirption:
 */
@Target(ElementType.METHOD)//这个注解放在方法使用
@Retention(RetentionPolicy.RUNTIME)//在运行时起作用
public @interface PermissionFail {
    public int requestCode();//请求码
}
