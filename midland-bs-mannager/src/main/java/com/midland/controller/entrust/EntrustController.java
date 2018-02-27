package com.midland.controller.entrust;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.Paginator;
import com.midland.base.ServiceBaseFilter;
import com.midland.config.MidlandConfig;
import com.midland.web.Contants.Contant;
import com.midland.web.api.ApiHelper;
import com.midland.web.enums.ContextEnums;
import com.midland.web.model.CenterMsg;
import com.midland.web.model.Entrust;
import com.midland.web.model.EntrustLog;
import com.midland.web.model.ExportModel;
import com.midland.web.model.user.User;
import com.midland.web.service.CenterMsgService;
import com.midland.web.service.EntrustLogService;
import com.midland.web.service.EntrustService;
import com.midland.web.service.SettingService;
import com.midland.web.util.JsonMapReader;
import com.midland.web.util.MidlandHelper;
import com.midland.web.util.ParamObject;
import com.midland.web.util.PoiExcelExport;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 'ms.x' on 2017/8/1.
 */
@Controller
@RequestMapping("/entrust")
public class EntrustController extends ServiceBaseFilter {
    @Autowired
    private EntrustService entrustServiceImpl;
    @Autowired
    private EntrustLogService entrustLogServiceImpl;
    @Autowired
    private MidlandConfig midlandConfig;
    @Autowired
    private SettingService settingService;
    @Autowired
    private ApiHelper apiHelper;
    @Autowired
    private CenterMsgService centerMsgServiceImpl;

    Logger logger = LoggerFactory.getLogger(EntrustController.class);

    /**
     * 买房
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/buy_index")
    public String showEntrustBuyIndex(HttpServletRequest request, Model model) {
        getSelectParam(model);
        return "/entrustBuy/entrustIndex";
    }

    /**
     * 卖房
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/sale_index")
    public String showEntrustSaleIndex(HttpServletRequest request, Model model) {
        getSelectParam(model);
        return "/entrustSale/entrustIndex";
    }

    /**
     * 出租
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/rentOut_index")
    public String showRentOutIndex(HttpServletRequest request, Model model) {
        getSelectParam(model);
        return "/entrustRentOut/entrustIndex";
    }

    /**
     * 租房
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/rentIn_index")
    public String showRentInIndex(HttpServletRequest request, Model model) {
        getSelectParam(model);
        return "/entrustRentIn/entrustIndex";
    }

    /**
     * 评估
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/evaluate_index")
    public String showEntrustEvaluateIndex(HttpServletRequest request, Model model) {
        getSelectParam(model);
        return "/entrustEvaluate/entrustIndex";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object deleteByPrimaryKey(Integer id) {
        Map map = new HashMap<>();
        try {
            entrustServiceImpl.deleteEntrustById(id);
            map.put("state", 0);
        } catch (Exception e) {
            logger.error("deleteByPrimaryKey id={}", id, e);
            map.put("state", -1);
        }
        return map;
    }

    @RequestMapping("/to_add")
    public String toAdd(HttpServletRequest request, Model model) {
        getSelectParam(model);
        settingService.getAllProvinceList(model);
        return "entrust/addEntrust";
    }

    @RequestMapping("/add")
    @ResponseBody
    public Object addEntrust(Entrust record) {
        Map map = new HashMap();
        try {
            record.setAssignedTime(MidlandHelper.getCurrentTime());
            entrustServiceImpl.insertEntrust(record);

            map.put("state", 0);
            return map;
        } catch (Exception e) {
            logger.error("addEntrust {}", record, e);
            map.put("state", -1);
            return map;
        }
    }

    @RequestMapping("/get")
    public Entrust selectByPrimaryKey(Integer id) {

        return entrustServiceImpl.selectEntrustById(id);
    }

    private void getSelectParam(Model model) {
        List<ParamObject> paramObjects = JsonMapReader.getMap("entrust_type");
        ;
        model.addAttribute("sellRents", paramObjects);
        List<ParamObject> paramObjects1 = JsonMapReader.getMap("appointment_status");
        model.addAttribute("statusList", paramObjects1);
        List<ParamObject> paramObjects2 = JsonMapReader.getMap("source");
        model.addAttribute("sources", paramObjects2);
        List<ParamObject> paramObjects3 = JsonMapReader.getMap("appointment_houseType");
        model.addAttribute("houses", paramObjects3);
        List<ParamObject> paramObjects4 = JsonMapReader.getMap("entrust_status");
        model.addAttribute("statusList", paramObjects4);
        List<ParamObject> paramObjects5 = JsonMapReader.getMap("decoration");
        model.addAttribute("decorations", paramObjects5);

    }


    @RequestMapping("/page")
    public String entrustPage(Model model, Entrust record, String pageNo, String pageSize, HttpServletRequest request) throws Exception {
        if (pageNo == null || pageNo.equals("")) {
            pageNo = ContextEnums.PAGENO;
        }
        if (pageSize == null || pageSize.equals("")) {
            pageSize = ContextEnums.PAGESIZE;
        }
        User user = MidlandHelper.getCurrentUser(request);
        record.setCityId(user.getCityId());
        PageHelper.startPage(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
        Page<Entrust> result = (Page<Entrust>) entrustServiceImpl.findEntrustList(record);
        Paginator paginator = result.getPaginator();
        getSelectParam(model);
        model.addAttribute("paginator", paginator);
        model.addAttribute("entrusts", result);
        return "entrust/entrustList";
    }


    @RequestMapping("/to_update")
    public String toUpdateAppointment(int entrustId, Model model) {
        Entrust entrust = entrustServiceImpl.selectEntrustById(entrustId);
        List<EntrustLog> entrustLogs = entrustLogServiceImpl.selectEntrustLogByEntrustId(entrustId);

        getSelectParam(model);
        model.addAttribute("entrust", entrust);
        model.addAttribute("entrustLogs", entrustLogs);
        return "entrust/updateEntrust";
    }


    @RequestMapping("/update")
    @ResponseBody
    public Object updateByPrimaryKeySelective(Entrust entrust, String dealRemark, HttpServletRequest request) {
        Map map = new HashMap();
        try {
            String oldStatus = request.getParameter("oldStatus");
            StringBuilder msg = new StringBuilder();
            List<ParamObject> mapre = JsonMapReader.getMap("decoration");
            ParamObject object = JsonMapReader.getObject("decoration", entrust.getRenovation());
            msg.append("你意向小区").append(entrust.getCommunityName()).append(",").append(entrust.getLayout()).append(",").append("意向面积").append(entrust.getMeasure()).append(",").append(object.getName()).append("的买房委托").append(entrust.getAgentName()).append("经纪人已受理，前去");
            if (StringUtils.isNotEmpty(oldStatus) && Integer.valueOf(oldStatus) == entrust.getStatus()) {
                CenterMsg centermsg = new CenterMsg();
                centermsg.setType(4);
                centermsg.setJumpId(entrust.getId().toString());
                centermsg.setTitle(Contant.APPOINT_TITLE.replace("||", entrust.getAgentName() == null ? "" : entrust.getAgentName()));
                centermsg.setMsg(msg.toString());
                centerMsgServiceImpl.insertCenterMsg(centermsg);
            }
            if (entrust.getStatus() != null && 1 != entrust.getStatus()) {
                //如果委托状态不是已分配，隐藏重新分配按钮
                entrust.setResetFlag(0);
            }
            entrust.setHandleTime(MidlandHelper.getCurrentTime());
            entrustServiceImpl.updateEntrustById(entrust);
            User user = (User) request.getSession().getAttribute("userInfo");

            EntrustLog appointLog = new EntrustLog();
            if (StringUtils.isEmpty(dealRemark)) {
                appointLog.setRemark("无");
            } else {
                appointLog.setRemark(dealRemark);
            }

            appointLog.setEntrustId(entrust.getId());
            appointLog.setLogTime(MidlandHelper.getCurrentTime());
            appointLog.setOperatorId(user.getId());
            appointLog.setOperatorName(user.getUserCnName());

            appointLog.setState(entrust.getStatus());
            entrustLogServiceImpl.insertEntrustLog(appointLog);
            map.put("state", 0);
        } catch (Exception e) {
            logger.error("updateByPrimaryKeySelective {}", entrust, e);
            map.put("state", -1);
        }
        return map;
    }


    /**
     * 重新分配经纪人，把经纪人更新到委托记录里
     *
     * @param record
     * @return
     */
    @RequestMapping("/reset_agent")
    @ResponseBody
    public Object resetAgent(Entrust record) {
        logger.info("resetAgent ： 重新分配经纪人，{}", record);
        Map map = new HashMap();
        try {
            Entrust obj = selectByPrimaryKey(record.getId());
            record.setResetFlag(0);//重新分配经纪人后，隐藏“重新分配按钮”
            record.setAssignedTime(MidlandHelper.getCurrentTime());
            entrustServiceImpl.updateEntrustById(record);
            //发送给经纪人的短信：模板56849，内容：您好{1}(经纪人姓名),官网收到委托放盘，{1}(楼盘名称){2}(联系人){3}(电话)，现已分配由您跟进，请尽快与客户进行联系，助您成交！
            List<String> param = new ArrayList<>();
            param.add(obj.getAgentName()==null?"":obj.getAgentName());
            param.add(obj.getCommunityName()==null?"":obj.getCommunityName());
            param.add(obj.getPhone()==null?"":obj.getPhone());
           //apiHelper.smsSender("18825234000",Contant.SMS_TEMPLATE_89262,param);
            apiHelper.smsSender(record.getAgentPhone(),Contant.SMS_TEMPLATE_89262,param);

            //发送给预约人的短信：模板id56848，内容：您好！您提交的看房日程由{1}电话{2}帮您带看，该经纪人会尽快联系您安排看房，请保持电话畅通，感谢！
            List<String> param1 = new ArrayList<>();
            param1.add(record.getAgentName()==null?"":record.getAgentName());
            param1.add(record.getAgentPhone()==null?"":record.getAgentPhone());
//            apiHelper.smsSender("18825234000",Contant.SMS_TEMPLATE_56848,param1);
            apiHelper.smsSender(obj.getPhone(),Contant.SMS_TEMPLATE_56848,param1);

            map.put("state", 0);
        } catch (Exception e) {
            logger.error("resetAgent : {}", record, e);
            map.put("state", -1);
        }

        return map;
    }

    @RequestMapping("/export")
    public void userInfoExportExcel(Entrust entrust, HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = MidlandHelper.getCurrentUser(request);
        entrust.setCityId(user.getCityId());
        List<Entrust> dataList = entrustServiceImpl.findEntrustList(entrust);
        PoiExcelExport pee = new PoiExcelExport(response, "委托记录", "sheet1");
        //调用
        List<ExportModel> exportModels = new ArrayList<>();
        for (Entrust appointment1 : dataList) {
            ExportModel exportModel = new ExportModel();
            exportModel.setModelName1(appointment1.getEntrustSn());
            List<ParamObject> sources = JsonMapReader.getMap("source");
            exportModel.setModelName2(MidlandHelper.getNameById(appointment1.getSource(), sources));
            exportModel.setModelName3(appointment1.getNickName());
            exportModel.setModelName4(appointment1.getPhone());
            List<ParamObject> houseTypes = JsonMapReader.getMap("appointment_houseType");
            exportModel.setModelName5(MidlandHelper.getNameById(appointment1.getHouseType(), houseTypes));
            List<ParamObject> sellRents = JsonMapReader.getMap("entrust_type");
            exportModel.setModelName6(MidlandHelper.getNameById(appointment1.getSellRent(), sellRents));
            exportModel.setModelName7(appointment1.getEntrustTime());
            exportModel.setModelName8(appointment1.getAreaName());
            exportModel.setModelName9(appointment1.getCommunityName());
            exportModel.setModelName10(appointment1.getAddress());
            exportModel.setModelName11(appointment1.getLayout());
            exportModel.setModelName12(appointment1.getMeasure());
            exportModel.setModelName13(appointment1.getPrice());
            exportModel.setModelName14(appointment1.getEntrustTime());
            exportModel.setModelName15(appointment1.getAgentName());
            List<ParamObject> statusList = JsonMapReader.getMap("appointment_status");
            exportModel.setModelName16(MidlandHelper.getNameById(appointment1.getStatus(), statusList));
            exportModel.setModelName17(appointment1.getHandleTime());
            exportModels.add(exportModel);
        }
        String titleColumn[] = {"modelName1", "modelName2", "modelName3", "modelName4", "modelName5", "modelName6", "modelName7", "modelName8", "modelName9", "modelName10", "modelName11", "modelName12", "modelName13", "modelName14", "modelName15", "modelName16", "modelName17"};
        String titleName[] = {"委托编号", "平台", "称呼", "手机号码", "类型", "分类", "委托时间", "所属区域", "小区名", "门牌地址", "户型", "面积", "售价/租价", "预约时间", "经纪人", "状态", "处理时间"};
        int titleSize[] = {13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13};
        //其他设置 set方法可全不调用
        pee.wirteExcel(titleColumn, titleName, titleSize, exportModels, request);
    }

    /**
     * 批量更新
     **/
    @RequestMapping("batchUpdate")
    @ResponseBody
    public Object batchUpdate(String ids, Entrust entrust) throws Exception {
        List<Entrust> commentList = new ArrayList<>();
        String[] ides = ids.split(",", -1);
        for (String id : ides) {
            Entrust comment1 = new Entrust();
            comment1.setId(Integer.valueOf(id));
            comment1.setIsDelete(entrust.getIsDelete());
            commentList.add(comment1);
        }
        Map<String, Object> map = new HashMap<>();
        try {
            logger.debug("updateEntrustById  {}", commentList);
            entrustServiceImpl.batchUpdate(commentList);
            map.put("state", 0);
        } catch (Exception e) {
            logger.error("updateEntrustById  {}", commentList, e);
            map.put("state", -1);
        }
        return map;
    }

}
