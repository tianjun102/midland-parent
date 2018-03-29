package com.midland.web.service.impl;

import com.midland.web.dao.InformationMapper;
import com.midland.web.model.Information;
import com.midland.web.service.InformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InformationServiceImpl implements InformationService {

    private Logger log = LoggerFactory.getLogger(InformationServiceImpl.class);
    @Autowired
    private InformationMapper informationMapper;

    /**
     * 插入
     **/
    @Override
    public void insertInformation(Information information) throws Exception {
        try {
            log.debug("insert {}", information);
            informationMapper.insertInformation(information);
        } catch (Exception e) {
            log.error("insertInformation异常 {}", information, e);
            throw e;
        }
    }

    /**
     * 查询
     **/
    @Override
    public Information selectInformationById(Integer id) {
        log.debug("selectInformationById  {}", id);
        return informationMapper.selectInformationById(id);
    }

    /**
     * 查询
     **/
    @Override
    public List<Information> getByIdList(List<Integer> ids) {
        log.debug("selectInformationById  {}", ids);
        return informationMapper.getByIdList(ids);
    }

    /**
     * 删除
     **/
    @Override
    public void deleteInformationById(Integer id) throws Exception {
        try {
            log.debug("deleteInformationById  {}", id);
            int result = informationMapper.deleteInformationById(id);
            if (result < 1) {
                throw new Exception("deleteInformationById失败");
            }
        } catch (Exception e) {
            log.error("deleteInformationById  {}", id, e);
            throw e;
        }
    }

    /**
     * 更新
     **/
    @Override
    public void updateInformationById(Information information) throws Exception {
        try {
            log.debug("updateInformationById  {}", information);
            int result = informationMapper.updateInformationById(information);
            if (result < 1) {
                throw new Exception("updateInformationById失败");
            }
        } catch (Exception e) {
            log.error("updateInformationById  {}", information, e);
            throw e;
        }
    }

    /**
     * 上移
     **/
    @Override
    @Transactional
    public void shiftUp(Information information) throws Exception {
        try {
            log.debug("shiftUp {}", information);
            Information obj = informationMapper.shiftUp(information);
            if (obj == null){
                return;
            }
            int nextOrderBy = obj.getOrderBy();
            int currOrderBy = information.getOrderBy();
            obj.setOrderBy(-999999999);
            informationMapper.updateInformationById(obj);
            information.setOrderBy(nextOrderBy);
            informationMapper.updateInformationById(information);
            obj.setOrderBy(currOrderBy);
            informationMapper.updateInformationById(obj);
        } catch (Exception e) {
            log.error("shiftUp {}", information, e);
            throw e;
        }
    }
  @Override
    @Transactional
    public void shiftTop(Information information) throws Exception {
        try {
            log.debug("shiftTop {}", information);
            Information obj = informationMapper.shiftTop(information);

        } catch (Exception e) {
            log.error("shiftUp {}", information, e);
            throw e;
        }
    }

    /**
     * 下移
     **/
    @Override
    @Transactional
    public void shiftDown(Information information) throws Exception {
        try {
            log.debug("shiftDown {}", information);
            Information obj = informationMapper.shiftDown(information);
            if (obj == null){
                return;
            }
            int nextOrderBy = obj.getOrderBy();
            int currOrderBy = information.getOrderBy();
            obj.setOrderBy(-999999999);
            informationMapper.updateInformationById(obj);
            information.setOrderBy(nextOrderBy);
            informationMapper.updateInformationById(information);
            obj.setOrderBy(currOrderBy);
            informationMapper.updateInformationById(obj);
        } catch (Exception e) {
            log.error("shiftDown异常 {}", information, e);
            throw e;
        }
    }


    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @Override
    public List<Information> findInformationList(Information information) throws Exception {
        try {
            log.debug("findInformationList  {}", information);
            return informationMapper.findInformationList(information);
        } catch (Exception e) {
            log.error("findInformationList  {}", information, e);
            throw e;
        }
    }

    @Override
    public List<Information> getCates(Information information) throws Exception {
        try {
            log.debug("getCates  {}", information);
            return informationMapper.getCates(information);
        } catch (Exception e) {
            log.error("getCates  {}", information, e);
            throw e;
        }
    }

    @Override
    public List<Information> findNewestInformationList(Information information) throws Exception {
        try {
            log.debug("findNewestInformationList  {}", information);
            return informationMapper.findNewestInformationList(information);
        } catch (Exception e) {
            log.error("findNewestInformationList  {}", information, e);
            throw e;
        }
    }

    @Override
    public void batchUpdate(List<Information> informationList) throws Exception {
        try {
            log.debug("updateInformationById  {}", informationList);
            int result = informationMapper.batchUpdate(informationList);
            if (result < 1) {
                throw new Exception("updateFeedbackById失败");
            }
        } catch (Exception e) {
            log.error("updateInformationById  {}", informationList, e);
            throw e;
        }
    }
}
