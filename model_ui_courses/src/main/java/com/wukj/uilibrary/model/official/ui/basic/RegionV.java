package com.wukj.uilibrary.model.official.ui.basic;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;


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

public class RegionV extends View implements View.OnClickListener {


    public RegionV(Context context) {
        super(context);
    }

    public RegionV(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
    }

    private Paint mPaint;
    private int centerX;
    private int cY;
    private int progress;
    private boolean isEnd = false;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.parseColor("#FFFACD"));

        centerX = getMeasuredWidth() / 2;
        cY = getMeasuredHeight() / 2;

        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#FF6A6A"));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10);

        drawRegion(canvas, getRegion(), mPaint);

        Region region = getCenterRect();
        region.op(getLeftSemiCircle(), Region.Op.UNION);
        region.op(getRightSemiCircle(), Region.Op.UNION);
        drawRegion(canvas, region, mPaint);

        drawRegion(canvas, getShade(progress), mPaint);

    }

    private Region getRegion() {
        return new Region(new Rect(centerX / 2, cY / 4, centerX + centerX / 2, cY / 2));
    }

    private Region getCenterRect() {
        if (isEnd){
            mPaint.setColor(Color.parseColor("#A52A2A"));
        } else {
            mPaint.setColor(Color.parseColor("#7CCD7C"));
        }
        return new Region(centerX / 4, cY, centerX + centerX * 3 / 4, cY + centerX / 2);
    }

    private Region getLeftSemiCircle() {
        Path ovalPath = new Path();
        ovalPath.addOval(new RectF(0, cY, centerX / 2, cY + centerX / 2), Path.Direction.CCW);
        Region region = new Region();
        region.setPath(ovalPath, new Region(0, cY, centerX / 4, (cY + centerX / 2)));
        return region;
    }

    private Region getRightSemiCircle() {
        Path ovalPath = new Path();
        ovalPath.addOval(new RectF(centerX + centerX / 2, cY, centerX * 2, cY + centerX / 2), Path.Direction.CCW);
        Region region = new Region();
        region.setPath(ovalPath, new Region(centerX + centerX * 3 / 4, cY, centerX * 2, (cY + centerX / 2)));
        return region;
    }


    private void drawRegion(Canvas canvas, Region region, Paint paint) {
        RegionIterator iterator = new RegionIterator(region);
        Rect rect = new Rect();
        while (iterator.next(rect)) {
            canvas.drawRect(rect, paint);
        }
    }

    private Region getShade(int x) {
        mPaint.setColor(Color.parseColor("#60FFFACD"));
        return new Region(x, cY, centerX * 2, cY + centerX / 2);
    }

    private ValueAnimator mValueAnimator;

    /**
     * 利用动画刷新进度条
     */
    private void initLoadingAnimation(int width) {
        mValueAnimator = ValueAnimator.ofInt(0, width);
        mValueAnimator.setDuration(5005);
        mValueAnimator.setStartDelay(50);
        mValueAnimator.setRepeatMode(ValueAnimator.RESTART);
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                progress = (int) valueAnimator.getAnimatedValue();
                if (progress == getMeasuredWidth()) {
                    isEnd = true;
                } else {
                    isEnd = false;
                }
                invalidate();

            }

        });
        mValueAnimator.start();
    }

    @Override
    public void onClick(View v) {

        initLoadingAnimation(getMeasuredWidth());

    }
}
