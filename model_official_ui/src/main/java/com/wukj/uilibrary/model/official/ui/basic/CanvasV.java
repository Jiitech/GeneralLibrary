package com.wukj.uilibrary.model.official.ui.basic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 项目名称：UILibrary
 * 创建时间：2018/11/10 下午7:24
 * 作者：Jonyker
 * 博客：http://www.udevtech.com
 * github：https://github.com/Jonyker
 * 修改人：Jonyker
 * 联系方式：QQ/534098845
 * 修改时间：2018/11/10 下午7:24
 * 备注：
 * 版本：V.1.0
 * 描述：
 * 1.
 * 2.
 * 3.
 */
public class CanvasV extends View{


    private Paint mPaint;

    public CanvasV(Context context) {
        super(context);
    }

    public CanvasV(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int centerX = getMeasuredWidth()/2;
        int centerY = getMeasuredHeight()/2;

        mPaint = new Paint();

        canvas.drawARGB(100,200,50,90);

        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(20);
        canvas.drawLine(100,100,200,200,mPaint);

        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(20);
        canvas.drawLine(200,200,300,300,mPaint);

        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(20);
        canvas.drawPoint(centerX,centerY,mPaint);


        mPaint.setColor(Color.parseColor("#00b38a"));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(20);
        Rect rect = new Rect(centerX/2,centerY+centerY/6,centerX+centerX/2,centerY+centerY/2);
        canvas.drawRect(rect,mPaint);




    }









}
