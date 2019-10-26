package com.wukj.uilibrary.model.tagcloud.sample;

import java.io.Serializable;

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
public class TagEntity implements Serializable {

    private String title;
    private String cls;

    public TagEntity(String title, String cls) {
        this.title = title;
        this.cls = cls;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCls() {
        return cls;
    }

    public void setCls(String cls) {
        this.cls = cls;
    }


}
