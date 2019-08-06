package com.wukj.uilibrary.model.official.ui.sfv.sample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.wukj.uilibrary.model.official.ui.R;

import com.wukj.uilibrary.model.library.sup.SupFragment;
import com.wukj.uilibrary.model.official.ui.sfv.CSurfaceView;

public class FragmentCSurfaceView extends SupFragment {


    private CSurfaceView mCSurfaceView;

    @Override
    protected int getCreateVID() {
        return R.layout.fragment_csfv;
    }

    @Override
    protected void onInitV(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mCSurfaceView = view.findViewById(R.id.csfv);


    }




}