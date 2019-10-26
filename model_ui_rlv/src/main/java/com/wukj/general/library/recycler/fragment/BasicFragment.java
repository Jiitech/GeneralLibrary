package com.wukj.general.library.recycler.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.wukj.general.common.fragment.SupFragment;
import com.wukj.general.library.recycler.R;


public class BasicFragment extends SupFragment {

    @Override
    protected int getCreateVID() {
        return R.layout.fragment_handler;
    }

    @Override
    protected void onInitV(@NonNull View view, @Nullable Bundle savedInstanceState) {


    }


}
