package com.bluemobi.midland.web.controller;

import com.bluemobi.midland.web.commons.Result;
import com.bluemobi.midland.web.commons.core.util.ResultStatusUtils;
import com.midland.web.model.Appointment;
import com.midland.web.service.AppointmentService;
import com.midland.web.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/appoint")
public class AppointController {
    /*@Resource
    private AppointmentService appointmentServiceImpl;
    @RequestMapping("/rest")
    public Object addAppoint(@RequestBody Appointment obj){
        Result result = new Result();
        try {
            appointmentServiceImpl.insertAppointment(obj);
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("success");
        } catch (Exception e) {
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("success");
        }
        return  result;
    }*/
}
