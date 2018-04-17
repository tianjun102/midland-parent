package com.midland.web.service.impl;

import com.midland.config.MidlandConfig;
import com.midland.core.redis.IBaseRedisTemplate;
import com.midland.core.util.HttpUtils;
import com.midland.web.dao.BannerMapper;
import com.midland.web.dao.LinkUrlManagerMapper;
import com.midland.web.dao.PopularMapper;
import com.midland.web.model.Area;
import com.midland.web.model.Banner;
import com.midland.web.model.LinkUrlManager;
import com.midland.web.model.Popular;
import com.midland.web.service.SettingService;
import com.midland.web.util.MidlandHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class SettingServiceImpl implements SettingService {
    private Logger log = LoggerFactory.getLogger(SettingServiceImpl.class);

    private final Logger logger = LoggerFactory.getLogger(SettingServiceImpl.class);
    @Autowired
    private IBaseRedisTemplate baseRedisTemplate;

    @Autowired
    private PopularMapper popularMapper;

    @Autowired
    private LinkUrlManagerMapper linkUrlMapper;

    @Autowired
    private BannerMapper bannerMapper;
    @Autowired
    private MidlandConfig midlandConfig;

    @Override
    public List<Popular> findPopularList(Popular popular) {
        return popularMapper.findPopularList(popular);
    }

    @Override
    public Popular findPopular(Popular popular) {
        return popularMapper.selectById(popular.getId());
    }

    @Override
    public int updatePopular(Popular popular) {
        return popularMapper.updateById(popular);
    }

    @Override
    public int insertPopular(Popular popular) {
        return popularMapper.insertPopular(popular);
    }

    @Override
    public Map<String, List<Area>> queryCityByRedis(Map<String, String> parem) {
        return getStringListMap(parem);
    }

    /**
     * 查询所有省份
     *
     * @param model
     */
    @Override
    public void getAllProvinceList(Model model) {
        Map<String, String> param = new HashMap<>();
        param.put("flag", "province");
        Map<String, List<Area>> result = queryCityByRedis(param);
        List<Area> provinceList = result.get("province");

        model.addAttribute("provinceList", provinceList);
    }

    /**
     * 查询所有省份
     */
    @Override
    public List<Area> getAllProvince() {
        Map<String, String> param = new HashMap<>();
        param.put("flag", "province");
        Map<String, List<Area>> result = queryCityByRedis(param);
        return result.get("province");
    }

    /**
     * 查询所有城市
     *
     * @return
     */
    @Override
    public List<Area> queryAllCityByRedis() {
        Map<String, String> param = new HashMap<>();
        param.put("flag", "city");
        param.put("id", "*");
        Map<String, List<Area>> list = getStringListMap(param);
        return list.get("city");
    }

    @Override
    public Area getCityByCityId(String cityId) {
        Map<String, String> param = new HashMap<>();
        param.put("flag", "_city");
        param.put("id", cityId);
        Map<String, List<Area>> list = getStringListMap(param);
        if (list != null) {
            if (list.get("_city") != null) {
                return list.get("_city").get(0);
            }
        }
        return null;
    }

    /**
     * 查询省份
     *
     * @return
     */
    @Override
    public Area queryProvinceByCityId(String cityId) {
        Map<String, String> param = new HashMap<>();
        param.put("flag", "province");
        List<Area> result = getAllProvince();
        for (Area area : result) {
            if (area.getId().equals(cityId)) {
                return area;
            }
        }

        return null;
    }


    /**
     * 城市id查询区域
     *
     * @param cityId
     * @return
     */
    @Override
    public List<Area> getAreaByCityId(String cityId) {

        Map parem = new HashMap();
        parem.put("flag", "area");
        parem.put("id", cityId);
        Map areaList = queryAreaByRedis(parem);
        List<Area> result = (List<Area>) areaList.get("area");
        return result;
    }

    @Override
    public Area getDistByCityIdAndDistName(String cityId, String distName) {
        List<Area> areas = getAreaByCityId(cityId);
        for (Area area : areas) {
            if (area.getName().equals(distName)) {
                return area;
            }
        }
        return null;
    }

    private Map<String, List<Area>> getStringListMap(Map<String, String> parem) {
        //先在缓存中查询
        Map<String, List<Area>> areaMap = this.getArea(parem.get("flag"), parem.get("id"), parem.get("parentId"));
        //如果缓存查不到再调接口查
        if (areaMap == null) {
            parem.put("cityId", parem.get("id"));
            if (StringUtils.isNotEmpty(parem.get("parentId"))) {
                parem.put("areaId", parem.get("parentId"));
            }
            String data = HttpUtils.get(midlandConfig.getApiUrl(), parem);
            List<Area> areaList = MidlandHelper.getPojoList(data, Area.class);
            String parentId = "";
            if (areaList != null) {
                for (Area area : areaList) {
                    if (!parentId.equals(area.getParentId())) {
                        parentId = area.getParentId();
                        baseRedisTemplate.saveValue("province:" + area.getParentId(), area);
                    }
                    if (area.getParentName().equals("上海") || area.getParentName().equals("北京") ||
                            area.getParentName().equals("天津") || area.getParentName().equals("重庆") ||
                            area.getParentName().equals("香港") || area.getParentName().equals("澳门")) {
                        Area city = new Area();
                        city.setParentName(area.getParentName());
                        city.setParentId(area.getParentId());
                        city.setId(area.getParentId());
                        city.setName(area.getParentName());
                        baseRedisTemplate.saveValue("city:" + area.getParentId() + ":" + area.getParentId(), city, 6, TimeUnit.HOURS);
                        baseRedisTemplate.saveValue("area:" + area.getId() + ":" + area.getParentId(), area, 6, TimeUnit.HOURS);
                    } else {
                        baseRedisTemplate.saveValue("city:" + area.getId() + ":" + area.getParentId(), area, 6, TimeUnit.HOURS);
                    }
                }
            }

            areaMap = this.getArea(parem.get("flag"), parem.get("id"), parem.get("parentId"));
        }

        return areaMap;
    }


    /**
     * @param parem flag = province(省) , city(市) ,area(区), sheet(片区) id 或 parentId
     * @return
     */
    @Override
    public Map<String, List<Area>> queryAreaByRedis(Map<String, String> parem) {
        //先在缓存中查询
        Map<String, List<Area>> areaMap = this.getArea(parem.get("flag"), parem.get("id"), parem.get("parentId"));

        if (areaMap == null) {

            parem.put("cityId", parem.get("id"));
            if (StringUtils.isNotEmpty(parem.get("parentId"))) {
                parem.put("areaId", parem.get("parentId"));
            }
            String data = null;
            try {
                data = HttpUtils.get(midlandConfig.getAreaUrl(), parem);
            } catch (Exception e) {
                data = null;
            }
            List<Area> areaList = MidlandHelper.getPojoList(data, Area.class);
            String parentId = "";
            if (areaList != null) {
                for (Area area : areaList) {
                    if (StringUtils.isNotEmpty(parem.get("id")) && StringUtils.isEmpty(parem.get("parentId"))) {
                        baseRedisTemplate.saveValue("area:" + area.getId() + ":" + parem.get("id"), area, 12, TimeUnit.HOURS);
                    } else if (StringUtils.isNotEmpty(parem.get("id")) && StringUtils.isNotEmpty(parem.get("parentId"))) {
                        baseRedisTemplate.saveValue("sheet:" + area.getId() + ":" + area.getParentId(), area, 12, TimeUnit.HOURS);
                    }
                }
            }

            areaMap = this.getArea(parem.get("flag"), parem.get("id"), parem.get("parentId"));
        }

        return areaMap;
    }

    @Override
    public void batchUpdatePopular(List<Popular> popularList) throws Exception {
        try {
            log.debug("batchUpdatePopular  {}", popularList);
            int result = popularMapper.batchUpdate(popularList);
            if (result < 1) {
                throw new Exception("batchUpdatePopular失败");
            }
        } catch (Exception e) {
            log.error("batchUpdatePopular  {}", popularList, e);
            throw e;
        }

    }

    /**
     * 友情链接列表实现方法
     *
     * @param linkUrlManager
     * @return
     */
    @Override
    public List<LinkUrlManager> findLinkUrlList(LinkUrlManager linkUrlManager) {
        return linkUrlMapper.findLinkUrlManagerList(linkUrlManager);
    }

    @Override
    public LinkUrlManager findLinkUrlManager(LinkUrlManager linkUrlManager) {
        return linkUrlMapper.selectById(linkUrlManager.getId());
    }

    @Override
    public int updateLinkUrlManager(LinkUrlManager linkUrlManager) {
        return linkUrlMapper.updateById(linkUrlManager);
    }

    @Override
    public int insertLinkUrlManage(LinkUrlManager linkUrlManager) {
        return linkUrlMapper.insertLinkUrlManager(linkUrlManager);
    }

    /**
     * 上移
     **/
    @Override
    @Transactional
    public void shiftUp(LinkUrlManager category) throws Exception {
        try {
            log.debug("shiftUp {}", category);
            LinkUrlManager obj = linkUrlMapper.shiftUp(category);
            if (obj == null){
                return;
            }
            int nextOrderBy = obj.getOrderBy();
            int currOrderBy = category.getOrderBy();
            obj.setOrderBy(-999999999);
            linkUrlMapper.updateById(obj);
            category.setOrderBy(nextOrderBy);
            linkUrlMapper.updateById(category);
            obj.setOrderBy(currOrderBy);
            linkUrlMapper.updateById(obj);
        } catch (Exception e) {
            log.error("shiftUp {}", category, e);
            throw e;
        }
    }

    /**
     * 下移
     **/
    @Override
    @Transactional
    public void shiftDown(LinkUrlManager linkUrlManager) throws Exception {
        try {
            log.debug("shiftDown {}", linkUrlManager);
            LinkUrlManager obj = linkUrlMapper.shiftDown(linkUrlManager);
            if (obj == null){
                return;
            }
            int nextOrderBy = obj.getOrderBy();
            int currOrderBy = linkUrlManager.getOrderBy();
            obj.setOrderBy(-999999999);
            linkUrlMapper.updateById(obj);
            linkUrlManager.setOrderBy(nextOrderBy);
            linkUrlMapper.updateById(linkUrlManager);
            obj.setOrderBy(currOrderBy);
            linkUrlMapper.updateById(obj);
        } catch (Exception e) {
            log.error("shiftDown异常 {}", linkUrlManager, e);
            throw e;
        }
    }
    
    @Override
    public void batchUpdateLinkUrl(List<LinkUrlManager> linkUrlManagerList) throws Exception {
        try {
            log.debug("batchUpdateLinkUrl  {}", linkUrlManagerList);
            int result = linkUrlMapper.batchUpdate(linkUrlManagerList);
            if (result < 1) {
                throw new Exception("batchUpdateLinkUrl失败");
            }
        } catch (Exception e) {
            log.error("batchUpdateLinkUrl  {}", linkUrlManagerList, e);
            throw e;
        }
    }

    @Override
    public List<Banner> findBannerList(Banner banner) {
        return bannerMapper.findBannerList(banner);
    }

    @Override
    public Banner findBanner(Banner banner) {
        return bannerMapper.selectById(banner.getId());
    }

    @Override
    public int updateBanner(Banner banner) {
        return bannerMapper.updateById(banner);
    }

    @Override
    public int insertBanner(Banner banner) {
        return bannerMapper.insertBanner(banner);
    }

    @Override
    public void batchUpdateBanner(List<Banner> bannerList) throws Exception {
        try {
            log.debug("batchUpdateBanner  {}", bannerList);
            int result = bannerMapper.batchUpdate(bannerList);
            if (result < 1) {
                throw new Exception("batchUpdateBanner失败");
            }
        } catch (Exception e) {
            log.error("batchUpdateBanner  {}", bannerList, e);
            throw e;
        }

    }


    /**
     * 根据省份id获取下级市区
     *
     * @param flag = province(省) , city(市)
     * @param id
     * @return
     */
    private Map<String, List<Area>> getArea(String flag, String id, String parentId) {
        Map<String, List<Area>> result = new HashMap<>();
        List<Area> areaList = new LinkedList<>();
        Set<byte[]> keys = null;
        if ("province".equals(flag)) {
            keys = baseRedisTemplate.getKeysLike("province:*");
            if (keys == null || keys.size() <= 0) {
                return null;
            }
        } else if ("_city".equals(flag)) {
            keys = baseRedisTemplate.getKeysLike("city:" + id + ":*");
            if (keys == null || keys.size() <= 0) {
                return null;
            }
        } else if ("city".equals(flag)) {
            keys = baseRedisTemplate.getKeysLike("city:*:" + id);
            if (keys == null || keys.size() <= 0) {
                return null;
            }
        } else if ("area".equals(flag)) {
            keys = baseRedisTemplate.getKeysLike("area:*:" + id);
            if (keys == null || keys.size() <= 0) {
                return null;
            }
        } else if ("sheet".equals(flag)) {
            keys = baseRedisTemplate.getKeysLike("sheet:*:" + parentId);
            if (keys == null || keys.size() <= 0) {
                return null;
            }
        }

        for (byte[] a : keys) {
            String key = "";
            try {
                key = StringUtils.toString(a, null);
                Area area = (Area) baseRedisTemplate.getValueByKey(key);
                areaList.add(area);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        result.put(flag, areaList);

        return result;
    }

}