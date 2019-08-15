package com.wukj.uilibrary.model.tagcloud.sample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.wukj.uilibrary.model.library.sup.SupFragment;

import com.wukj.uilibrary.model.tagcloud.R;
import com.wukj.uilibrary.model.tagcloud.TagCloudView;

import java.util.ArrayList;
import java.util.List;


public class FragmentTagCloud extends SupFragment {

    private TagCloudView mTagCloudView;
    private TextTagsAdapter mTextTagsAdapter;

    @Override
    protected int getCreateVID() {
        return R.layout.fragment_tagcloud;
    }

    @Override
    protected void onInitV(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mTagCloudView = view.findViewById(R.id.tag_cloud_v);
        mTextTagsAdapter = new TextTagsAdapter(getTagDataSets());
        mTagCloudView.setAdapter(mTextTagsAdapter);

    }

    private List<TagEntity> getTagDataSets() {
        List<TagEntity> mClaes = new ArrayList<>();
        mClaes.add(new TagEntity("标签云", ""));
        mClaes.add(new TagEntity("标签云", ""));
        mClaes.add(new TagEntity("标签云", ""));
        mClaes.add(new TagEntity("标签云", ""));
        mClaes.add(new TagEntity("标签云", ""));
        mClaes.add(new TagEntity("标签云", ""));
        mClaes.add(new TagEntity("标签云", ""));
        mClaes.add(new TagEntity("标签云", ""));
        mClaes.add(new TagEntity("标签云", ""));
        mClaes.add(new TagEntity("标签云", ""));
        mClaes.add(new TagEntity("标签云", ""));
        mClaes.add(new TagEntity("标签云", ""));
        mClaes.add(new TagEntity("标签云", ""));
        mClaes.add(new TagEntity("标签云", ""));
        mClaes.add(new TagEntity("标签云", ""));
        mClaes.add(new TagEntity("标签云", ""));
        mClaes.add(new TagEntity("标签云", ""));
        mClaes.add(new TagEntity("标签云", ""));
        mClaes.add(new TagEntity("标签云", ""));
        mClaes.add(new TagEntity("标签云", ""));
        return mClaes;
    }




}