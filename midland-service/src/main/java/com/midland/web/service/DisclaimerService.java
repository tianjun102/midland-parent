package com.midland.web.service;

import com.midland.web.model.Disclaimer;

import java.util.List;

public interface DisclaimerService {

    /**
     * 主键查询
     **/
    Disclaimer selectDisclaimerById(Integer id);

    /**
     * 主键删除
     **/
    void deleteDisclaimerById(Integer id) throws Exception;

    /**
     * 主键更新
     **/
    void updateDisclaimerById(Disclaimer disclaimer) throws Exception;

    /**
     * 插入
     **/
    void insertDisclaimer(Disclaimer disclaimer) throws Exception;

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    List<Disclaimer> findDisclaimerList(Disclaimer disclaimer) throws Exception;

}
