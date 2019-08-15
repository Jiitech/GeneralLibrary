package com.jonyker.mvp.architecture.common;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * 项目名称：Android-MVP-Architecture
 * 类名称：com.jonyker.mvp.architecture.common
 * 类描述：
 * 创建人：Jonyker
 * 创建时间：2017/6/20 0020 下午 1:54
 * 修改人：Jonyker
 * 修改时间：2017/6/20 0020 下午 1:54
 * 修改备注：
 * 版本：V.1.0
 */

public class BaseActivity extends AppCompatActivity {
    protected  <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }
}
