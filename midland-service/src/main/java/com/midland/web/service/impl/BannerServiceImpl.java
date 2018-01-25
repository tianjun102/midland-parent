package com.midland.web.service.impl;

import com.midland.web.dao.BannerMapper;
import com.midland.web.model.Banner;
import com.midland.web.service.BannerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {

    private Logger log = LoggerFactory.getLogger(BannerServiceImpl.class);
    @Autowired
    private BannerMapper bannerMapper;

    /**
     * 插入
     **/
    @Override
    public void insertBanner(Banner banner) throws Exception {
        try {
            log.debug("insert {}", banner);
            bannerMapper.insertBanner(banner);
        } catch (Exception e) {
            log.error("insertBanner异常 {}", banner, e);
            throw e;
        }
    }

    /**
     * 查询
     **/
    @Override
    public Banner selectById(Integer id) {
        log.debug("selectById  {}", id);
        return bannerMapper.selectById(id);
    }

    /**
     * 删除
     **/
    @Override
    public void deleteById(Integer id) throws Exception {
        try {
            log.debug("deleteById  {}", id);
            int result = bannerMapper.deleteById(id);
            if (result < 1) {
                throw new Exception("deleteById失败");
            }
        } catch (Exception e) {
            log.error("deleteById  {}", id, e);
            throw e;
        }
    }

    /**
     * 更新
     **/
    @Override
    public void updateById(Banner banner) throws Exception {
        try {
            log.debug("updateById  {}", banner);
            int result = bannerMapper.updateById(banner);
            if (result < 1) {
                throw new Exception("updateById失败");
            }
        } catch (Exception e) {
            log.error("updateById  {}", banner, e);
            throw e;
        }
    }

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @Override
    public List<Banner> findBannerList(Banner banner) throws Exception {
        try {
            log.debug("findBannerList  {}", banner);
            return bannerMapper.findBannerList(banner);
        } catch (Exception e) {
            log.error("findBannerList  {}", banner, e);
            throw e;
        }
    }

    @Override
    public void batchUpdate(List<Banner> bannerList) throws Exception {
        try {
            log.debug("updateBannerById  {}", bannerList);
            int result = bannerMapper.batchUpdate(bannerList);
            if (result < 1) {
                throw new Exception("updateAppointLogById失败");
            }
        } catch (Exception e) {
            log.error("updateBannerById  {}", bannerList, e);
            throw e;
        }
    }
}
