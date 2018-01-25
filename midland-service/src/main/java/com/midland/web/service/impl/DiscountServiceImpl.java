package com.midland.web.service.impl;

import com.midland.web.dao.DiscountMapper;
import com.midland.web.model.Discount;
import com.midland.web.service.DiscountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {

    private Logger log = LoggerFactory.getLogger(DiscountServiceImpl.class);
    @Autowired
    private DiscountMapper discountMapper;

    /**
     * 插入
     **/
    @Override
    public void insertDiscount(Discount discount) throws Exception {
        try {
            log.info("insert {}", discount);
            discountMapper.insertDiscount(discount);
        } catch (Exception e) {
            log.error("insertDiscount异常 {}", discount, e);
            throw e;
        }
    }

    /**
     * 查询
     **/
    @Override
    public Discount selectDiscountById(Integer id) {
        log.info("selectDiscountById  {}", id);
        return discountMapper.selectDiscountById(id);
    }

    /**
     * 删除
     **/
    @Override
    public void deleteDiscountById(Integer id) throws Exception {
        try {
            log.info("deleteDiscountById  {}", id);
            int result = discountMapper.deleteDiscountById(id);
            if (result < 1) {
                throw new Exception("deleteDiscountById失败");
            }
        } catch (Exception e) {
            log.error("deleteDiscountById  {}", id, e);
            throw e;
        }
    }

    /**
     * 更新
     **/
    @Override
    public void updateDiscountById(Discount discount) throws Exception {
        try {
            log.info("updateDiscountById  {}", discount);
            int result = discountMapper.updateDiscountById(discount);
            if (result < 1) {
                throw new Exception("updateDiscountById失败");
            }
        } catch (Exception e) {
            log.error("updateDiscountById  {}", discount, e);
            throw e;
        }
    }

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @Override
    public List<Discount> findDiscountList(Discount discount) throws Exception {
        try {
            log.info("findDiscountList  {}", discount);
            return discountMapper.findDiscountList(discount);
        } catch (Exception e) {
            log.error("findDiscountList  {}", discount, e);
            throw e;
        }
    }

    @Override
    public void batchUpdate(List<Discount> discountList) throws Exception {
        try {
            log.debug("discountList  {}", discountList);
            int result = discountMapper.batchUpdate(discountList);
            if (result < 1) {
                throw new Exception("discountList");
            }
        } catch (Exception e) {
            log.error("discountList  {}", discountList, e);
            throw e;
        }
    }
}
