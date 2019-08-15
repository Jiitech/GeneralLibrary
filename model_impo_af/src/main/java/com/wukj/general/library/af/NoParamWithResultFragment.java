package com.wukj.general.library.af;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class NoParamWithResultFragment extends BaseFragment {

    public static final String FUNCTION_NAME = NoParamWithResultFragment.class.getName();

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
                String str = mFunctionManager.invokeFunction(FUNCTION_NAME,String.class);
                Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
            }
        });
    }


}
