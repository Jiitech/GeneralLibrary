package com.wukj.general.library.af;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.wukj.general.library.af.results.FunctionManager;

/**
 * 项目名称：AFCommunication
 * 创建时间：2018/7/13 0013 上午 8:47
 * 作者：Jonyker
 * 博客：http://blog.csdn.net/jerry_137188
 * github：https://github.com/Jiitech
 * 修改人：Jonyker
 * 联系方式：QQ/534098845
 * 修改时间：2018/7/13 0013 上午 8:47
 * 备注：
 * 版本：V.1.0
 * 描述：
 */
public class BaseFragment extends Fragment {

    protected FunctionManager mFunctionManager;
    private AFctivity mMainActivity;

    public void setFunctionManager(FunctionManager functionManager) {
        this.mFunctionManager = functionManager;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AFctivity) {
            mMainActivity = (AFctivity) context;
            mMainActivity.setFunctionForFragment(getTag());
        }
    }





}
