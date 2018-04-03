package com.midland.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.base.BaseFilter;
import com.midland.web.model.Area;
import com.midland.web.model.QrCode;
import com.midland.web.model.user.User;
import com.midland.web.service.QrCodeService;
import com.midland.web.service.SettingService;
import com.midland.web.util.MidlandHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@SuppressWarnings("all")
@RequestMapping("/qrCode/")
public class QrCodeController extends BaseFilter {

    private Logger log = LoggerFactory.getLogger(QrCodeController.class);
    @Autowired
    private QrCodeService qrCodeServiceImpl;
    @Autowired
    private SettingService settingService;

    /**
     *
     **/
    @RequestMapping("index")
    public String qrCodeIndex(QrCode qrCode, Model model, HttpServletRequest request) throws Exception {
        /*Map<String,String> parem = new HashMap<>();
        parem.put("flag","city");
		parem.put("id","*");
		Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
		List<Area> cityList = cityMap.get("city");
		model.addAttribute("cityList",cityList);*/
        settingService.getAllProvinceList(model);
        User user = MidlandHelper.getCurrentUser(request);
        if (user.getIsSuper() == null) {
            model.addAttribute("cityId", user.getCityId());
            model.addAttribute("cityName", user.getCityName());
        }
        model.addAttribute("isSuper", user.getIsSuper());
        return "qrCode/qrCodeIndex";
    }

    /**
     *
     **/
    @RequestMapping("to_add")
    public String toAddQrCode(QrCode qrCode, Model model, HttpServletRequest request) throws Exception {
        Map<String, String> parem = new HashMap<>();
        parem.put("flag", "city");
        parem.put("id", "*");
        Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
        List<Area> cityList=new ArrayList<>();
        if (cityMap !=null){
            cityList = cityMap.get("city");
        }
        model.addAttribute("cityList", cityList);
        User user = MidlandHelper.getCurrentUser(request);
        if (user.getIsSuper() == null) {
            model.addAttribute("cityId", user.getCityId());
            model.addAttribute("cityName", user.getCityName());
        }
        model.addAttribute("isSuper", user.getIsSuper());
        return "qrCode/addQrCode";
    }

    /**
     * 新增
     **/
    @RequestMapping("add")
    @ResponseBody
    public Object addQrCode(QrCode qrCode) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            log.debug("addQrCode {}", qrCode);
            qrCodeServiceImpl.insertQrCode(qrCode);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("addQrCode异常 {}", qrCode, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     * 查询
     **/
    @RequestMapping("get_qrCode")
    public String getQrCodeById(Integer id, Model model) {
        log.debug("getQrCodeById  {}", id);
        QrCode result = qrCodeServiceImpl.selectQrCodeById(id);
        model.addAttribute("item", result);
        return "qrCode/updateQrCode";
    }

    /**
     * 删除
     **/
    @RequestMapping("delete")
    @ResponseBody
    public Object deleteQrCodeById(Integer id) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            log.debug("deleteQrCodeById  {}", id);
            qrCodeServiceImpl.deleteQrCodeById(id);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("deleteQrCodeById  {}", id, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     *
     **/
    @RequestMapping("to_update")
    public String toUpdateQrCode(Integer id, Model model, HttpServletRequest request) throws Exception {
        Map<String, String> parem = new HashMap<>();
        parem.put("flag", "city");
        parem.put("id", "*");
        Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
        List<Area> cityList=new ArrayList<>();
        if (cityMap !=null){
            cityList = cityMap.get("city");
        }
        QrCode result = qrCodeServiceImpl.selectQrCodeById(id);
        model.addAttribute("cityList", cityList);
        model.addAttribute("item", result);
        User user = MidlandHelper.getCurrentUser(request);
        if (user.getIsSuper() == null) {
            model.addAttribute("cityId", user.getCityId());
            model.addAttribute("cityName", user.getCityName());
        }
        model.addAttribute("isSuper", user.getIsSuper());
        return "qrCode/updateQrCode";
    }

    /**
     * 更新
     **/
    @RequestMapping("update")
    @ResponseBody
    public Object updateQrCodeById(QrCode qrCode) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            log.debug("updateQrCodeById  {}", qrCode);
            qrCodeServiceImpl.updateQrCodeById(qrCode);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("updateQrCodeById  {}", qrCode, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @RequestMapping("list")
    public String findQrCodeList(QrCode qrCode, Model model, HttpServletRequest request) {
        try {
            log.debug("findQrCodeList  {}", qrCode);
            MidlandHelper.doPage(request);
            Page<QrCode> result = (Page<QrCode>) qrCodeServiceImpl.findQrCodeList(qrCode);
            Paginator paginator = result.getPaginator();
            model.addAttribute("paginator", paginator);
            model.addAttribute("items", result);
        } catch (Exception e) {
            log.error("findQrCodeList  {}", qrCode, e);
            model.addAttribute("paginator", null);
            model.addAttribute("items", null);
        }
        return "qrCode/qrCodeList";
    }

    /**
     * 批量更新
     **/
    @RequestMapping("batchUpdate")
    @ResponseBody
    public Object batchUpdate(String ids, QrCode qrCode) throws Exception {
        List<QrCode> commentList = new ArrayList<>();
        Arrays.asList(ids.split(",", -1)).forEach((id) -> {
                    QrCode comment1 = new QrCode();
                    comment1.setId(Integer.valueOf(id));
                    comment1.setIsDelete(qrCode.getIsDelete());
                    commentList.add(comment1);
                }
        );

        Map<String, Object> map = new HashMap<>();
        try {
            log.debug("updateCategoryById  {}", commentList);
            qrCodeServiceImpl.batchUpdate(commentList);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("updateCategoryById  {}", commentList, e);
            map.put("state", -1);
        }
        return map;
    }
}
