package com.wukj.uilibrary.model.favour;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.wukj.uilibrary.model.library.LogUtils;

import java.util.Random;

/**
 * 项目名称：UILibrary
 * 创建时间：2018/11/7 0007 下午 2:45
 * 作者：Jonyker
 * 博客：http://www.udevtech.com
 * github：https://github.com/Jonyker
 * 修改人：Jonyker
 * 联系方式：QQ/534098845
 * 修改时间：2018/11/7 0007 下午 2:45
 * 备注：
 * 版本：V.1.0
 * 描述：直播节目的点赞效果
 * 1.
 * 2.
 * 3.
 */
public class LikeView extends SurfaceView implements SurfaceHolder.Callback {


    private int mWidth;
    private int mHeight;
    private int mBombX; // 旋转的中心X
    private int mBombY; // 旋转的中心Y
    private Paint mPaint;
    private DrawTask mDrawTask; // 绘制UI的线程
    private FuseView mFuseView; // 引导的view
    private Bitmap[] mDrawables;
    private int[] mDrawableResIDs;
    private SurfaceHolder mHolder;
    private Random mRandom = new Random(); // 用于产生随机数
    private static final int MSG_DRAW_BUBBLE = 10;
    private static HandlerThread mHandlerThread = new HandlerThread("BombView");

    private int dx = 0; // 引导view在坐抛物线运动时在x轴的增量
    private boolean mIsDismiss = false; //是否处于消失阶段


    static {
        mHandlerThread.start();
    }

    private Handler.Callback mCallback = new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_DRAW_BUBBLE:
                    mHandler.removeMessages(MSG_DRAW_BUBBLE);
                    mHandler.post(mDrawTask);
                    mHandler.sendEmptyMessageDelayed(MSG_DRAW_BUBBLE, 20);
                    LogUtils.d(this.getClass(),MSG_DRAW_BUBBLE+"");
                    break;
            }
            return true;
        }
    };
    private Handler mHandler = new WeakRefHandler(mCallback, mHandlerThread.getLooper());



    public LikeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setKeepScreenOn(true);
        mHolder.setFormat(PixelFormat.TRANSPARENT);
        mPaint = new Paint();
        mPaint.setColor(Color.YELLOW);

        mDrawableResIDs = new int[]{
                R.mipmap.ic_praise_eight,
                R.mipmap.ic_praise_one,
                R.mipmap.ic_praise_third,
                R.mipmap.ic_praise_two,
                R.mipmap.ic_praise_five,
                R.mipmap.ic_praise_four,
                R.mipmap.ic_praise_seven,
                R.mipmap.ic_praise_six,
        };
        mDrawables = new Bitmap[mDrawableResIDs.length];


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (canvas == null) {
            return;
        }
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);



    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (mDrawTask == null) {
            LogUtils.d(this.getClass(),"------创建绘制线程");
            mDrawTask = new DrawTask(holder, this);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        this.mWidth = width;
        this.mHeight = height;

        mBombX = mWidth / 2;
        mBombY = mWidth / 2;
        LogUtils.d(this.getClass(),"------mWidth："+mWidth);
        LogUtils.d(this.getClass(),"------mHeight："+mHeight);

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }


    /**
     * 绘制UI的线程，只要是调用PraiseView.onDraw(canvas);
     * 并且做了锁保护（固定用法，不要轻易修改）
     */
    class DrawTask implements Runnable {

        private SurfaceHolder holder;
        private LikeView bombView;

        public DrawTask(SurfaceHolder holder, LikeView bombView) {
            this.bombView = bombView;
            this.holder = holder;
        }

        @SuppressLint("WrongCall")
        @Override
        public void run() {
            Canvas canvas = null;
            try {
                canvas = holder.lockCanvas();
                synchronized (holder) {
                    bombView.onDraw(canvas);
                }
            } finally {
                if (canvas != null) {
                    try { // 修复umeng崩溃
                        holder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                    }
                }
            }
        }
    }
    /**
     * 随机获取一个bitmap
     *
     * @return
     */
    private Bitmap getRandBitmap() {
        int n = mRandom.nextInt(mDrawableResIDs.length);
        Bitmap bitmap = mDrawables[n];
        if (bitmap == null || bitmap.isRecycled()) {
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inMutable = true;
            bitmap = BitmapFactory.decodeResource(getResources(), mDrawableResIDs[n], opts);
            mDrawables[n] = bitmap;
        }
        return bitmap;
    }

    /**
     * 开始绘制爆炸彩蛋，如果上一个效果还没结束，则不处理新的
     */
    public void startBomb() {
        if (mFuseView != null) {
            return;
        }
        initFuseView();
        setVisibility(View.VISIBLE);
        // generateBubble(MAX_BUBBLE_COUNT);
        dx = 0;
        mIsDismiss = false;
//        mHandler.sendEmptyMessage(MSG_DRAW_BUBBLE);
    }


    /**
     * 初始化引导
     */
    private void initFuseView() {
        mFuseView = new FuseView();
        mFuseView.x = mWidth * 7 / 8;
        mFuseView.y = mHeight * 17 / 20;
        mFuseView.bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_praise_five);
    }

    class Bubble {
        public Bitmap bitmap;

        public float scale = 1.0f; // 缩放
        public float top = 0f; // 偏移
        public float left = 0f; // 偏移
        public int rotate = 0; // 旋转
        public int alpha = 255; // 透明度

        public Bubble() {
            this.bitmap = getRandBitmap();
        }
    }

    class FuseView {
        public Bitmap bitmap;
        public float scale;
        public int alpha;
        public int x;
        public int y;
    }

    private void dismiss() {
        mHandler.removeCallbacksAndMessages(null);
        post(new Runnable() {
            @Override
            public void run() {
                setVisibility(View.GONE);
            }
        });
        mFuseView = null;
        for (Bitmap bitmap : mDrawables) {
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
            }
        }
    }

    /**
     * 释放资源
     */
    public void release() {
        //mBubbles.clear();
        dismiss();
    }


}
