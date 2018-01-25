package com.midland.web.service.impl;

import com.midland.web.dao.HotHandMapper;
import com.midland.web.model.HotHand;
import com.midland.web.service.HotHandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HotHandServiceImpl implements HotHandService {

    private Logger log = LoggerFactory.getLogger(HotHandServiceImpl.class);
    @Autowired
    private HotHandMapper hotHandMapper;

    /**
     * 插入
     **/
    @Override
    public void insertHotHand(HotHand hotHand) throws Exception {
        try {
            log.info("insert {}", hotHand);
            hotHandMapper.insertHotHand(hotHand);
        } catch (Exception e) {
            log.error("insertHotHand异常 {}", hotHand, e);
            throw e;
        }
    }



    /**
     * 查询
     **/
    @Override
    public HotHand selectHotHandById(Integer id) {
        log.info("selectHotHandById  {}", id);
        return hotHandMapper.selectHotHandById(id);
    }

    /**
     * 上移
     **/
    @Override
    @Transactional
    public void shiftUp(HotHand hotHand) throws Exception {
        try {
            log.debug("shiftUp {}", hotHand);
            HotHand obj = hotHandMapper.shiftUp(hotHand);
            if (obj == null){
                return;
            }
            int nextOrderBy = obj.getOrderBy();
            int currOrderBy = hotHand.getOrderBy();
            obj.setOrderBy(-999999999);
            hotHandMapper.updateHotHandById(obj);
            hotHand.setOrderBy(nextOrderBy);
            hotHandMapper.updateHotHandById(hotHand);
            obj.setOrderBy(currOrderBy);
            hotHandMapper.updateHotHandById(obj);
        } catch (Exception e) {
            log.error("shiftUp {}", hotHand, e);
            throw e;
        }
    }

    /**
     * 下移
     **/
    @Override
    @Transactional
    public void shiftDown(HotHand hotHand) throws Exception {
        try {
            log.debug("shiftDown {}", hotHand);
            HotHand obj = hotHandMapper.shiftDown(hotHand);
            if (obj == null){
                return;
            }
            int nextOrderBy = obj.getOrderBy();
            int currOrderBy = hotHand.getOrderBy();
            obj.setOrderBy(-999999999);
            hotHandMapper.updateHotHandById(obj);
            hotHand.setOrderBy(nextOrderBy);
            hotHandMapper.updateHotHandById(hotHand);
            obj.setOrderBy(currOrderBy);
            hotHandMapper.updateHotHandById(obj);
        } catch (Exception e) {
            log.error("shiftDown异常 {}", hotHand, e);
            throw e;
        }
    }

    /**
     * 删除
     **/
    @Override
    public void deleteHotHandById(Integer id) throws Exception {
        try {
            log.info("deleteHotHandById  {}", id);
            int result = hotHandMapper.deleteHotHandById(id);
            if (result < 1) {
                throw new Exception("deleteHotHandById失败");
            }
        } catch (Exception e) {
            log.error("deleteHotHandById  {}", id, e);
            throw e;
        }
    }

    /**
     * 更新
     **/
    @Override
    public void updateHotHandById(HotHand hotHand) throws Exception {
        try {
            log.info("updateHotHandById  {}", hotHand);
            int result = hotHandMapper.updateHotHandById(hotHand);
            if (result < 1) {
                throw new Exception("updateHotHandById失败");
            }
        } catch (Exception e) {
            log.error("updateHotHandById  {}", hotHand, e);
            throw e;
        }
    }

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @Override
    public List<HotHand> findHotHandList(HotHand hotHand) throws Exception {
        try {
            log.info("findHotHandList  {}", hotHand);
            return hotHandMapper.findHotHandList(hotHand);
        } catch (Exception e) {
            log.error("findHotHandList  {}", hotHand, e);
            throw e;
        }
    }
}
