package com.midland.web.annocontroller;

import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.base.BaseFilter;
import com.midland.core.util.SmsUtil;
import com.midland.web.Contants.Contant;
import com.midland.web.api.ApiHelper;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ResultStatusUtils;
import com.midland.web.model.Appointment;
import com.midland.web.service.AppointmentService;
import com.midland.web.service.impl.PublicService;
import com.midland.web.util.MidlandHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@SuppressWarnings("all")
@RequestMapping("/appointment/")
public class AppointmentRestController extends BaseFilter {

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
            appointmentServiceImpl.insertAppointment(obj);
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
     * @param webUserId
     */
    @RequestMapping("clean")
    public Object cleanAppointment(@RequestBody Map map) {
        Result result = new Result();
        try {
            Integer webUserId = (Integer) map.get("webUserId");
            Appointment obj = new Appointment();
            obj.setWebUserId(webUserId);
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

    /**
     * 取消预约
     * @param  webUserId
     */
    @RequestMapping("cancel")
    public Object cancelAppointment(@RequestBody Map map) {
        Result result = new Result();
        try {
            Integer webUserId = (Integer) map.get("webUserId");
            Appointment obj = new Appointment();
            obj.setWebUserId(webUserId);
            obj.setStatus(4);//已取消，详见Midland.json的 appointment_status
            log.info("cancelAppointment  {}", obj);
            appointmentServiceImpl.updateAppointmentByWebUserId(obj);
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
     * 发送验证码
     * @param map,phone
     */
    @RequestMapping("send/code")
    public Object appointSendSMS(@RequestBody Map map) {
        String phone = (String) map.get("phone");
        String key = Contant.APPOINT_VCODE_KEY + phone;
        int codeEffective=3;
        String vCode = SmsUtil.createRandomVCode();//验证码
        int tpId=54711;
        return publicServiceImpl.sendCode(phone,tpId,vCode,key,codeEffective,TimeUnit.MINUTES);
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
