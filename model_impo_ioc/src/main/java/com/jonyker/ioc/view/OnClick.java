package com.jonyker.ioc.view;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * CopyRight: wkj
 * Time：2017/2/4.16:46
 * Author: wkj
 * QQ:534098845
 * Descirption: 打一个点击事件的标志
 */
@Target(ElementType.METHOD) //作用在方法上的
@Retention(RetentionPolicy.RUNTIME) //场景是运行时
public @interface OnClick {
    int[] value();// 可以传递多个参数
}
