package com.midland.web.service.impl;

import com.midland.web.dao.CommunityAlbumMapper;
import com.midland.web.model.CommunityAlbum;
import com.midland.web.service.CommunityAlbumService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommunityAlbumServiceImpl implements CommunityAlbumService {

    private Logger log = LoggerFactory.getLogger(CommunityAlbumServiceImpl.class);
    @Autowired
    private CommunityAlbumMapper communityAlbumMapper;

    /**
     * 插入
     **/
    @Override
    public void insertCommunityAlbum(CommunityAlbum communityAlbum) throws Exception {
        try {
            log.info("insert {}", communityAlbum);
            communityAlbumMapper.insertCommunityAlbum(communityAlbum);
        } catch (Exception e) {
            log.error("insertCommunityAlbum异常 {}", communityAlbum, e);
            throw e;
        }
    }




    /**
     * 上移
     **/
    @Override
    @Transactional
    public void shiftUp(CommunityAlbum communityAlbum) throws Exception {
        try {
            log.debug("shiftUp {}", communityAlbum);
            CommunityAlbum obj = communityAlbumMapper.shiftUp(communityAlbum);
            if (obj == null){
                return;
            }
            int nextOrderBy = obj.getOrderBy();
            int currOrderBy = communityAlbum.getOrderBy();
            obj.setOrderBy(-999999999);
            communityAlbumMapper.updateCommunityAlbumById(obj);
            communityAlbum.setOrderBy(nextOrderBy);
            communityAlbumMapper.updateCommunityAlbumById(communityAlbum);
            obj.setOrderBy(currOrderBy);
            communityAlbumMapper.updateCommunityAlbumById(obj);
        } catch (Exception e) {
            log.error("shiftUp {}", communityAlbum, e);
            throw e;
        }
    }

    /**
     * 下移
     **/
    @Override
    @Transactional
    public void shiftDown(CommunityAlbum communityAlbum) throws Exception {
        try {
            log.debug("shiftDown {}", communityAlbum);
            CommunityAlbum obj = communityAlbumMapper.shiftDown(communityAlbum);
            if (obj == null){
                return;
            }
            int nextOrderBy = obj.getOrderBy();
            int currOrderBy = communityAlbum.getOrderBy();
            obj.setOrderBy(-999999999);
            communityAlbumMapper.updateCommunityAlbumById(obj);
            communityAlbum.setOrderBy(nextOrderBy);
            communityAlbumMapper.updateCommunityAlbumById(communityAlbum);
            obj.setOrderBy(currOrderBy);
            communityAlbumMapper.updateCommunityAlbumById(obj);
        } catch (Exception e) {
            log.error("shiftDown异常 {}", communityAlbum, e);
            throw e;
        }
    }

    /**
     * 查询
     **/
    @Override
    public CommunityAlbum selectCommunityAlbumById(Integer id) {
        log.info("selectCommunityAlbumById  {}", id);
        return communityAlbumMapper.selectCommunityAlbumById(id);
    }

    /**
     * 删除
     **/
    @Override
    public void deleteCommunityAlbumById(Integer id) throws Exception {
        try {
            log.info("deleteCommunityAlbumById  {}", id);
            int result = communityAlbumMapper.deleteCommunityAlbumById(id);
            if (result < 1) {
                throw new Exception("deleteCommunityAlbumById失败");
            }
        } catch (Exception e) {
            log.error("deleteCommunityAlbumById  {}", id, e);
            throw e;
        }
    }

    /**
     * 更新
     **/
    @Override
    public void updateCommunityAlbumById(CommunityAlbum communityAlbum) throws Exception {
        try {
            log.info("updateCommunityAlbumById  {}", communityAlbum);
            int result = communityAlbumMapper.updateCommunityAlbumById(communityAlbum);
            if (result < 1) {
                throw new Exception("updateCommunityAlbumById失败");
            }
        } catch (Exception e) {
            log.error("updateCommunityAlbumById  {}", communityAlbum, e);
            throw e;
        }
    }

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @Override
    public List<CommunityAlbum> findCommunityAlbumList(CommunityAlbum communityAlbum) throws Exception {
        try {
            log.info("findCommunityAlbumList  {}", communityAlbum);
            return communityAlbumMapper.findCommunityAlbumList(communityAlbum);
        } catch (Exception e) {
            log.error("findCommunityAlbumList  {}", communityAlbum, e);
            throw e;
        }
    }
}
