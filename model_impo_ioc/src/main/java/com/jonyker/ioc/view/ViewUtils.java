package com.jonyker.ioc.view;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import com.jonyker.ioc.NetManagerUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * CopyRight: wkj
 * Time：2017/2/4.16:48
 * Author: wkj
 * QQ:534098845
 * Descirption:
 */
public class ViewUtils {
    /**
     * 绑定View控件和事件
     */
    public static void bondInit(Activity activity) {

        findViewById(activity);

        onClickListener(activity);
    }

    /**
     * 通过注解设置监听事件
     *
     * @param activity
     */
    private static void onClickListener(final Activity activity) {
        Class<?> clazz = activity.getClass();

        Method[] Methods = clazz.getDeclaredMethods();

        for (final Method method :
                Methods) {
            OnClick onClick = method.getAnnotation(OnClick.class);

            if (onClick != null) {

                //获取注解身上的Value
                int[] viewIds = onClick.value();

                final boolean idCheckNet = method.getAnnotations() != null;

                for (int id :
                        viewIds) {
                    //得到View组件的对象
                    View view = activity.findViewById(id);
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (idCheckNet) {

                                if (!NetManagerUtil.cureentNetIsAvailable(activity)) {
                                    Toast.makeText(activity, "当前无网络", Toast.LENGTH_LONG).show();
                                    return;
                                }
                                try {
                                    //同意使用使用属性
                                    method.setAccessible(true);
                                    //调用方法
                                    method.invoke(activity);
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                } catch (InvocationTargetException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                }
            }
        }
    }


    /**
     * 通过反射拿到id，然后将对象赋值到View组件上面去
     *
     * @param activity
     */
    public static void findViewById(Activity activity) {
        Class<?> clazz = activity.getClass();

        Field[] fields = clazz.getDeclaredFields();

        for (Field field :
                fields) {
            ViewById viewById = field.getAnnotation(ViewById.class);
            if (viewById != null) {

                //获取注解身上的Value
                int viewId = viewById.value();
                //得到View组件的对象
                View view = activity.findViewById(viewId);

                try {
                    //同意使用使用属性
                    field.setAccessible(true);
                    //将对象放在View组件上面
                    field.set(activity, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
