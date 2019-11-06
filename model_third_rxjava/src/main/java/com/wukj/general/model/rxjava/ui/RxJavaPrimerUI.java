package com.wukj.general.model.rxjava.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.wukj.general.common.fragment.SupListFragment;
import com.wukj.general.model.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class RxJavaPrimerUI extends SupListFragment {

    public static final String[] TITLES = {"title1", "title2", "title3", "title4", "title5"};
    @Override
    protected int getCreateVID() {
        return R.layout.fragment_rxjava_primer_ui;
    }

    @Override
    protected void onInitV(@NonNull View view, @Nullable Bundle savedInstanceState) {

        //设置ListView为单选模式
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        // 给listView设置adapter显示列表
        setListAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, TITLES));
        //默认选中第一个item
        getListView().setItemChecked(0, true);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

    }


}

