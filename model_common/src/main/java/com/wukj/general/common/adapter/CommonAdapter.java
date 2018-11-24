package com.wukj.general.common.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 项目名称：dev_android_ecx_debug_v_1.0
 * 类名称：com.jonyker.common.base.pattern.view
 * 类描述：
 * 创建人：Jonyker
 * 创建时间：2017/8/9 0009 下午 2:46
 * 修改人：Jonyker
 * 联系方式：QQ/534098845
 * 修改时间：2017/8/9 0009 下午 2:46
 * 修改备注：
 * 版本：V.1.0
 */

public abstract class CommonAdapter<T> extends BaseAdapter{

    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    protected int mlayoutId;

    public CommonAdapter(Context context, List<T> datas, int layoutId)
    {
        this.mContext = context;
        this.mDatas = datas;
        this.mlayoutId = layoutId;
        mInflater = LayoutInflater.from(context);
    }

    /**
     * @see android.widget.Adapter#getCount()
     */
    @Override
    public int getCount()
    {
        return mDatas.size();
    }

    /**
     * @see android.widget.Adapter#getItem(int)
     */
    @Override
    public T getItem(int position)
    {
        return mDatas.get(position);
    }

    /**
     * @see android.widget.Adapter#getItemId(int)
     */
    @Override
    public long getItemId(int position)
    {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        CommonViewHolder holder = CommonViewHolder.get(mContext, convertView, parent, mlayoutId, position);

        convert(holder, getItem(position));

        return holder.getConvertView();
    }

    public abstract void convert(CommonViewHolder holder, T t);

    /**
     * 更新数据
     * @param datas
     */
    public void updateItems(List<T> datas){
        this.mDatas = datas;
        this.notifyDataSetChanged();
    }

    protected int getColor(Context context,int colorId){
        return context.getResources().getColor(colorId);
    }

}
