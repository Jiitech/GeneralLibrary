package com.wukj.general.library.entity;

import java.io.Serializable;
import java.util.List;


/**
 * 项目名称：UILibrary
 * 创建时间：2018/11/17 下午12:07
 * 作者：Jonyker
 * 博客：https://www.jianshu.com/u/07642698e7f4
 * github：https://github.com/Jonyker
 * 修改人：Jonyker
 * 联系方式：QQ/534098845
 * 修改时间：2018/11/17 下午12:07
 * 备注：
 * 版本：V.1.0
 * 描述：
 * 1.
 * 2.
 * 3.
 */
public class VCategoryEntity implements Serializable {

    private String type;
    private String title;
    private List<VCItemEntity> items;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<VCItemEntity> getItems() {
        return items;
    }

    public void setItems(List<VCItemEntity> items) {
        this.items = items;
    }



}
