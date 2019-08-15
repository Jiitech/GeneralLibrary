package com.wukj.general.library.af;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class WithParamNoResultFragment extends BaseFragment {

    public static final String FUNCTION_NAME = WithParamNoResultFragment.class.getName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_function, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        view.findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mFunctionManager.invokeFunction(FUNCTION_NAME,"调用有参数无返回值的接口");
            }
        });
    }



}
