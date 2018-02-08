package com.midland.controller;

import com.github.nobodxbodon.zhconverter.简繁转换类;
import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.base.BaseFilter;
import com.midland.web.Contants.Contant;
import com.midland.web.model.HotHand;
import com.midland.web.model.user.User;
import com.midland.web.service.HotHandService;
import com.midland.web.service.JdbcService;
import com.midland.web.service.SettingService;
import com.midland.web.util.JsonMapReader;
import com.midland.web.util.MidlandHelper;
import com.midland.web.util.ParamObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@SuppressWarnings("all")
@RequestMapping("/hotHand/")
public class HotHandController extends BaseFilter {

    private Logger log = LoggerFactory.getLogger(HotHandController.class);
    @Autowired
    private HotHandService hotHandServiceImpl;
    @Autowired
    private SettingService settingServiceImpl;
    @Autowired
    private JdbcService jdbcService;

    /**
     *
     **/
    @RequestMapping("index")
    public String hotHandIndex(HotHand hotHand, Model model, HttpServletRequest request) throws Exception {
        settingServiceImpl.getAllProvinceList(model);
        User user = MidlandHelper.getCurrentUser(request);
        model.addAttribute("isSuper", user.getIsSuper());
        List<ParamObject> obj = JsonMapReader.getMap("is_delete");
        model.addAttribute("isDeletes", obj);
        return "hotHand/hotHandIndex";
    }

    /**
     *
     **/
    @RequestMapping("to_add")
    public String toAddHotHand(HotHand hotHand, Model model) throws Exception {
        settingServiceImpl.getAllProvinceList(model);
        List<ParamObject> bo = JsonMapReader.getMap("decoration");
        model.addAttribute("decorations", bo);
        return "hotHand/addHotHand";
    }

    /**
     * 新增
     **/
    @RequestMapping("add")
    @ResponseBody
    public Object addHotHand(HotHand hotHand) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            log.info("addHotHand {}", hotHand);
            简繁转换类 con=简繁转换类.取实例(简繁转换类.目标.简体);
            if (StringUtils.isEmpty(hotHand.getBuildingType())){
                hotHand.setBuildingType( con.转换(hotHand.getBuildingType()));
            }
            hotHand.setCreateTime(MidlandHelper.getCurrentTime());
            hotHandServiceImpl.insertHotHand(hotHand);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("addHotHand异常 {}", hotHand, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     * 查询
     **/
    @RequestMapping("get_hotHand")
    public String getHotHandById(Integer id, Model model) {
        log.info("getHotHandById  {}", id);
        HotHand result = hotHandServiceImpl.selectHotHandById(id);
        model.addAttribute("item", result);
        return "hotHand/updateHotHand";
    }

    /**
     * 删除
     **/
    @RequestMapping("delete")
    @ResponseBody
    public Object deleteHotHandById(Integer id) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            log.info("deleteHotHandById  {}", id);
            hotHandServiceImpl.deleteHotHandById(id);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("deleteHotHandById  {}", id, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     *
     **/
    @RequestMapping("to_update")
    public String toUpdateHotHand(Integer id, Model model) throws Exception {
        HotHand result = hotHandServiceImpl.selectHotHandById(id);
        if (result != null) {
            if (StringUtils.isNotEmpty(result.getImgUrl())) {
                String[] array = result.getImgUrl().split("\\|\\|");
                List<String> imglist = Arrays.asList(array);
                result.setImgUrlList(imglist);
            }
        }
        settingServiceImpl.getAllProvinceList(model);
        List<ParamObject> bo = JsonMapReader.getMap("decoration");
        model.addAttribute("decorations", bo);
        model.addAttribute("item", result);
        return "hotHand/updateHotHand";
    }

    /**
     * 更新
     **/
    @RequestMapping("update")
    @ResponseBody
    public Object updateHotHandById(HotHand hotHand) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            简繁转换类 con=简繁转换类.取实例(简繁转换类.目标.简体);
            if (StringUtils.isEmpty(hotHand.getBuildingType())){
                hotHand.setBuildingType( con.转换(hotHand.getBuildingType()));
            }
            log.info("updateHotHandById  {}", hotHand);
            hotHandServiceImpl.updateHotHandById(hotHand);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("updateHotHandById  {}", hotHand, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @RequestMapping("list")
    public String findHotHandList(HotHand hotHand, Model model, HttpServletRequest request) {
        try {
            log.info("findHotHandList  {}", hotHand);
            简繁转换类 con=简繁转换类.取实例(简繁转换类.目标.简体);
            if (StringUtils.isNotEmpty(hotHand.getBuildingType())){
                hotHand.setBuildingType( con.转换(hotHand.getBuildingType()));
            }
            MidlandHelper.doPage(request);
            User user = MidlandHelper.getCurrentUser(request);
            if (!Contant.isSuper.equals(user.getIsSuper())) {//不是超级管理员，只能看属性城市的相关信息
                hotHand.setCityId(user.getCityId());
            }
            Page<HotHand> result = (Page<HotHand>) hotHandServiceImpl.findHotHandList(hotHand);
            Paginator paginator = result.getPaginator();
            List<ParamObject> bo = JsonMapReader.getMap("decoration");
            model.addAttribute("decorations", bo);
            model.addAttribute("paginator", paginator);
            model.addAttribute("items", result);
        } catch (Exception e) {
            log.error("findHotHandList  {}", hotHand, e);
            model.addAttribute("paginator", null);
            model.addAttribute("items", null);
        }
        return "hotHand/hotHandList";
    }

    @RequestMapping("sort")
    @ResponseBody
    public Map listDesc(HotHand hotHand, int sort, Model model, HttpServletRequest request) throws Exception {
        Map map = new HashMap();
        try {
            if (sort == 1) {
                hotHandServiceImpl.shiftUp(hotHand);
            } else {
                hotHandServiceImpl.shiftDown(hotHand);
            }

            map.put("state", 0);
        } catch (Exception e) {
            log.error("", e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     * 重新分配经纪人，把经纪人更新到预约记录里
     *
     * @param record
     * @return
     */
    @RequestMapping("/reset_agent")
    @ResponseBody
    public Object resetAgent(HotHand record) {
        Map map = new HashMap();
        try {
            hotHandServiceImpl.updateHotHandById(record);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("resetAgent : {}", record, e);
            map.put("state", -1);
        }
        return map;
    }

}
