package com.wukj.general.library.sdk;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.wukj.general.common.fragment.SupFragment;


public class HandlerFragment   extends SupFragment {

    @Override
    protected int getCreateVID() {
        return R.layout.fragment_handler;
    }

    @Override
    protected void onInitV(@NonNull View view, @Nullable Bundle savedInstanceState) {


        Handler mHandler =new Handler();


    }



}
