package com.wukj.uilibrary.model.official.ui.basic;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.wukj.uilibrary.model.library.LogUtils;
import com.wukj.uilibrary.model.official.ui.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


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
 *  
 */

public class ChartV extends View implements View.OnClickListener {

    public ChartV(Context context) {
        this(context, null);
    }

    public ChartV(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private int mWidth;
    private int mHeight;
    private int mPaddingBoth = 100;

    private Paint mLinePaint;
    private Paint mScalePaint;
    private Paint mPointPaint;

    private void init(Context context) {
        mLinePaint = new Paint();
        mLinePaint.setStyle(Paint.Style.FILL);
        mLinePaint.setStrokeWidth(2);
        mLinePaint.setColor(Color.parseColor("#50E5E5E5"));

        mScalePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mScalePaint.setStyle(Paint.Style.FILL);
        mScalePaint.setStrokeWidth(3);

        mPointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPointPaint.setStyle(Paint.Style.FILL);
        mPointPaint.setStrokeWidth(3);


        setOnClickListener(this);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        drawLines(canvas);

        drawScale(canvas);

        drawPoints(canvas);

    }

    /**
     * Y轴刻度
     *
     * @param canvas
     */
    private void drawScale(Canvas canvas) {
        lineHeight = mHeight / 2;
        int scaleCount = 7;
        int scaleSpace = lineHeight / scaleCount;
        int lineTopY = mHeight / 4;
        for (int i = 0; i < scaleCount; i++) {
            String scale = (scaleCount - i) + "百亿";
            int scaleY = lineTopY + scaleSpace / 2 + scaleSpace * i;
            int rectLeft = mPaddingBoth / 6;
            int rectTop = scaleY - 30;
            int rectRight = mPaddingBoth;
            int rectBottom = scaleY + 30;
            Rect targetRect = new Rect(rectLeft, rectTop, rectRight, rectBottom);
            mScalePaint.setColor(Color.TRANSPARENT);
            canvas.drawRect(targetRect, mScalePaint);

            mScalePaint.setTextSize(24);
            mScalePaint.setColor(Color.parseColor("#50E5E5E5"));
            Paint.FontMetricsInt fontMetrics = mScalePaint.getFontMetricsInt();
            int baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
            // 下面这行是实现水平居中，drawText对应改为传入targetRect.centerX()
            mScalePaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(scale, targetRect.centerX(), baseline, mScalePaint);

        }
    }

    /**
     * Y轴
     *
     * @param canvas
     */
    private void drawLines(Canvas canvas) {
        String times[] = new String[]{"00:00", "04:00", "08:00", "12:00", "16:00", "20:00", "24:00"};
        int lineCount = 7;
        int lineSpace = (mWidth - mPaddingBoth * 2) / (lineCount - 1);
        int lineHeight = mHeight / 2;
        int lineTopY = mHeight / 4;
        int lineBottomY = lineHeight + lineTopY;
        for (int i = 0; i < lineCount; i++) {
            int lineX = mPaddingBoth + lineSpace * i;
            canvas.drawLine(lineX, lineTopY, lineX, lineBottomY, mLinePaint);

            int rectLeft = lineX - 35;
            int rectTop = lineBottomY;
            int rectRight = lineX + 35;
            int rectBottom = lineBottomY + 45;
            Rect targetRect = new Rect(rectLeft, rectTop, rectRight, rectBottom);
            mLinePaint.setColor(Color.TRANSPARENT);
            canvas.drawRect(targetRect, mLinePaint);

            mLinePaint.setTextSize(24);
            mLinePaint.setColor(Color.parseColor("#50E5E5E5"));
            Paint.FontMetricsInt fontMetrics = mLinePaint.getFontMetricsInt();
            int baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
            // 下面这行是实现水平居中，drawText对应改为传入targetRect.centerX()
            mLinePaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(times[i], targetRect.centerX(), baseline, mLinePaint);
        }
    }


    private void drawPoints(Canvas canvas) {
        lineHeight = mHeight / 2;
        lineTopY = mHeight / 4;
        lineBottomY = lineHeight + lineTopY;
        int pCount = mLinePoints.size();
        Path mPath = new Path();
        for (int j = 0; j < pCount; j++) {
            Point startp = mLinePoints.get(j);
            Point endp;
            if (j != pCount - 1) {
                endp = mLinePoints.get(j + 1);
                int wt = (startp.x + endp.x) / 2;
                Point ps = new Point();
                Point pe = new Point();
                ps.y = startp.y;
                ps.x = wt;
                pe.y = endp.y;
                pe.x = wt;
                if (j == 0) {
                    mPath.moveTo(startp.x, startp.y);
                }
                /**
                 * 添加一个立方贝塞尔曲线的最后一点,接近控制点(x1,y1)和(x2,y2),到最后(x3,y3);
                 * 如果没有移至()调用为这个轮廓,第一点是自动设置为(0,0);
                 *
                 * @param x1的坐标1立方曲线控制点
                 * @param y1第一控制点的坐标一立方曲线
                 * @param x2上第二个控制点的坐标一立方曲线
                 * @param y2第二控制点的坐标一立方曲线
                 * @param x3的坐标三次曲线的终点
                 * @param y3终点坐标的三次曲线
                 *
                 */
                mPath.cubicTo(ps.x, ps.y, pe.x, pe.y, endp.x, endp.y);
            } else {
                /**连接到终点x,底部y*/
                mPath.lineTo(startp.x, lineBottomY);
                /**连接到起点x,底部y*/
                mPath.lineTo(mLinePoints.get(0).x, lineBottomY);
                /**连接到起点x,起点y*/
                mPath.lineTo(mLinePoints.get(0).x, mLinePoints.get(0).y);
            }
        }
        int save = canvas.save();
        //将画布切割成path的形状
        canvas.clipPath(mPath);

        Drawable drawable = getFillDrawable();
        drawable.setBounds(mPaddingBoth, minY, (mWidth - mPaddingBoth), lineBottomY);
        drawable.draw(canvas);
        canvas.restoreToCount(save);

    }


    private int minY = mHeight;
    private ValueAnimator mValueAnimator;
    private List<Point> mLinePoints = new ArrayList<>();
    private int lineHeight;
    private int lineTopY;
    private int lineBottomY;
    private int destinationX;
    private int scaleX;
    private boolean isDrawing = false;

    /**
     * 利用动画刷新进度条
     */
    private void initLoadingAnimation() {
        lineHeight = mHeight / 2;
        lineTopY = mHeight / 4;
        lineBottomY = lineHeight + lineTopY;
        destinationX = mWidth - mPaddingBoth;
        scaleX = (mWidth - mPaddingBoth * 2) / 12;
        mValueAnimator = ValueAnimator.ofInt(mPaddingBoth, destinationX);
        mValueAnimator.setDuration(3000);
        mValueAnimator.setStartDelay(50);
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int progress = (int) valueAnimator.getAnimatedValue();
                LogUtils.d(this.getClass(), "progress:" + progress + ",destinationX:" + destinationX);
                int index = (progress - mPaddingBoth)/scaleX;
                if ((mLinePoints.size() -1) !=index) {
                    int x = progress;
                    int dis = (int) (Math.random() * lineHeight);
                    if (dis > lineHeight / 2) {
                        dis = dis / 2;
                    }
                    int y = lineHeight - dis;
                    if (y < minY) {
                        minY = y;
                    }
                    mLinePoints.add(new Point(x, y));
                    invalidate();
                }
                if(destinationX == progress){
                    isDrawing = false;
                }
            }
        });
        mValueAnimator.start();
        isDrawing = true;
    }

    @Override
    public void onClick(View v) {

        if(!isDrawing){
            mLinePoints.clear();
            initLoadingAnimation();
        }

    }


    private Drawable getFillDrawable() {
        return getResources().getDrawable(R.drawable.shape_shadow);
    }


}
