package com.wukj.general.common.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 项目名称：UILibrary
 * 创建时间：2018/10/30 0030 下午 3:21
 * 作者：Jonyker
 * 博客：http://www.udevtech.com
 * github：https://github.com/Jiitech
 * 修改人：Jonyker
 * 联系方式：QQ/534098845
 * 修改时间：2018/10/30 0030 下午 3:21
 * 备注：
 * 版本：V.1.0
 * 描述：
 */
public abstract class SupFragment extends Fragment {


    protected abstract int getCreateVID();
    protected abstract void onInitV(@NonNull View view, @Nullable Bundle savedInstanceState);

    protected View getRV(){
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layoutID = getCreateVID();
        View rootV;
        if(layoutID != -1){
            rootV = inflater.inflate(layoutID,null);
        } else {
            rootV = getRV();
        }
        return rootV;
    }

    @Override
    public void onViewCreated(@NonNull View rV, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(rV, savedInstanceState);
        onInitV(rV,savedInstanceState);
    }




}
