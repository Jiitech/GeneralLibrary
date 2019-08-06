package com.wukj.ndk.grammar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    public String name = "Test：";

    static {
        System.loadLibrary("native-lib");
        init();
    }

    private TextView mText;
    private int[] source = {1, 4, 0, 7, 33, 11};
    private Stu stu = new Stu();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mText = this.findViewById(R.id.tv);

        String strFromC = getStringFromNDK();
        StringBuffer buffer = new StringBuffer();
        buffer.append("1.--------------------------");
        buffer.append("\n");
        buffer.append(strFromC);
        buffer.append("\n");


        String javaStrFromC = getJavaNameFromC();
        buffer.append("2.--------------------------");
        buffer.append("\n");
        buffer.append(javaStrFromC);
        buffer.append("\n");

        String javaMethod = getJavaMethod();
        buffer.append("3.--------------------------");
        buffer.append("\n");
        buffer.append(javaMethod);
        buffer.append("\n");

        buffer.append("4.--------------------------");
        buffer.append("\n");
        getArray(source);
        for (int i = 0; i < source.length; i++) {
            buffer.append(source[i]);
            buffer.append("，");
        }
        buffer.append("\n");


        getLocalReference();
        buffer.append("5.--------------------------");
        buffer.append("\n");

        try {
            handleException();
        } catch (Exception e) {
            Log.e("exception", e.getMessage());

        }

        //缓存策略问题
        for (int i = 0; i < 10; i++) {
            cached();
        }

        mText.setText(buffer.toString());


    }

    public String getName() {
        return "Test：";
    }


    /**
     * java调用native方法获取C++中的字符串
     *
     * @return
     */
    public native String getStringFromNDK();

    /**
     * jni访问java属性
     * java调用native方法，然后通过jni代码调用java中的属性，然后返回给java
     * java->jni->java attrs>java
     *
     * @return
     */
    public native String getJavaNameFromC();

    /**
     * jni访问java方法
     *
     * @return
     */
    public native String getJavaMethod();

    /**
     * 通过jni处理java中的数组数据
     *
     * @return
     */
    public native void getArray(int[] arrays);

    /**
     * 引用解决的问题是通知JVM，什么时候回收JNI对象
     */
    public native void getLocalReference();

    /**
     * 处理异常，java层面是无法处理jni层面的异常的
     */
    public native void handleException();

    /**
     * 缓存策略-生命周期
     */
    public native void cached();

    public static native void init();


}
