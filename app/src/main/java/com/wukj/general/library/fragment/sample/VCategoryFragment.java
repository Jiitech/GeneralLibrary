package com.wukj.general.library.fragment.sample;

import android.app.Activity;
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

import java.io.Serializable;


/**
 * 项目名称：GeneralLibrary
 * 创建时间：2018/11/25 上午12:20
 * 作者：Jonyker
 * 博客：http://www.udevtech.com
 * github：https://github.com/Jiitech
 * 修改人：Jonyker
 * 联系方式：QQ/534098845
 * 修改时间：2018/11/25 上午12:20
 * 备注：
 * 版本：V.1.0
 * 描述：
 * 1.
 * 2.
 * 3.
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
        LogUtils.d(this.getClass(), "path:" + path);
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
                skipTarget(entity);
            }
        });

    }


    public static VCategoryFragment newInstance(String jsonPath) {
        VCategoryFragment f = new VCategoryFragment();
        Bundle args = new Bundle();
        args.putString(Flag.JSON_PATH, jsonPath);
        f.setArguments(args);
        return f;
    }

    private void skipTarget(VCItemEntity entity) {
        Intent intent;
        Object target = getTarget(entity.getClazz());
        if (target instanceof Activity) {
            intent = new Intent(getActivity(), target.getClass());
        } else {
            intent = new Intent(getActivity(), TargetActivity.class);
            intent.putExtra(Flag.TARGET, (Serializable) entity);
        }
        getActivity().startActivity(intent);
    }

    private Object getTarget(String aClass) {
        Object obj;
        try {
            // Create new fragment and transaction
            Class<?> clazz = Class.forName(aClass);
            obj = clazz.newInstance();
            return obj;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }


}
