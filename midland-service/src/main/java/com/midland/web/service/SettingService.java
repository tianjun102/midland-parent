package com.midland.web.service;

import com.midland.web.model.Area;
import com.midland.web.model.Banner;
import com.midland.web.model.LinkUrlManager;
import com.midland.web.model.Popular;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

public interface SettingService {

    public List<Popular> findPopularList(Popular popular);

    public Popular findPopular(Popular popular);

    public int updatePopular(Popular popular);

    public int insertPopular(Popular popular);

    public Map<String, List<Area>> queryCityByRedis(Map<String, String> parem);

    void getAllProvinceList(Model model);

    List<Area> getAllProvince();

    List<Area> queryAllCityByRedis();

    Area getCityByCityId(String cityId);

    Area queryProvinceByCityId(String cityId);

    List<Area> getAreaByCityId(String cityId);

    Area getDistByCityIdAndDistName(String cityId, String distName);

    public Map<String, List<Area>> queryAreaByRedis(Map<String, String> parem);

    void batchUpdatePopular(List<Popular> popularList) throws Exception;

    /**
     * 友情链接
     *
     * @param linkUrlManager
     * @return
     */

    public List<LinkUrlManager> findLinkUrlList(LinkUrlManager linkUrlManager);

    public LinkUrlManager findLinkUrlManager(LinkUrlManager linkUrlManager);

    public int updateLinkUrlManager(LinkUrlManager linkUrlManager);

    public int insertLinkUrlManage(LinkUrlManager linkUrlManager);

    @Transactional
    void shiftUp(LinkUrlManager category) throws Exception;

    @Transactional
    void shiftDown(LinkUrlManager linkUrlManager) throws Exception;

    void batchUpdateLinkUrl(List<LinkUrlManager> linkUrlManagerList) throws Exception;


    /**
     * Banner管理
     */

    public List<Banner> findBannerList(Banner banner);

    public Banner findBanner(Banner banner);

    public int updateBanner(Banner banner);

    public int insertBanner(Banner banner);

    void batchUpdateBanner(List<Banner> bannerList) throws Exception;

}
