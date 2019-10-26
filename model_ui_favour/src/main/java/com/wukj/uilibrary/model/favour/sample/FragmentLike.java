package com.wukj.uilibrary.model.favour.sample;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.wukj.uilibrary.model.favour.LikeView;
import com.wukj.uilibrary.model.favour.R;
import com.wukj.uilibrary.model.library.LogUtils;
import com.wukj.uilibrary.model.library.ScreenUtils;
import com.wukj.uilibrary.model.library.sup.SupFragment;

/**
 * 项目名称：UILibrary
 * 创建时间：2018/10/30 0030 下午 3:18
 * 作者：Jonyker
 * 博客：https://www.jianshu.com/u/07642698e7f4
 * github：https://github.com/Jonyker
 * 修改人：Jonyker
 * 联系方式：QQ/534098845
 * 修改时间：2018/10/30 0030 下午 3:18
 * 备注：
 * 版本：V.1.0
 * 描述：
 * 1.
 * 2.
 * 3.
 */
public class FragmentLike extends SupFragment {



    private LikeView mLikeView;
    private Handler mHandler = new Handler();
    private Runnable task = new Runnable() {
        @Override
        public void run() {
            mLikeView.startBomb();
            mHandler.postDelayed(task, 10000);
        }
    };
    @Override
    protected int getCreateVID() {
        return R.layout.fragment_like;
    }

    @Override
    protected void onInitV(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mLikeView = view.findViewById(R.id.likev);
        mHandler.postDelayed(task, 10000);

        LogUtils.d(this.getClass(),"------屏宽："+ ScreenUtils.getScreenWidth(getActivity()));
        LogUtils.d(this.getClass(),"------屏高："+ ScreenUtils.getScreenHeight(getActivity()));

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLikeView.release();
    }


}
