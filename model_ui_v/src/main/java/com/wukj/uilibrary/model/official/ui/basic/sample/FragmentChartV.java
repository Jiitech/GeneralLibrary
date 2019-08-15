package com.wukj.uilibrary.model.official.ui.basic.sample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.wukj.uilibrary.model.library.LogUtils;
import com.wukj.uilibrary.model.library.sup.SupFragment;
import com.wukj.uilibrary.model.official.ui.R;
import com.wukj.uilibrary.model.official.ui.basic.ChartV;

public class FragmentChartV extends SupFragment {


    private ChartV mChartV;

    @Override
    protected int getCreateVID() {
        return R.layout.fragment_chart;
    }

    @Override
    protected void onInitV(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mChartV = view.findViewById(R.id.chartv);



    }




}