package com.wukj.uilibrary.model.tagcloud.sample;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wukj.uilibrary.model.tagcloud.TagsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：UILibrary
 * 创建时间：2018/10/30 0030 下午 7:05
 * 作者：Jonyker
 * 博客：http://www.udevtech.com
 * github：https://github.com/Jonyker
 * 修改人：Jonyker
 * 联系方式：QQ/534098845
 * 修改时间：2018/10/30 0030 下午 7:05
 * 备注：
 * 版本：V.1.0
 * 描述：
 * 1.
 * 2.
 * 3.
 */
public class TextTagsAdapter extends TagsAdapter {

    private List<TagEntity> dataSet = new ArrayList<>();

    public TextTagsAdapter(@NonNull List<TagEntity> dataset) {
        dataSet.clear();
        dataSet.addAll(dataset);
    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public View getView(final Context context, final int position, ViewGroup parent) {
        final TagEntity tagEntity = dataSet.get(position);
        TextView tv = new TextView(context);
        tv.setText(tagEntity.getTitle());
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(Color.BLUE);
        return tv;
    }

    @Override
    public Object getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public int getPopularity(int position) {
        return position % 3;
    }

    @Override
    public void onThemeColorChanged(View view, int themeColor, float alpha) {
         view.setBackgroundColor(themeColor);
    }


}

