package com.midland.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/appoint")
public class WebAppointController {
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
