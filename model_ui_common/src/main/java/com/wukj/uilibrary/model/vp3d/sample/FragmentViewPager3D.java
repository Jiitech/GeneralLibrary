package com.wukj.uilibrary.model.vp3d.sample;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.wukj.general.common.fragment.SupFragment;
import com.wukj.uilibrary.model.tagcloud.R;
import com.wukj.uilibrary.model.tagcloud.sample.TextTagsAdapter;
import com.wukj.uilibrary.model.vp3d.MyTransformation;

/**
 * 项目名称：AndroidGeneralLibrary
 * 创建时间：2019-09-19 17:39
 * 作者：Jonyker
 * 博客：https://www.jianshu.com/u/07642698e7f4
 * github：https://github.com/Jonyker
 * 修改人：Jonyker
 * 联系方式：QQ/534098845
 * 修改时间：2019-09-19 17:39
 * 备注：
 * 版本：V.1.0
 * 描述：
 * 1.
 * 2.
 * 3.
 */
public class FragmentViewPager3D extends SupFragment {



    //这里的图片从百度图片中下载，图片规格是960*640
    private static final int[] drawableIds = new int[]{
            R.mipmap.bg_01,
            R.mipmap.bg_02,
            R.mipmap.bg_03,
            R.mipmap.bg_04,
            R.mipmap.bg_05};
    private ViewPager mViewPager;
    private RelativeLayout mRelativeLayout;
    private MyPagerAdapter mPagerAdapter;


    private void initViews() {

    }


    @Override
    protected int getCreateVID() {
        return R.layout.fragment_vp3d;
    }

    @Override
    protected void onInitV(@NonNull View rootView, @Nullable Bundle savedInstanceState) {
        mViewPager = rootView.findViewById(R.id.viewpager);
        mPagerAdapter = new MyPagerAdapter(drawableIds,getActivity());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setPageTransformer(true,new MyTransformation());
    }




    class MyPagerAdapter extends PagerAdapter {

        private int[] mBitmapIds;
        private Context mContext;

        public MyPagerAdapter(int[] data,Context context){
            mBitmapIds = data;
            mContext = context;
        }

        @Override
        public int getCount() {
            return mBitmapIds.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_vp3d_item,container,false);
            ImageView imageView = view.findViewById(R.id.iv);
            imageView.setImageResource(mBitmapIds[position]);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}
