package com.midland.web.service.impl;

import com.midland.core.redis.IBaseRedisTemplate;
import com.midland.core.util.AppSetting;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.UnsupportedEncodingException;
import java.util.*;

@Service
public class SettingServiceImpl implements SettingService {

    @Autowired
    private IBaseRedisTemplate baseRedisTemplate;

    @Autowired
    private PopularMapper popularMapper;

    @Autowired
    private LinkUrlManagerMapper linkUrlMapper;

    @Autowired
    private BannerMapper bannerMapper;

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
     * @param model
     */
    @Override
    public void getAllProvinceList(Model model) {
        Map<String,String> param = new HashMap<>();
        param.put("flag","province");
        Map<String, List<Area>> result = queryCityByRedis(param);
        List<Area> provinceList = result.get("province");
        model.addAttribute("provinceList",provinceList);
    }
    /**
     * 查询所有省份
     */
    @Override
    public List<Area> getAllProvince() {
        Map<String,String> param = new HashMap<>();
        param.put("flag","province");
        Map<String, List<Area>> result = queryCityByRedis(param);
        return result.get("province");
    }
    
    /**
     * 查询所有城市
     * @return
     */
    @Override
    public List<Area> queryAllCityByRedis() {
        Map<String,String> param = new HashMap<>();
        param.put("flag","city");
        param.put("id","*");
        Map<String, List<Area>> list=getStringListMap(param);
        return list.get("city");
    }
    @Override
    public Area getCityByCityId(String cityId) {
        Map<String,String> param = new HashMap<>();
        param.put("flag","_city");
        param.put("id",cityId);
        Map<String, List<Area>> list=getStringListMap(param);
        if (list != null){
            if (list.get("_city")!=null){
                return list.get("_city").get(0);
            }
        }
        return null;
    }
    
    /**
     * 查询省份
     * @return
     */
    @Override
    public Area queryProvinceByCityId(String cityId) {
        Map<String,String> param = new HashMap<>();
        param.put("flag","province");
        List<Area> result = getAllProvince();
        for (Area area : result){
            if (area.getId().equals(cityId)){
                return area;
            }
        }
        
        return null;
    }
    
    
    
    @Override
    public List<Area> getAreaByCityId(String cityId){
        Map param = new HashMap();
        param.put("flag","area");
        param.put("id",cityId);
        Map result = queryCityByRedis(param);
        return (List<Area>)result.get("area");
    }
    
    @Override
    public Area getDistByCityIdAndDistName(String cityId, String distName){
        List<Area> areas = getAreaByCityId(cityId);
        for (Area area:areas){
            if (area.getName().equals(distName)){
                return area;
            }
        }
        return null;
    }
    @Override
    public Area getDistByCityIdAndDistId(String cityId, String distId){
        List<Area> areas = getAreaByCityId(cityId);
        for (Area area:areas){
            if (area.getId().equals(distId)){
                return area;
            }
        }
        return null;
    }
    
    private Map<String, List<Area>> getStringListMap(Map<String, String> parem) {
        //先在缓存中查询
        Map<String, List<Area>> areaMap = this.getArea(parem.get("flag"),parem.get("id"),parem.get("parentId"));
        //如果缓存查不到再调接口查
        if (areaMap == null){
            parem.put("cityId",parem.get("id"));
            if(StringUtils.isNotEmpty(parem.get("parentId"))){
                parem.put("areaId",parem.get("parentId"));
            }
            String data = HttpUtils.get(AppSetting.getAppSetting("APIURL"), parem);
            List<Area> areaList = MidlandHelper.getPojoList(data, Area.class);
            String parentId = "";
            if (areaList !=null) {
                for (Area area : areaList) {
                    if (!parentId.equals(area.getParentId())) {
                        parentId = area.getParentId();
                        baseRedisTemplate.saveValue("province:" + area.getParentId(), area);
                    }
                    baseRedisTemplate.saveValue("city:" + area.getId() + ":" + area.getParentId(), area);
                }
            }

            areaMap = this.getArea(parem.get("flag"),parem.get("id"),parem.get("parentId"));
        }
        
        return areaMap;
    }
   
    
    /**
     *
     * @param parem flag = province(省) , city(市) ,area(区), sheet(片区) id 或 parentId
     * @return
     */
    @Override
    public Map<String, List<Area>> queryAreaByRedis(Map<String, String> parem) {
        //先在缓存中查询
        Map<String, List<Area>> areaMap = this.getArea(parem.get("flag"),parem.get("id"),parem.get("parentId"));

        if (areaMap == null){

            parem.put("cityId",parem.get("id"));
            if(StringUtils.isNotEmpty(parem.get("parentId"))){
                parem.put("areaId",parem.get("parentId"));
            }
            String data = null;
            try{
                data = HttpUtils.get(AppSetting.getAppSetting("AREAURL"), parem);
            }catch (Exception e){
                data = null;
            }
            List<Area> areaList = MidlandHelper.getPojoList(data, Area.class);
            String parentId = "";
            if (areaList!=null) {
                for (Area area : areaList) {
                    if (StringUtils.isNotEmpty(parem.get("id")) && StringUtils.isEmpty(parem.get("parentId"))) {
                        baseRedisTemplate.saveValue("area:" + area.getId() + ":" + parem.get("id"), area);
                    } else if (StringUtils.isNotEmpty(parem.get("id")) && StringUtils.isNotEmpty(parem.get("parentId"))) {
                        baseRedisTemplate.saveValue("sheet:" + area.getId() + ":" + area.getParentId(), area);
                    }
                }
            }

            areaMap = this.getArea(parem.get("flag"),parem.get("id"),parem.get("parentId"));
        }

        return areaMap;
    }

    /**
     * 友情链接列表实现方法
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


    /**
     * 根据省份id获取下级市区
     * @param flag = province(省) , city(市)
     * @param id
     * @return
     */
    private Map<String, List<Area>> getArea(String flag, String id,String parentId) {
        Map<String, List<Area>> result = new HashMap<>();
        List<Area> areaList = new LinkedList<>();
        Set<byte[]> keys = null;
        if ("province".equals(flag)) {
             keys = baseRedisTemplate.getKeysLike("province:*");
             if(keys==null||keys.size()<=0){
                 return null;
             }
        }else if ("_city".equals(flag)){
            keys = baseRedisTemplate.getKeysLike("city:"+id+":*");
            if(keys==null||keys.size()<=0){
                return null;
            }
        }
        
        else if ("city".equals(flag)){
             keys = baseRedisTemplate.getKeysLike("city:*:"+id);
            if(keys==null||keys.size()<=0){
                return null;
            }
        }else if("area".equals(flag)){
            keys = baseRedisTemplate.getKeysLike("area:*:"+id);
            if(keys==null||keys.size()<=0){
                return null;
            }
        }else if("sheet".equals(flag)){
            keys = baseRedisTemplate.getKeysLike("sheet:*:"+parentId);
            if(keys==null||keys.size()<=0){
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
        result.put(flag,areaList);

        return  result;
    }



}