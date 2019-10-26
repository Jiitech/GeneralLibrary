package com.wukj.uilibrary.model.official.ui.sfv;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.wukj.uilibrary.model.library.LogUtils;

/**
 * 项目名称：UILibrary
 * 创建时间：2018/11/7 0007 下午 5:37
 * 作者：Jonyker
 * 博客：https://www.jianshu.com/u/07642698e7f4
 * github：https://github.com/Jonyker
 * 修改人：Jonyker
 * 联系方式：QQ/534098845
 * 修改时间：2018/11/7 0007 下午 5:37
 * 备注：
 * 版本：V.1.0
 * 描述：
 * 1.
 * 2.
 * 3.
 */
public class CSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder mHolder;              // 用于控制SurfaceView
    private Thread t;                           // 声明一条线程
    private boolean flag;                       // 线程运行的标识，用于控制线程
    private Canvas mCanvas;                     // 声明一张画布
    private Paint p;                            // 声明一支画笔
    private int x = 50, y = 50, r = 300;        // 圆的坐标和半径

    public CSurfaceView(Context context) {
        super(context);

        mHolder = getHolder(); // 获得SurfaceHolder对象
        mHolder.addCallback(this); // 为SurfaceView添加状态监听
        p = new Paint(); // 创建一个画笔对象
        p.setColor(Color.BLUE); // 设置画笔的颜色为白色
        setFocusable(true); // 设置焦点

        LogUtils.d(this.getClass(),"构造方法：CSurfaceView(Context context)");

    }

    public CSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mHolder = getHolder(); // 获得SurfaceHolder对象
        mHolder.addCallback(this); // 为SurfaceView添加状态监听
        p = new Paint(); // 创建一个画笔对象
        p.setColor(Color.BLUE); // 设置画笔的颜色为白色
        setFocusable(true); // 设置焦点

        LogUtils.d(this.getClass(),"构造方法：CSurfaceView(Context context, AttributeSet attrs)");

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        LogUtils.d(this.getClass(),"-----w:"+w);
        LogUtils.d(this.getClass(),"-----h:"+h);
        LogUtils.d(this.getClass(),"-----oldw:"+oldw);
        LogUtils.d(this.getClass(),"-----oldh:"+oldh);

    }

    /**
     * 自定义一个方法，在画布上画一个圆
     */
    public void doDraw() {
        mCanvas = mHolder.lockCanvas(); // 获得画布对象，开始对画布画画
        mCanvas.drawRGB(0, 0, 0); // 把画布填充为黑色
        mCanvas.drawCircle(x, y, r, p); // 画一个圆
        mHolder.unlockCanvasAndPost(mCanvas); // 完成画画，把画布显示在屏幕上
    }

    /**
     * 当SurfaceView创建的时候，调用此函数
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        t = new Thread(this); // 创建一个线程对象
        flag = true; // 把线程运行的标识设置成true
        t.start(); // 启动线程
    }

    /**
     * 当SurfaceView的视图发生改变的时候，调用此函数
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        LogUtils.d(this.getClass(),"-----width:"+width);
        LogUtils.d(this.getClass(),"----height:"+height);
    }

    /**
     * 当SurfaceView销毁的时候，调用此函数
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        flag = false; // 把线程运行的标识设置成false
    }

    /**
     * 当屏幕被触摸时调用
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = (int) event.getX(); // 获得屏幕被触摸时对应的X轴坐标
        y = (int) event.getY(); // 获得屏幕被触摸时对应的Y轴坐标
        return true;
    }

    /**
     * 当用户按键时调用
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {  //当用户点击↑键时
            y--;  //设置Y轴坐标减1
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void run() {
        while (flag) {
            doDraw(); // 调用自定义画画方法
            try {
                Thread.sleep(50); // 让线程休息50毫秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
