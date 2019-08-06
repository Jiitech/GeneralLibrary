package com.wukj.uilibrary.model.favour;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wukj.uilibrary.model.favour.leonids.ParticleSystem;

/**
 * 项目名称：UILibrary
 * 创建时间：2018/10/30 0030 下午 2:59
 * 作者：Jonyker
 * 博客：https://www.jianshu.com/u/07642698e7f4
 * github：https://github.com/Jonyker
 * 修改人：Jonyker
 * 联系方式：QQ/534098845
 * 修改时间：2018/10/30 0030 下午 2:59
 * 备注：
 * 版本：V.1.0
 * 描述：模仿今日头条的点赞效果
 * 1.
 * 2.
 * 3.
 */
public class FavourView extends LinearLayout implements View.OnClickListener {

    private int[] iconInts = new int[]{R.mipmap.af0,
            R.mipmap.af1,
            R.mipmap.af2,
            R.mipmap.af3,
            R.mipmap.af4,
            R.mipmap.af5,
            R.mipmap.af6,
            R.mipmap.af7,
            R.mipmap.af8,
            R.mipmap.af9};
    private View ivLike;
    private TextView txNumber;
    private boolean animaterRunning = false;
    private Long jetDuration = 800L;
    private boolean isLike = false;                 //是否点赞
    private int likeNumber = 0;                     //点赞数
    private int clickNum = 0;                       //点击次数

    /**
     * 图片大小改变动画
     */
    private AnimatorSet ivSizeSet;
    private Long sizeDuration = 200L;

    public FavourView(Context context) {
        super(context);
        init(context,null);
    }

    public FavourView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public FavourView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.icon_favour, this, true);
        ivLike = view.findViewById(R.id.iv_like);
        txNumber = view.findViewById(R.id.tx_number);
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (isLike) {
            //已点赞,取消点赞或者开始喷射动画
            if (animaterRunning) {
                //动画未结束，开始喷射动画、点赞动画
                startJetAnimator();
                startLikedAnimator();
            } else {
                //取消点赞
                cacelLiked();
            }
        } else {
            //未点赞，开始点赞
            startLiked();
            //开始喷射动画
            startJetAnimator();
        }
    }

    /**
     * 开启喷射动画
     */
    private void startJetAnimator() {
        animaterRunning = true;
        ParticleSystem ps = new ParticleSystem((Activity) getContext(), 100, iconInts, jetDuration);
        ps.setScaleRange(0.7f, 1.3f);
        ps.setSpeedModuleAndAngleRange(0.1f, 0.5f, 180, 360);
        ps.setAcceleration(0.0001f, 90);
        ps.setRotationSpeedRange(90, 180);
        ps.setFadeOut(200, new AccelerateInterpolator());
        ps.oneShot(this, 10, new DecelerateInterpolator());
        clickNum++;
        handler.sendEmptyMessageDelayed(clickNum, jetDuration);
    }

    /**
     * 设置初始状态
     *
     * @param isLike
     * @param likeNumber
     */
    public void setLike(boolean isLike, int likeNumber) {
        this.isLike = isLike;
        this.likeNumber = likeNumber;
        txNumber.setText(String.valueOf(likeNumber));
        if (isLike) {
            ivLike.setBackgroundResource(R.mipmap.icon_like_pressed);
            txNumber.setTextColor(ContextCompat.getColor(getContext(), R.color.color_like_pressed));
        } else {
            ivLike.setBackgroundResource(R.mipmap.icon_like_normal);
            txNumber.setTextColor(ContextCompat.getColor(getContext(), R.color.color_like_normal));
        }
    }
    /**
     * 点赞动画
     */
    private void startLikedAnimator() {
        if (ivSizeSet == null) {
            ObjectAnimator animatorX = ObjectAnimator.ofFloat(ivLike, "scaleX", 1f, 1.2f, 1f);
            ObjectAnimator animatorY = ObjectAnimator.ofFloat(ivLike, "scaleY", 1f, 1.2f, 1f);
            ivSizeSet = new AnimatorSet();
            ivSizeSet.playTogether(animatorX, animatorY);
            ivSizeSet.setDuration(sizeDuration);
            ivSizeSet.setInterpolator(new LinearInterpolator());
        }
        if (!ivSizeSet.isRunning()) {
            ivSizeSet.start();
        }
    }

    /**
     * 点赞
     */
    private void startLiked() {
        ++likeNumber;
        setLike(true, likeNumber);
    }

    /**
     * 取消点赞
     */
    private void cacelLiked() {
        --likeNumber;
        setLike(false, likeNumber);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == clickNum) {
                animaterRunning = false;
                clickNum = 0;
            }
        }
    };

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (clickNum > 0) {
            for (int i = clickNum; i > 0; i--) {
                handler.removeMessages(i);
            }
        }
    }

}
