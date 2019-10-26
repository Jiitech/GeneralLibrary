package com.wukj.general.library.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.wukj.general.common.adapter.CommonAdapter;
import com.wukj.general.common.adapter.CommonViewHolder;
import com.wukj.general.library.R;
import com.wukj.general.library.entity.VCategoryEntity;

import java.util.List;

/**
 * 项目名称：UILibrary
 * 创建时间：2018/10/30 0030 下午 6:21
 * 作者：Jonyker
 * 博客：https://www.jianshu.com/u/07642698e7f4
 * github：https://github.com/Jonyker
 * 修改人：Jonyker
 * 联系方式：QQ/534098845
 * 修改时间：2018/10/30 0030 下午 6:21
 * 备注：
 * 版本：V.1.0
 * 描述：
 * 1.
 * 2.
 * 3.
 */
public class VBasicCategoryAdapter extends CommonAdapter<VCategoryEntity> {


    private int mSelectedIndex = 0;

    public VBasicCategoryAdapter(Context context, List<VCategoryEntity> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(CommonViewHolder holder, VCategoryEntity entity) {

        View convertView = holder.getConvertView();
        convertView.setTag(R.id.category_id, entity);

        TextView tv = holder.getView(R.id.item_category_title_tv);
        if (mSelectedIndex == holder.getmPosition()) {
            tv.setBackgroundResource(R.drawable.selector_category_item_bg);
        } else {
            tv.setBackgroundResource(R.drawable.selector_category_bg);
        }
        tv.setText(entity.getTitle());


    }

    public void setSelectIndex(int index) {
        this.mSelectedIndex = index;
        notifyDataSetChanged();
    }


}
