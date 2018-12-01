package com.wukj.general.multiload.db;

import com.wukj.general.multiload.entity.FileInfoEntity;

import java.util.List;

/**
 * 项目名称：GeneralLibrary
 * 创建时间：2018/12/1 下午7:31
 * 作者：Jonyker
 * 博客：http://www.udevtech.com
 * github：https://github.com/Jiitech
 * 修改人：Jonyker
 * 联系方式：QQ/534098845
 * 修改时间：2018/12/1 下午7:31
 * 备注：
 * 版本：V.1.0
 * 描述：
 * 1.
 * 2.
 * 3.
 */
public interface ThreadDao {
    // 插入线程信息
    public void insertThread(FileInfoEntity threadInfo);

    // 删除线程信息
    public void deleteThread(String url, int threadId);

    // 删除所有关于这个url的线程
    public void deleteThread(String url);

    // 更新线程信息
    public void updateThread(String url, int threadId, int finished);

    // 查询线程信息
    public List<FileInfoEntity> queryThread(String url);

    // 线程信息是否存在
    public boolean isThreadInfoExist(String url, int threadId);
}
