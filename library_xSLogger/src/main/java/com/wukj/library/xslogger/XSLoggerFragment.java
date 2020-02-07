package com.wukj.library.xslogger;

import android.os.Bundle;
import android.view.View;

import com.wukj.general.common.fragment.SupFragment;
import com.wukj.library.xslogger.manager.LogManager;
import com.wukj.library.xslogger.utils.LogUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class XSLoggerFragment extends SupFragment {

    private static final String TAG = "MainActivity";

    private int step = 0;

    @Override
    protected int getCreateVID() {
        return R.layout.fragment_xslogger;
    }

    @Override
    protected void onInitV(@NonNull View view, @Nullable Bundle savedInstanceState) {
        LogManager.getManager(getActivity()).registerActivity(getActivity());

        view.findViewById(R.id.button_xslogger).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LogManager.getManager(getActivity()).log(TAG, getLog("onCreate"),LogUtils.LOG_TYPE_2_TRACE);

            }
        });


    }


    private String getLog(String log){
        step ++;
        StringBuffer buffer = new StringBuffer();
        buffer.append("step->");
        buffer.append(step);
        buffer.append(":");
        buffer.append(log);
        return buffer.toString();
    }


    @Override
    public void onDestroy() {

        LogManager.getManager(getActivity()).unregisterActivity(getActivity());

        super.onDestroy();

    }




}

