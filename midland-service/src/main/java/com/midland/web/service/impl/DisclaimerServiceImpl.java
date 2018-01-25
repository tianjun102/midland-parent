package com.midland.web.service.impl;

import com.midland.web.dao.DisclaimerMapper;
import com.midland.web.model.Disclaimer;
import com.midland.web.service.DisclaimerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisclaimerServiceImpl implements DisclaimerService {

    private Logger log = LoggerFactory.getLogger(DisclaimerServiceImpl.class);
    @Autowired
    private DisclaimerMapper disclaimerMapper;

    /**
     * 插入
     **/
    @Override
    public void insertDisclaimer(Disclaimer disclaimer) throws Exception {
        try {
            log.info("insert {}", disclaimer);
            disclaimerMapper.insertDisclaimer(disclaimer);
        } catch (Exception e) {
            log.error("insertDisclaimer异常 {}", disclaimer, e);
            throw e;
        }
    }

    /**
     * 查询
     **/
    @Override
    public Disclaimer selectDisclaimerById(Integer id) {
        log.info("selectDisclaimerById  {}", id);
        return disclaimerMapper.selectDisclaimerById(id);
    }

    /**
     * 删除
     **/
    @Override
    public void deleteDisclaimerById(Integer id) throws Exception {
        try {
            log.info("deleteDisclaimerById  {}", id);
            int result = disclaimerMapper.deleteDisclaimerById(id);
            if (result < 1) {
                throw new Exception("deleteDisclaimerById失败");
            }
        } catch (Exception e) {
            log.error("deleteDisclaimerById  {}", id, e);
            throw e;
        }
    }

    /**
     * 更新
     **/
    @Override
    public void updateDisclaimerById(Disclaimer disclaimer) throws Exception {
        try {
            log.info("updateDisclaimerById  {}", disclaimer);
            int result = disclaimerMapper.updateDisclaimerById(disclaimer);
            if (result < 1) {
                throw new Exception("updateDisclaimerById失败");
            }
        } catch (Exception e) {
            log.error("updateDisclaimerById  {}", disclaimer, e);
            throw e;
        }
    }

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @Override
    public List<Disclaimer> findDisclaimerList(Disclaimer disclaimer) throws Exception {
        try {
            log.info("findDisclaimerList  {}", disclaimer);
            return disclaimerMapper.findDisclaimerList(disclaimer);
        } catch (Exception e) {
            log.error("findDisclaimerList  {}", disclaimer, e);
            throw e;
        }
    }
}
