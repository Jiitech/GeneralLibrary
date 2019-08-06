package com.wukj.general.library.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.wukj.general.common.adapter.CommonAdapter;
import com.wukj.general.common.adapter.CommonViewHolder;
import com.wukj.general.library.R;
import com.wukj.general.library.entity.VCItemEntity;

import java.util.List;

/**
 *  项目名称：UILibrary
 *  创建时间：2018/11/17 上午11:59
 *  作者：Jonyker
 *  博客：http://www.udevtech.com
 *  github：https://github.com/Jonyker
 *  修改人：Jonyker
 *  联系方式：QQ/534098845
 *  修改时间：2018/11/17 上午11:59
 *  备注：
 *  版本：V.1.0
 *  描述：
 *  1.
 *  2.
 *  3.
 *  
 */

public class VBasicItemAdapter extends CommonAdapter<VCItemEntity> {


    private Activity activity;

    public VBasicItemAdapter(Context context, List<VCItemEntity> datas, int layoutId) {
        super(context, datas, layoutId);
        activity = (Activity) context;
    }

    @Override
    public void convert(CommonViewHolder holder, final VCItemEntity entity) {

        View convertView = holder.getConvertView();
        convertView.setTag(R.id.category_item_id, entity);

        holder.setText(R.id.item_category_item_title_tv,entity.getTitle());


    }


}
