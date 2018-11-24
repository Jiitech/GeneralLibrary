package com.wukj.general.library.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wukj.general.library.Flag;
import com.wukj.general.library.R;

public class TargetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);

        Object target = getTarget();

        if (target != null) {
            if (target instanceof Activity) {
                skipActivity(target.getClass());
            } else if (target instanceof Fragment) {
                replaceFragment((Fragment) target);
            } else {
                throw new IllegalArgumentException("Illegal parameter");
            }
        }


    }


    private void skipActivity(Class clazz) {
        Intent intent = new Intent(this, clazz);
        this.startActivity(intent);
        this.finish();
    }

    private void replaceFragment(Fragment target) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.f_content, target);
        transaction.commit();
    }

    private Object getTarget() {
        Object obj;
        try {
            String fClass = getIntent().getStringExtra(Flag.TARGET);
            // Create new fragment and transaction
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
