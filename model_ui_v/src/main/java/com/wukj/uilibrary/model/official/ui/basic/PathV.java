package com.wukj.uilibrary.model.official.ui.basic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
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
public class PathV extends View {


    private Paint mPaint;

    public PathV(Context context) {
        super(context);
    }

    public PathV(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int centerX = getMeasuredWidth() / 2;
        int cY = getMeasuredHeight() / 2;

        mPaint = new Paint();

        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        Path path = new Path();
        path.moveTo(centerX, cY / 3);
        path.lineTo(centerX + centerX / 2, cY);
        path.lineTo(centerX / 2, cY);
        path.close();
        RectF rectF = new RectF(centerX / 2, cY, centerX + centerX / 2, cY + centerX / 2);
        path.arcTo(rectF, 0, 300,true);
        canvas.drawPath(path, mPaint);



    }


}
