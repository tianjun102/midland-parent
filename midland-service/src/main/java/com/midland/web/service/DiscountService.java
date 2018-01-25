package com.midland.web.service;

import com.midland.web.model.Discount;

import java.util.List;

public interface DiscountService {

    /**
     * 主键查询
     **/
    Discount selectDiscountById(Integer id);

    /**
     * 主键删除
     **/
    void deleteDiscountById(Integer id) throws Exception;

    /**
     * 主键更新
     **/
    void updateDiscountById(Discount discount) throws Exception;

    /**
     * 插入
     **/
    void insertDiscount(Discount discount) throws Exception;

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    List<Discount> findDiscountList(Discount discount) throws Exception;

    public void batchUpdate(List<Discount> discountList) throws Exception;

}
