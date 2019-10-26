package com.wukj.general.library.fragment;


import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.wukj.general.common.fragment.SupFragment;
import com.wukj.general.library.R;
import com.wukj.general.library.fragment.sample.VCategoryFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * 项目名称：UILibrary
 * 创建时间：2018/11/17 上午2:50
 * 作者：Jonyker
 * 博客：https://www.jianshu.com/u/07642698e7f4
 * github：https://github.com/Jonyker
 * 修改人：Jonyker
 * 联系方式：QQ/534098845
 * 修改时间：2018/11/17 上午2:50
 * 备注：
 * 版本：V.1.0
 * 描述：
 * 1.
 * 2.
 * 3.
 */

public class CategoryFragment extends SupFragment {

    private TabLayout mTabLayout;
    private ViewPager mVP;
    private String[] titles = {"G技巧", "UI控件", "G网络", "G基类", "G工具", "G高级", "G库"};

    private PAdapter mPAdapter;

    @Override
    protected int getCreateVID() {
        return R.layout.fragment_category;
    }

    @Override
    protected void onInitV(@NonNull View view, @Nullable Bundle savedInstanceState) {

        mTabLayout = view.findViewById(R.id.category_tab);
        mVP = view.findViewById(R.id.category_vp);

        mPAdapter = new PAdapter(getChildFragmentManager());
        mVP.setAdapter(mPAdapter);
        mTabLayout.setupWithViewPager(mVP);

    }

    class PAdapter extends FragmentPagerAdapter {

        public PAdapter(FragmentManager fm) {
            super(fm);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            StringBuffer path = new StringBuffer();
            path.append("json/g_items_");
            path.append(position);
            path.append(".json");
            return VCategoryFragment.newInstance(path.toString());
        }

        @Override
        public int getCount() {
            return titles.length;
        }
    }
}
