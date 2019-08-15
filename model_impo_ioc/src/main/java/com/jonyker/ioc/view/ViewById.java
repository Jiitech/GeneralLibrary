package com.jonyker.ioc.view;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * CopyRight: wkj
 * Time：2017/2/4.16:34
 * Author: wkj
 * QQ:534098845
 * Descirption: 在View上打一个标志
 */

@Target(ElementType.FIELD) //表示使用的范围，这个是使用在属性上面的
@Retention(RetentionPolicy.RUNTIME) //表示使用的场景，是运行的时候
public @interface ViewById {
    int value();//代表这个注解是可以传递值
}
