package com.midland.web.service;

import com.midland.web.model.Banner;

import java.util.List;

public interface BannerService {

    /**
     * 主键查询
     **/
    Banner selectById(Integer id);

    /**
     * 主键删除
     **/
    void deleteById(Integer id) throws Exception;

    /**
     * 主键更新
     **/
    void updateById(Banner banner) throws Exception;

    /**
     * 插入
     **/
    void insertBanner(Banner banner) throws Exception;

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    List<Banner> findBannerList(Banner banner) throws Exception;

    void batchUpdate(List<Banner> bannerList) throws Exception;

}
