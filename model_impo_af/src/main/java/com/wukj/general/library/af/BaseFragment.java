package com.wukj.general.library.af;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.wukj.general.library.af.results.FunctionManager;

/**
 * 项目名称：GeneralLibrary
 * 创建时间：2018/11/25 上午12:20
 * 作者：Jonyker
 * 博客：http://www.udevtech.com
 * github：https://github.com/Jonyker
 * 修改人：Jonyker
 * 联系方式：QQ/534098845
 * 修改时间：2018/11/25 上午12:20
 * 备注：
 * 版本：V.1.0
 * 描述：
 * 1.
 * 2.
 * 3.
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
