package com.wukj.general.library.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wukj.general.library.Flag;
import com.wukj.general.library.R;
import com.wukj.general.library.entity.VCItemEntity;

public class TargetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);

        VCItemEntity entity = (VCItemEntity) getIntent().getSerializableExtra(Flag.TARGET);

        getSupportActionBar().setTitle(entity.getTitle());
        replaceFragment((Fragment) getFClass(entity.getClazz()));


    }


    private void replaceFragment(Fragment target) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.f_content, target);
        transaction.commit();
    }

    private Object getFClass(String fClass) {
        Object obj;
        try {
            Class<?> clazz = Class.forName(fClass);
            obj = clazz.newInstance();
            return obj;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }


}
