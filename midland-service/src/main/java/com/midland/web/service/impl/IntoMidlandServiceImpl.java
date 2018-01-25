package com.midland.web.service.impl;

import com.midland.web.dao.IntoMidlandMapper;
import com.midland.web.model.IntoMidland;
import com.midland.web.service.IntoMidlandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntoMidlandServiceImpl implements IntoMidlandService {

    private Logger log = LoggerFactory.getLogger(IntoMidlandServiceImpl.class);
    @Autowired
    private IntoMidlandMapper intoMidlandMapper;

    /**
     * 插入
     **/
    @Override
    public void insertIntoMidland(IntoMidland intoMidland) throws Exception {
        try {
            log.debug("insert {}", intoMidland);
            intoMidlandMapper.insertIntoMidland(intoMidland);
        } catch (Exception e) {
            log.error("insertIntoMidland异常 {}", intoMidland, e);
            throw e;
        }
    }

    /**
     * 查询
     **/
    @Override
    public IntoMidland selectIntoMidlandById(Integer id) {
        log.debug("selectIntoMidlandById  {}", id);
        return intoMidlandMapper.selectIntoMidlandById(id);
    }

    /**
     * 删除
     **/
    @Override
    public void deleteIntoMidlandById(Integer id) throws Exception {
        try {
            log.debug("deleteIntoMidlandById  {}", id);
            int result = intoMidlandMapper.deleteIntoMidlandById(id);
            if (result < 1) {
                throw new Exception("deleteIntoMidlandById失败");
            }
        } catch (Exception e) {
            log.error("deleteIntoMidlandById  {}", id, e);
            throw e;
        }
    }

    /**
     * 更新
     **/
    @Override
    public void updateIntoMidlandById(IntoMidland intoMidland) throws Exception {
        try {
            log.debug("updateIntoMidlandById  {}", intoMidland);
            int result = intoMidlandMapper.updateIntoMidlandById(intoMidland);
            if (result < 1) {
                throw new Exception("updateIntoMidlandById失败");
            }
        } catch (Exception e) {
            log.error("updateIntoMidlandById  {}", intoMidland, e);
            throw e;
        }
    }

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @Override
    public List<IntoMidland> findIntoMidlandList(IntoMidland intoMidland) throws Exception {
        try {
            log.debug("findIntoMidlandList  {}", intoMidland);
            return intoMidlandMapper.findIntoMidlandList(intoMidland);
        } catch (Exception e) {
            log.error("findIntoMidlandList  {}", intoMidland, e);
            throw e;
        }
    }
}
