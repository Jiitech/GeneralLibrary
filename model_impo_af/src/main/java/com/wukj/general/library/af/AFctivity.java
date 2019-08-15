package com.wukj.general.library.af;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.wukj.general.library.af.results.FunctionManager;
import com.wukj.general.library.af.results.FunctionNoParamNoResult;
import com.wukj.general.library.af.results.FunctionNoParamWithResult;
import com.wukj.general.library.af.results.FunctionWithParamNoResult;
import com.wukj.general.library.af.results.FunctionWithParamWithResult;

public class AFctivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_af);

//        NoParamNoResultFragment functionFragment = new NoParamNoResultFragment();
//        NoParamWithResultFragment functionFragment = new NoParamWithResultFragment();
//        WithParamNoResultFragment functionFragment = new WithParamNoResultFragment();
        WithParamWithResultFragment functionFragment = new WithParamWithResultFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_id, functionFragment, WithParamWithResultFragment.FUNCTION_NAME)
                .commit();


    }


    public void setFunctionForFragment(String tag) {
        FragmentManager fm = getSupportFragmentManager();
        BaseFragment fragment = (BaseFragment) fm.findFragmentByTag(tag);
        FunctionManager fManager = FunctionManager.getInstance();
        fragment.setFunctionManager(fManager.addFunction(new FunctionNoParamNoResult(NoParamNoResultFragment.FUNCTION_NAME) {
            @Override
            public void function() {

                Toast.makeText(AFctivity.this, "调用无参无返回值的接口", Toast.LENGTH_SHORT).show();

            }
        }).addFunction(new FunctionNoParamWithResult<String>(NoParamWithResultFragment.FUNCTION_NAME) {
            @Override
            public String function() {
                return "调用无参有返回值的接口";
            }
        }).addFunction(new FunctionWithParamNoResult<String>(WithParamNoResultFragment.FUNCTION_NAME) {
            @Override
            public void function(String o) {
                Toast.makeText(AFctivity.this, o, Toast.LENGTH_SHORT).show();
            }
        }).addFunction(new FunctionWithParamWithResult<String,String>(WithParamWithResultFragment.FUNCTION_NAME) {
            @Override
            public String function(String o) {
                return "参数是："+o+", 返回值是：100";
            }
        }));
    }


}
