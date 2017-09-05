package com.midland.web.service;

import com.midland.web.model.Area;
import com.midland.web.model.Banner;
import com.midland.web.model.LinkUrlManager;
import com.midland.web.model.Popular;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

public interface SettingService {

    public List<Popular> findPopularList(Popular popular );

    public Popular findPopular(Popular popular);

    public int updatePopular(Popular popular);

    public int insertPopular(Popular popular);

    public Map<String, List<Area>> queryCityByRedis(Map<String,String> parem);
	
	void getAllProvinceList(Model model);
	
	List<Area> queryAllCityByRedis();
	
	public Map<String, List<Area>> queryAreaByRedis(Map<String,String> parem);

    /**
     * 友情链接
     * @param linkUrlManager
     * @return
     */

    public List<LinkUrlManager> findLinkUrlList(LinkUrlManager linkUrlManager );

    public LinkUrlManager findLinkUrlManager(LinkUrlManager linkUrlManager);

    public int updateLinkUrlManager(LinkUrlManager linkUrlManager);

    public int insertLinkUrlManage(LinkUrlManager linkUrlManager);


    /**
     * Banner管理
     */

    public List<Banner> findBannerList(Banner banner );

    public Banner findBanner(Banner banner);

    public int updateBanner(Banner banner);

    public int insertBanner(Banner banner);


}
