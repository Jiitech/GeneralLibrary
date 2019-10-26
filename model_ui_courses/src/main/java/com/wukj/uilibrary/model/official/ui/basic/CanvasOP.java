package com.wukj.uilibrary.model.official.ui.basic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 项目名称：UILibrary
 * 创建时间：2018/11/10 下午7:24
 * 作者：Jonyker
 * 博客：https://www.jianshu.com/u/07642698e7f4
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
public class CanvasOP extends View{


    private Paint mPaint;

    public CanvasOP(Context context) {
        super(context);
    }

    public CanvasOP(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int centerX = getMeasuredWidth()/2;
        int centerY = getMeasuredHeight()/2;

        mPaint = new Paint();

        canvas.drawColor(Color.parseColor("#CD0000"));

        canvas.save();

        canvas.translate(100,100);

        mPaint.setColor(Color.parseColor("#70CAE1FF"));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10);
        canvas.drawRect(new RectF(0,0,200,200),mPaint);

        canvas.restore();



    }









}
