package com.wukj.general.library.fragment.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.wukj.general.common.fragment.SupFragment;
import com.wukj.general.library.Flag;
import com.wukj.general.library.R;
import com.wukj.general.library.activity.TargetActivity;
import com.wukj.general.library.adapter.VBasicCategoryAdapter;
import com.wukj.general.library.adapter.VBasicItemAdapter;
import com.wukj.general.library.entity.VCategoryEntity;
import com.wukj.general.library.entity.VCItemEntity;
import com.wukj.general.library.entity.gson.VCategoryGson;
import com.wukj.general.utils.LogUtils;
import com.wukj.general.utils.ReadAssetsUtils;


/**
 *  * 项目名称：UILibrary
 *  * 创建时间：2018/11/17 上午2:52
 *  * 作者：Jonyker
 *  * 博客：http://www.udevtech.com
 *  * github：https://github.com/Jiitech
 *  * 修改人：Jonyker
 *  * 联系方式：QQ/534098845
 *  * 修改时间：2018/11/17 上午2:52
 *  * 备注：
 *  * 版本：V.1.0
 *  * 描述：
 *  * 1.
 *  * 2.
 *  * 3.
 *  
 */

public class VCategoryFragment extends SupFragment {


    private ListView mContainerLV;
    private ListView mItemsLV;
    private VBasicCategoryAdapter mVCAdapter;
    private VBasicItemAdapter mVIAdapter;


    @Override
    protected int getCreateVID() {
        return R.layout.fragment_v_category;
    }

    @Override
    protected void onInitV(@NonNull View root, @Nullable Bundle savedInstanceState) {

        mContainerLV = root.findViewById(R.id.v_basic_container_lv);
        mItemsLV = root.findViewById(R.id.v_basic_items_lv);


        String path = getArguments().getString(Flag.JSON_PATH);
        LogUtils.d(this.getClass(),"path:"+path);
        String categoryJSON = ReadAssetsUtils.readLocalJSON(getActivity(), path);

        Gson gson = new Gson();
        VCategoryGson categoryGson = gson.fromJson(categoryJSON, VCategoryGson.class);

        mVCAdapter = new VBasicCategoryAdapter(getActivity(), categoryGson.getResults(), R.layout.item_category);
        mVIAdapter = new VBasicItemAdapter(getActivity(), categoryGson.getResults().get(0).getItems(), R.layout.item_category_item);
        mItemsLV.setAdapter(mVIAdapter);
        mContainerLV.setAdapter(mVCAdapter);
        mContainerLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                VCategoryEntity vBCEntities = (VCategoryEntity) view.getTag(R.id.category_id);
                if (mVIAdapter == null) {
                    mVIAdapter = new VBasicItemAdapter(getActivity(), vBCEntities.getItems(), R.layout.item_category_item);
                } else {
                    mVIAdapter.updateItems(vBCEntities.getItems());
                }
                mItemsLV.setAdapter(mVIAdapter);
                mItemsLV.startLayoutAnimation();
                mVCAdapter.setSelectIndex(position);

            }
        });
        mItemsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                VCItemEntity entity = (VCItemEntity) view.getTag(R.id.category_item_id);
                Intent intent = new Intent(getActivity(), TargetActivity.class);
                intent.putExtra(Flag.TARGET, entity.getClazz());
                getActivity().startActivity(intent);
            }
        });

    }


    public static VCategoryFragment newInstance(String jsonPath) {
        VCategoryFragment f = new VCategoryFragment();
        Bundle args = new Bundle();
        args.putString(Flag.JSON_PATH,jsonPath);
        f.setArguments(args);
        return f;
    }


}
