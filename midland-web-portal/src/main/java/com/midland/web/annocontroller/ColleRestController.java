package com.midland.web.annocontroller;

import com.midland.base.BaseFilter;
import com.midland.web.Contants.Contant;
import com.midland.web.api.ApiHelper;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ResultStatusUtils;
import com.midland.web.service.AppointmentService;
import com.midland.web.service.impl.PublicService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@SuppressWarnings("all")
@RequestMapping("/colle/")
public class ColleRestController extends BaseFilter {

    private Logger log = LoggerFactory.getLogger(ColleRestController.class);
    @Autowired
    private AppointmentService appointmentServiceImpl;
    @Autowired
    private ApiHelper apiHelper;
    @Autowired
    private PublicService publicServiceImpl;


    /**
     * 清空收藏记录
     * @param webUserId
     */
    @RequestMapping("clean")
    public Object cleanAppointment(@RequestBody Map map) {
        Result result = new Result();
        try {
            String userId = String.valueOf(map.get("userId"));
            String houseType =String.valueOf(map.get("houseType"));
            String houseId = String.valueOf( map.get("houseId"));
            StringBuffer sb = new StringBuffer();
            sb.append(Contant.COLLE_CACHE_KEY).append(":"+houseType+":").append(userId);
            publicServiceImpl.removeAll(sb.toString());
            log.info("cancelAppointment  {}", map);
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("success");
        } catch (Exception e) {
            log.error("cleanAppointment  {}", map, e);
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("service error");
        }
        return result;
    }

    /**
     * 取消收藏
     * @param  webUserId
     */
    @RequestMapping("cancel")
    public Object cancelAppointment(@RequestBody Map map) {
        Result result = new Result();
        try {
            String userId = String.valueOf(map.get("userId"));
            String houseType =String.valueOf(map.get("houseType"));
            String houseId = String.valueOf( map.get("houseId"));
            Map mapj = new HashMap();
            mapj.put("hourseType",houseType);
            mapj.put("houseId",houseId);
            mapj.put("userId",userId);
            StringBuffer sb = new StringBuffer();
            sb.append(Contant.COLLE_CACHE_KEY).append(":"+houseType+":").append(userId);
            publicServiceImpl.listRemove(sb.toString(),mapj);
            log.info("cancelAppointment  {}", map);
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("success");
        } catch (Exception e) {
            log.error("cancelAppointment  {}", map, e);
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("service error");
        }
        return result;
    }



    /**
     * 添加收藏
     * houseType=1，二手房；houseType=2租房；houseType=3新房；houseType=4写字楼；houseType=5商铺；houseType=6小区
     */
    @RequestMapping("add")
     public Object cache(@RequestBody Map map,HttpServletRequest request){
         Result result = new Result();
         try {
             String userId = String.valueOf(map.get("userId"));
             String houseType =String.valueOf(map.get("houseType"));
             String houseId = String.valueOf( map.get("houseId"));
             if (StringUtils.isEmpty(userId)||StringUtils.isEmpty(houseType)||StringUtils.isEmpty(houseId)){
                 throw new IllegalArgumentException("参数不能为空");
             }
             Map mapj = new HashMap();
             mapj.put("hourseType",houseType);
             mapj.put("houseId",houseId);
             mapj.put("userId",userId);
             StringBuffer sb = new StringBuffer();
             sb.append(Contant.COLLE_CACHE_KEY).append(":"+houseType+":").append(userId);
             publicServiceImpl.listLeftPush(sb.toString(),mapj);
             result.setCode(ResultStatusUtils.STATUS_CODE_200);
             result.setMsg("success");
         } catch (Exception e) {
             log.error("cache",e);
             result.setCode(ResultStatusUtils.STATUS_CODE_203);
             result.setMsg("service error");
         }
         return result;
     }
 /**
     * 我的收藏列表
     */
    @RequestMapping("cacheList")
     public Object cacheList(@RequestBody Map map,HttpServletRequest request){
         Result result = new Result();
         try {

             String userId = String.valueOf(map.get("userId"));
             String houseType =String.valueOf(map.get("houseType"));
             if (StringUtils.isEmpty(userId)){
                 throw new IllegalArgumentException("参数不能为空");
             }
             if (StringUtils.isEmpty(houseType)||houseType.equals("null")){
                 List list = new ArrayList();
                 for (int i=1;i<7;i++){
                     StringBuffer sb = new StringBuffer();
                     houseType=String.valueOf(i);
                     sb.append(Contant.COLLE_CACHE_KEY).append(":"+houseType+":").append(userId);
                     List listTemp =publicServiceImpl.getList(sb.toString());
                     list.addAll(listTemp);
                 }
                 result.setCode(ResultStatusUtils.STATUS_CODE_200);
                 result.setList(list);
                 result.setMsg("success");
             }else {
                 StringBuffer sb = new StringBuffer();
                 sb.append(Contant.COLLE_CACHE_KEY).append(":" + houseType + ":").append(userId);
                 result.setCode(ResultStatusUtils.STATUS_CODE_200);
                 result.setList(publicServiceImpl.getList(sb.toString()));
                 result.setMsg("success");
             }
         } catch (Exception e) {
             log.error("cache",e);
             result.setCode(ResultStatusUtils.STATUS_CODE_203);
             result.setMsg("service error");
         }
         return result;
     }


}
