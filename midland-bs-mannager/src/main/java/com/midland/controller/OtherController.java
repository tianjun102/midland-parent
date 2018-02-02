package com.midland.controller;

import com.midland.base.BaseFilter;
import com.midland.web.model.Footer;
import com.midland.web.service.FooterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 'ms.x' on 2017/8/7.
 */
@Controller
public class OtherController extends BaseFilter {
    @Autowired
    private FooterService footerServiceImpl;

    @RequestMapping("/privacy/index")
    public String privacyIndex(HttpServletRequest request, Model model) {
        Footer footer = footerServiceImpl.getFooter();
        model.addAttribute("item", footer);
        return "footer/privacy";
    }

    @RequestMapping("/elite/index")
    public String eliteIndex(HttpServletRequest request, Model model, String flag) {
        Footer footer = footerServiceImpl.getFooter();
        model.addAttribute("item", footer);
        model.addAttribute("flag", flag);
        return "eliteClub/eliteClub";
    }

    @RequestMapping("/disclaimer/index")
    public String disclaimerIndex(HttpServletRequest request, Model model) {
        Footer footer = footerServiceImpl.getFooter();
        model.addAttribute("item", footer);
        return "footer/disclaimer";
    }

    @RequestMapping("/registrationProtocol/index")
    public String registrationProtocolIndex(HttpServletRequest request, Model model) {
        Footer footer = footerServiceImpl.getFooter();
        model.addAttribute("item", footer);
        return "footer/registrationProtocol";
    }

    @RequestMapping("/serviceArea/index")
    public String serviceAreaIndex(HttpServletRequest request, Model model) {
        Footer footer = footerServiceImpl.getFooter();
        model.addAttribute("item", footer);
        return "footer/serviceArea";
    }

    @RequestMapping("/aboutUs/index")
    public String aboutUsIndex(HttpServletRequest request, Model model) {
        Footer footer = footerServiceImpl.getFooter();
        model.addAttribute("item", footer);
        return "footer/aboutUs";
    }

    @RequestMapping("/contactUs/index")
    public String contactUsIndex(HttpServletRequest request, Model model) {
        Footer footer = footerServiceImpl.getFooter();
        model.addAttribute("item", footer);
        return "footer/contactUs";
    }


    @RequestMapping("/tradingProcess/index")
    public String tradingProcessIndex(HttpServletRequest request, Model model) {
        Footer footer = footerServiceImpl.getFooter();
        model.addAttribute("item", footer);
        return "footer/tradingProcess";
    }


}
