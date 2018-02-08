package com.midland.web.annocontroller;

import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.base.ServiceBaseFilter;
import com.midland.core.util.SmsUtil;
import com.midland.web.Contants.Contant;
import com.midland.web.api.ApiHelper;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ResultStatusUtils;
import com.midland.web.exception.UpdateException;
import com.midland.web.model.Appointment;
import com.midland.web.service.AppointmentService;
import com.midland.web.service.impl.PublicService;
import com.midland.web.util.MidlandHelper;
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
import java.util.concurrent.TimeUnit;

@RestController
@SuppressWarnings("all")
@RequestMapping("/appointment/")
public class AppointmentRestController extends ServiceBaseFilter {

    private Logger log = LoggerFactory.getLogger(AppointmentRestController.class);
    @Autowired
    private AppointmentService appointmentServiceImpl;
    @Autowired
    private ApiHelper apiHelper;
    @Autowired
    private PublicService publicServiceImpl;

    /**
     * 新增
     **/
    @RequestMapping("add")
    public Object addAppointment(@RequestBody Appointment obj) throws Exception {
        Result result = new Result();
        try {
            log.info("addAppointment {}", obj);
            obj.setIsDelete(Contant.isNotDelete);
            obj.setAppointmentTime(MidlandHelper.getCurrentTime());
            obj.setAppointSn(publicServiceImpl.getCode(Contant.APPOINT_SN_KEY,"A"));
            if(StringUtils.isNotEmpty(obj.getViewUrl())){
                if (!obj.getViewUrl().contains("http://")){
                    obj.setViewUrl("http://"+obj.getViewUrl());
                }
            }
            appointmentServiceImpl.insertAppointment(obj);
            String houseType =String.valueOf(obj.getHouseType());
            String houseId = String.valueOf(obj.getHouseId());
            Map mapj = new HashMap();
            mapj.put("hourseType",houseType);
            mapj.put("houseId",houseId);
            StringBuffer sb = new StringBuffer();
            sb.append(Contant.APPOINT_CACHE_KEY).append(":").append(obj.getUserId());
            publicServiceImpl.listRemove(sb.toString(),mapj);
            //发送给预约人的短信：模板id56848，内容：您好！您提交的看房日程由{1}电话{2}帮您带看，该经纪人会尽快联系您安排看房，请保持电话畅通，感谢！
            List<String> param = new ArrayList<>();
            param.add(obj.getNickName());
            param.add(obj.getCommunityName());
            apiHelper.smsSender(obj.getAgentPhone(),Contant.SMS_TEMPLATE_56846,param);
            //发送给经纪人的知府：模板56846，内容：您好{1}女士/先生忆通过官网约看{2}房源，现已分配由您跟进，请在24小时内与客户进行联系，联系方式请登录管理后台中查询
            List<String> param1 = new ArrayList<>();
            param1.add(obj.getCommunityName());
            apiHelper.smsSender(obj.getPhone(),Contant.SMS_TEMPLATE_56845,param1);
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("success");
        } catch (Exception e) {
            log.error("addAppointment异常 {}", obj, e);
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("service error");
        }
        return result;
    }

    /**
     * 查询
     **/
    @RequestMapping("get")
    public Object getAppointmentById(@RequestBody Map map) {
        Result result = new Result();
        try {
            Integer id = (Integer) map.get("id");
            log.info("getAppointmentById  {}", id);
            Appointment appointment = appointmentServiceImpl.selectAppointmentById(id);
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("success");
            result.setModel(appointment);
        } catch (Exception e) {
            log.error("getAppointment异常 {}", map, e);
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("service error");
        }
        return result;
    }

    /**
     * 更新
     **/
    @RequestMapping("update")
    public Object updateAppointmentById(@RequestBody Appointment obj) throws Exception {
        Result result = new Result();
        try {
            log.info("updateAppointmentById  {}", obj);
            appointmentServiceImpl.updateAppointmentById(obj);
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("success");
        } catch (Exception e) {
            log.error("updateAppointmentById  {}", obj, e);
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("service error");
        }
        return result;
    }

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @RequestMapping("list")
    public Object findAppointmentList(@RequestBody Appointment obj, HttpServletRequest request) {
        Result result = new Result();
        try {
            log.info("findAppointmentList  {}", obj);
            MidlandHelper.doPage(request);
            Page<Appointment> list = (Page<Appointment>) appointmentServiceImpl.findAppointmentList(obj);
            Paginator paginator = list.getPaginator();
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("success");
            result.setList(list);
            result.setPaginator(paginator);
        } catch (Exception e) {
            log.error("findAppointmentList  {}", obj, e);
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("service error");
        }
        return result;
    }

    /**
     * 清空预约记录
     * @param userId
     */
    @RequestMapping("clean")
    public Object cleanAppointment(@RequestBody Map map) {
        Result result = new Result();
        try {
            String userId = String.valueOf( map.get("userId"));
            Appointment obj = new Appointment();
            obj.setUserId(userId);
            obj.setIsDelete(Contant.isDelete);
            log.info("cleanAppointment  {}", obj);
            appointmentServiceImpl.updateAppointmentByWebUserId(obj);
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("success");
        } catch (Exception e) {
            log.error("cleanAppointment  {}", map, e);
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("service error");
        }
        return result;
    }
    /*
     * 清空预约记录
     * @param userId
     */
    @RequestMapping("delete")
    public Object deleteAppointment(@RequestBody Map map) {
        Result result = new Result();
        try {
            Integer id = (Integer)map.get("id");
            Appointment obj = new Appointment();
            obj.setId(id);
            obj.setIsDelete(Contant.isDelete);
            log.info("cleanAppointment  {}", obj);
            appointmentServiceImpl.updateAppointmentById(obj);
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
     * 取消预约
     * @param  userId
     */
    @RequestMapping("cancel")
    public Object cancelAppointment(@RequestBody Map map) {
        Result result = new Result();
        try {
            String userId = String.valueOf((Integer) map.get("userId"));
            Integer id = (Integer) map.get("id");
            Appointment obj = new Appointment();
            obj.setId(id);
            obj.setUserId(userId);
            obj.setStatus(4);//已取消，详见Midland.json的 appointment_status
            log.info("cancelAppointment  {}", obj);
            appointmentServiceImpl.cancelAppointmentByIdAndWebUserId(obj);
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("success");
        } catch (Exception e) {
            if (e instanceof UpdateException){
                log.error("cancelAppointment  {}", map, e);
                result.setCode(ResultStatusUtils.STATUS_CODE_203);
                result.setMsg("取消预约失败，参数有误");
            }else {
                log.error("cancelAppointment  {}", map, e);
                result.setCode(ResultStatusUtils.STATUS_CODE_203);
                result.setMsg("service error");
            }
        }
        return result;
    }



    /**
     * 预约记录添加缓存-类似购物车
     */
    @RequestMapping("addCache")
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
             StringBuffer sb = new StringBuffer();
             sb.append(Contant.APPOINT_CACHE_KEY).append(":").append(userId);
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
     * 预约记录缓存展示-类似购物车
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
             Map mapj = new HashMap();
             mapj.put("hourseType",houseType);
             StringBuffer sb = new StringBuffer();
             sb.append(Contant.APPOINT_CACHE_KEY).append(":").append(userId);
             result.setCode(ResultStatusUtils.STATUS_CODE_200);
             result.setList(publicServiceImpl.getList(sb.toString()));
             result.setMsg("success");
         } catch (Exception e) {
             log.error("cache",e);
             result.setCode(ResultStatusUtils.STATUS_CODE_203);
             result.setMsg("service error");
         }
         return result;
     }

    /**
     * 发送验证码
     * @param map,phone
     */
    @RequestMapping("send/code")
    public Object appointSendSMS(@RequestBody Map map) {
        String phone = (String) map.get("phone");
        String key = Contant.APPOINT_VCODE_KEY + phone;
        int codeEffective=3;
        String vCode = SmsUtil.createRandomVCode();//验证码
        return publicServiceImpl.sendCode(phone,Contant.SMS_TEMPLATE_54711,vCode,key,codeEffective,TimeUnit.MINUTES);
    }

    /**
     * 检验验证码
     * @param map phone,vcode
     * @return
     */
    @RequestMapping("checkVcode")
    public Object checkVcode_(@RequestBody Map map) {
        String phone = (String) map.get("phone");
        String vCode = (String) map.get("vcode");
        String key = Contant.APPOINT_VCODE_KEY + phone;
        return publicServiceImpl.codeCheck(phone,vCode,key);
    }

}
