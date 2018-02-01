package com.midland.controller;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.base.BaseFilter;
import com.midland.web.PublicUtils.QuotationUtil;
import com.midland.web.Contants.Contant;
import com.midland.web.model.*;
import com.midland.web.model.user.User;
import com.midland.web.service.QuotationService;
import com.midland.web.service.SettingService;
import com.midland.web.util.*;
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
import java.util.*;

@Controller

@RequestMapping("/quotation/")
public class QuotationController extends BaseFilter {

    private Logger log = LoggerFactory.getLogger(QuotationController.class);
    @Autowired
    private QuotationService quotationServiceImpl;
    @Autowired
    private SettingService settingService;

    /**
     *
     **/
    @RequestMapping("index")
    public String quotationIndex(Quotation quotation, Model model, HttpServletRequest request) throws Exception {
        User user = MidlandHelper.getCurrentUser(request);
        if (StringUtils.isEmpty(quotation.getAreaId()) && StringUtils.isEmpty(quotation.getAreaName())) {
            quotation.setAreaId("0");
            quotation.setAreaName("全市");
        } else {
            quotation.setAreaId(quotation.getAreaId());
            quotation.setAreaName(quotation.getAreaName());
        }
        if (StringUtils.isEmpty(quotation.getCityId())) {
            quotation.setCityId(user.getCityId());
            quotation.setCityName(user.getCityName());
        }

        if (quotation.getStartTime() == null) {
            Date date = new Date();
            quotation.setStartTime(MidlandHelper.getyyyyMMddHHmmss(date, -12));
        }
        if (quotation.getEndTime() == null) {
            quotation.setEndTime(MidlandHelper.getCurrentTime());
        }
        List<ParamObject> paramObjects = JsonMapReader.getMap("quotation_type");
        model.addAttribute("types", paramObjects);
        model.addAttribute("item", quotation);
        settingService.getAllProvinceList(model);

        model.addAttribute("isSuper", user.getIsSuper());
        List<ParamObject> ojb = JsonMapReader.getMap("is_delete");
        model.addAttribute("isDeletes", ojb);
        return "quotation/quotationIndex";
    }


    @RequestMapping("to_import")
    public String toImport(HttpServletRequest request, Model model) throws Exception {
        List<ParamObject> paramObjects = JsonMapReader.getMap("quotation_type");
        model.addAttribute("types", paramObjects);
        model.addAttribute("isNew", "0");
        settingService.getAllProvinceList(model);
        return "quotation/toImport";
    }

    /**
     *
     **/
    @RequestMapping("to_add")
    public String toAddQuotation(Quotation quotation, Model model) throws Exception {
        settingService.getAllProvinceList(model);
        List<ParamObject> paramObjects = JsonMapReader.getMap("quotation_type");
        model.addAttribute("types", paramObjects);
        return "quotation/addQuotation";
    }

    /**
     * 新增
     **/
    @RequestMapping("add")
    @ResponseBody
    public Object addQuotation(Quotation quotation) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            log.debug("addQuotation {}", quotation);
            quotationServiceImpl.insertQuotation(quotation);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("addQuotation异常 {}", quotation, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     * 查询
     **/
    @RequestMapping("get_quotation")
    public String getQuotationById(Integer id, Model model) {
        log.debug("getQuotationById  {}", id);
        Quotation result = quotationServiceImpl.selectQuotationById(id);
        model.addAttribute("item", result);
        return "quotation/updateQuotation";
    }

    /**
     * 删除
     **/
    @RequestMapping("delete")
    @ResponseBody
    public Object deleteQuotationById(Integer id) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            log.debug("deleteQuotationById  {}", id);
            quotationServiceImpl.deleteQuotationById(id);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("deleteQuotationById  {}", id, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     *
     **/
    @RequestMapping("to_update")
    public String toUpdateQuotation(Integer id, Model model) throws Exception {
        Quotation result = quotationServiceImpl.selectQuotationById(id);
        result.setDataTime(result.getDataTime() + "-01");
        List<ParamObject> paramObjects = JsonMapReader.getMap("quotation_type");
        Area province = settingService.getCityByCityId(result.getCityId());
        model.addAttribute("area", province);
        model.addAttribute("types", paramObjects);
        settingService.getAllProvinceList(model);
        model.addAttribute("item", result);
        return "quotation/updateQuotation";
    }

    /**
     * 更新
     **/
    @RequestMapping("update")
    @ResponseBody
    public Object updateQuotationById(Quotation quotation) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            log.debug("updateQuotationById  {}", quotation);
            quotationServiceImpl.updateQuotationById(quotation);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("updateQuotationById  {}", quotation, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @RequestMapping("list")
    public String findQuotationList(Quotation quotation, Model model, HttpServletRequest request) {
        try {
            User user = MidlandHelper.getCurrentUser(request);
            if (StringUtils.isEmpty(quotation.getAreaId()) && StringUtils.isEmpty(quotation.getAreaName())) {
                quotation.setAreaId("0");
            } else {
                quotation.setAreaId(quotation.getAreaId());
                quotation.setAreaName(quotation.getAreaName());
            }
            if (StringUtils.isEmpty(quotation.getCityId())) {
                quotation.setCityId(user.getCityId());
            }

            if (quotation.getStartTime() == null) {
                Date date = new Date();
                quotation.setStartTime(MidlandHelper.getyyyyMMddHHmmss(date, -12));
            }
            if (quotation.getEndTime() == null) {
                quotation.setEndTime(MidlandHelper.getCurrentTime());
            }
            log.debug("findQuotationList  {}", quotation);
            model.addAttribute("isSuper", user.getIsSuper());
            if (!Contant.isSuper.equals(user.getIsSuper())) {//不是超级管理员，只能看属性城市的相关信息
                quotation.setCityId(user.getCityId());
            }
            MidlandHelper.doPage(request);
            Page<Quotation> result = (Page<Quotation>) quotationServiceImpl.findQuotationList(quotation);
            Paginator paginator = result.getPaginator();
            List<ParamObject> paramObjects = JsonMapReader.getMap("quotation_type");
            model.addAttribute("types", paramObjects);
            model.addAttribute("paginator", paginator);
            model.addAttribute("items", result);
        } catch (Exception e) {
            log.error("findQuotationList  {}", quotation, e);
            model.addAttribute("paginator", null);
            model.addAttribute("items", null);
        }
        return "quotation/quotationList";
    }


    @RequestMapping("toolsTip_index")
    public String toolsTipIndex(Model model, Quotation quotation, HttpServletRequest request) {
        User user = MidlandHelper.getCurrentUser(request);
        if (StringUtils.isEmpty(quotation.getAreaId()) && StringUtils.isEmpty(quotation.getAreaName())) {
            quotation.setAreaId("0");
            quotation.setAreaName("全市");
        } else {
            quotation.setAreaId(quotation.getAreaId());
            quotation.setAreaName(quotation.getAreaName());
        }
        if (StringUtils.isEmpty(quotation.getCityId())) {
            quotation.setCityId(user.getCityId());
            quotation.setCityName(user.getCityName());
        }

        if (quotation.getStartTime() == null) {
            Date date = new Date();
            quotation.setStartTime(MidlandHelper.getyyyyMMddHHmmss(date, -12));
        }
        if (quotation.getEndTime() == null) {
            quotation.setEndTime(MidlandHelper.getCurrentTime());
        }

        List<Area> list1 = settingService.queryAllCityByRedis();
        settingService.getAllProvinceList(model);
        List<ParamObject> paramObjects = JsonMapReader.getMap("quotation_type");
        model.addAttribute("types", paramObjects);
        model.addAttribute("item", quotation);
        List<ParamObject> paramObjects1 = JsonMapReader.getMap("quotation_houseType_acreage_range");
        model.addAttribute("acreageRange", paramObjects1);
        model.addAttribute("citys", list1);
        model.addAttribute("showType", 0);//优先展示成交套数
        return "quotation/contentIndex";
    }


    @RequestMapping("toolsTip")
    public String showTooltip(Quotation obj, String url, String showType, Model model) throws Exception {
        if (StringUtils.isEmpty(obj.getAreaId()) && StringUtils.isEmpty(obj.getAreaName())) {
            obj.setAreaId("0");//没有传值时,默认为某个全市
        }
        if (StringUtils.isEmpty(obj.getCityId())) {
            obj.setCityId("085");//没有传值时,默认为当前用户的城市
        }
        if (obj.getType() == null) {
            obj.setType(0);
        }
        if (obj.getStartTime() == null) {
            Date date = new Date();
            obj.setStartTime(MidlandHelper.getyyyyMMddHHmmss(date, -12));
        }
        if (obj.getEndTime() == null) {
            obj.setEndTime(MidlandHelper.getCurrentTime());
        }
        List<String> month = new ArrayList<>();

        List<Object> numList = new ArrayList<>();
        List<Object> numRatioList = new ArrayList<>();
        List<Object> acreageList = new ArrayList<>();
        List<Object> acreageRatioList = new ArrayList<>();
        List<Object> dealAvgPriceList = new ArrayList<>();
        List<Object> dealAvgPriceRatioList = new ArrayList<>();
        List<Object> turnVolumeList = new ArrayList<>();
        List<Object> turnVolumeRatioList = new ArrayList<>();
        List<Object> soldNumList = new ArrayList<>();
        List<Object> soldNumRatioList = new ArrayList<>();
        List<Object> soldAcreageList = new ArrayList<>();
        List<Object> soldAcreageRatioList = new ArrayList<>();
        final double[] listMax = {0};
        double listMin = 0;
        final double[] ratioMax = {0};
        final double[] ratioMin = {0};
        List<Quotation> result = quotationServiceImpl.findQuotationList(obj);
        obj.setStartTime(MidlandHelper.getFormatPreMonth(obj.getStartTime(), -1));
        obj.setEndTime(MidlandHelper.getFormatPreMonth(obj.getEndTime(), -1));
        List<Quotation> listTemp = quotationServiceImpl.findQuotationList(obj);
        result.forEach(e -> {
            month.add(e.getDataTime());
            final Quotation[] res = {null};
            listTemp.forEach(e1 -> {
                if (e.getDataTime().equals(MidlandHelper.getFormatyyMMToMonth(e1.getDataTime(), +1))) {
                    res[0] = e1;
                }
            });
            if ("0".equals(showType)) {
                //成交套数
                Double preNum = null;
                if (res[0] != null && res[0].getDealNum() != null) {
                    preNum = Double.valueOf(res[0].getDealNum());
                }
                numList.add(e.getDealNum());
                Double ratio = QuotationUtil.getRatio(Double.valueOf(e.getDealNum()), preNum);
                numRatioList.add(ratio);
                listMax[0] = QuotationUtil.getMax(listMax[0], e.getDealNum());
                ratioMax[0] = QuotationUtil.getMax(ratioMax[0], ratio);
                ratioMin[0] = QuotationUtil.getMin(ratioMin[0], ratio);

            } else if ("1".equals(showType)) {
                //成交面积
                Double preDealAcreage = null;
                if (res[0] != null && res[0].getDealAcreage() != null) {
                    preDealAcreage = Double.valueOf(res[0].getDealAcreage());
                }
                acreageList.add(e.getDealAcreage());
                Double ratio = QuotationUtil.getRatio(Double.valueOf(e.getDealAcreage()), preDealAcreage);
                acreageRatioList.add(ratio);
                listMax[0] = QuotationUtil.getMax(listMax[0], Double.valueOf(e.getDealAcreage()));
                ratioMax[0] = QuotationUtil.getMax(ratioMax[0], ratio);
                ratioMin[0] = QuotationUtil.getMin(ratioMin[0], ratio);
            } else if ("2".equals(showType)) {
                //成交均价
                Double prePrice = null;
                if (res[0] != null && res[0].getPrice() != null) {
                    prePrice = Double.valueOf(res[0].getPrice());
                }
                dealAvgPriceList.add(e.getPrice());
                Double ratio = QuotationUtil.getRatio(Double.valueOf(e.getPrice()), prePrice);
                dealAvgPriceRatioList.add(ratio);
                listMax[0] = QuotationUtil.getMax(listMax[0], Double.valueOf(e.getPrice()));
                ratioMax[0] = QuotationUtil.getMax(ratioMax[0], ratio);
                ratioMin[0] = QuotationUtil.getMin(ratioMin[0], ratio);

            } else if ("3".equals(showType) && e.getDealPrice() != null) {
                //成交金额

                Double preDealPrice = null;
                if (res[0] != null && res[0].getDealPrice() != null) {
                    preDealPrice = Double.valueOf(res[0].getDealPrice());
                }
                turnVolumeList.add(e.getDealPrice());
                Double ratio = QuotationUtil.getRatio(Double.valueOf(e.getDealPrice()), preDealPrice);

                turnVolumeRatioList.add(ratio);
                listMax[0] = QuotationUtil.getMax(listMax[0], Double.valueOf(e.getDealPrice()));
                ratioMax[0] = QuotationUtil.getMax(ratioMax[0], ratio);
                ratioMin[0] = QuotationUtil.getMin(ratioMin[0], ratio);

            } else if ("4".equals(showType) && e.getSoldNum() != null) {
                //可售套数

                Double preSoldNum = null;
                if (res[0] != null && res[0].getSoldNum() != null) {
                    preSoldNum = Double.valueOf(res[0].getSoldNum());
                }
                soldNumList.add(e.getSoldNum());
                Double ratio = QuotationUtil.getRatio(Double.valueOf(e.getSoldNum()), preSoldNum);
                soldNumRatioList.add(ratio);
                listMax[0] = QuotationUtil.getMax(listMax[0], e.getSoldNum());
                ratioMax[0] = QuotationUtil.getMax(ratioMax[0], ratio);
                ratioMin[0] = QuotationUtil.getMin(ratioMin[0], ratio);

            } else if ("5".equals(showType) && e.getSoldArea() != null) {
                //可售面积

                Double preSoldArea = null;
                if (res[0] != null && res[0].getSoldArea() != null) {
                    preSoldArea = Double.valueOf(res[0].getSoldArea());
                }
                soldAcreageList.add(e.getSoldArea());
                Double ratio = QuotationUtil.getRatio(Double.valueOf(e.getSoldArea()), preSoldArea);
                soldAcreageRatioList.add(ratio);
                listMax[0] = QuotationUtil.getMax(listMax[0], Double.valueOf(e.getSoldArea()));
                ratioMax[0] = QuotationUtil.getMax(ratioMax[0], ratio);
                ratioMin[0] = QuotationUtil.getMin(ratioMin[0], ratio);

            }

        });

        model.addAttribute("numList", numList);
        model.addAttribute("numRatioList", numRatioList);
        model.addAttribute("acreageList", acreageList);
        model.addAttribute("acreageRatioList", acreageRatioList);
        model.addAttribute("dealAvgPriceList", dealAvgPriceList);
        model.addAttribute("dealAvgPriceRatioList", dealAvgPriceRatioList);
        model.addAttribute("soldNumList", soldNumList);
        model.addAttribute("soldNumRatioList", soldNumRatioList);
        model.addAttribute("soldAcreageList", soldAcreageList);
        model.addAttribute("soldAcreageRatioList", soldAcreageRatioList);
        model.addAttribute("turnVolumeList", turnVolumeList);
        model.addAttribute("turnVolumeRatioList", turnVolumeRatioList);
        listMax[0] = QuotationUtil.getDoubleUp(listMax[0]);
        listMin = 0;
        ratioMax[0] = QuotationUtil.getRatioDoubleUp(ratioMax[0]);
        ratioMin[0] = QuotationUtil.getRatioDoubleUp(ratioMin[0]);
//
        model.addAttribute("months", JSONArray.toJSONString(month));
        model.addAttribute("listMax", listMax[0]);
        model.addAttribute("listStep", listMax[0] / 10);
        model.addAttribute("listMin", listMin);


        model.addAttribute("ratioMax", ratioMax[0]);
        model.addAttribute("ratioStep", (ratioMax[0] - ratioMin[0]) / 10);
        model.addAttribute("ratioMin", ratioMin[0]);

        if (url != null) {
            return "quotation/" + url;
        }

        return "quotation/dealNumContent";

    }

    @RequestMapping("/export")
    public void quotationExportExcel(Quotation obj, HttpServletResponse response, HttpServletRequest request) throws Exception {
        if (StringUtils.isEmpty(obj.getAreaId()) && StringUtils.isEmpty(obj.getAreaName())) {
            obj.setAreaId("0");
        } else {
            obj.setAreaId(obj.getAreaId());
            obj.setAreaName(obj.getAreaName());
        }
        if (StringUtils.isEmpty(obj.getCityId())) {
            obj.setCityId("085");
        }

        if (obj.getStartTime() == null) {
            Date date = new Date();
            obj.setStartTime(MidlandHelper.getyyyyMMddHHmmss(date, -12));
        }
        if (obj.getEndTime() == null) {
            obj.setEndTime(MidlandHelper.getCurrentTime());
        }
        List<String> month = new ArrayList<>();

        List<Object> numList = new ArrayList<>();
        List<Object> numRatioList = new ArrayList<>();
        List<Object> acreageList = new ArrayList<>();
        List<Object> acreageRatioList = new ArrayList<>();
        List<Object> dealAvgPriceList = new ArrayList<>();
        List<Object> dealAvgPriceRatioList = new ArrayList<>();
        List<Object> turnVolumeList = new ArrayList<>();
        List<Object> turnVolumeRatioList = new ArrayList<>();
        List<Object> soldNumList = new ArrayList<>();
        List<Object> soldNumRatioList = new ArrayList<>();
        List<Object> soldAcreageList = new ArrayList<>();
        List<Object> soldAcreageRatioList = new ArrayList<>();
        double listMax = 0;
        double listMin = 0;
        double ratioMax = 0;
        double ratioMin = 0;
        List<Quotation> result = quotationServiceImpl.findQuotationList(obj);
        obj.setStartTime(MidlandHelper.getFormatPreMonth(obj.getStartTime(), -1));
        obj.setEndTime(MidlandHelper.getFormatPreMonth(obj.getEndTime(), -1));
        List<Quotation> listTemp = quotationServiceImpl.findQuotationList(obj);
        List<Quotation> listRes = new ArrayList<>();
        result.forEach(e -> {
            month.add(e.getDataTime());
            listTemp.forEach(e1 -> {
                if (e.getDataTime().equals(MidlandHelper.getFormatyyMMToMonth(e1.getDataTime(), +1))) {
                    e.setPreNum(e1.getDealNum());
                }
            });
            listRes.add(e);
        });


        PoiExcelExport pee = new PoiExcelExport(response, "新房信息", "sheet1");
        //调用
        List<ExportModel> exportModels = new ArrayList<>();
        listRes.forEach(e -> {
            ExportModel exportModel = new ExportModel();
            exportModel.setModelName1(e.getCityName());
            exportModel.setModelName2(e.getAreaName());
            List<ParamObject> quotationType = JsonMapReader.getMap("quotation_type");

            exportModel.setModelName3(MidlandHelper.getNameById(e.getType(), quotationType));
            exportModel.setModelName4(String.valueOf(e.getDealNum() == null ? 0 : e.getDealNum()));
            exportModel.setModelName5(String.valueOf(e.getPreNum() == null ? 0 : e.getPreNum()));
            Double ratio = QuotationUtil.getRatio(Double.valueOf(e.getDealNum()), Double.valueOf(e.getPreNum() == null ? 0 : e.getPreNum()));
            exportModel.setModelName6(String.valueOf(ratio));
            exportModel.setModelName7(String.valueOf(e.getDealAcreage() == null ? 0 : e.getDealAcreage()));

            exportModel.setModelName8(String.valueOf(e.getPrice() == null ? 0 : e.getPrice()));
            exportModel.setModelName9(String.valueOf(e.getDealPrice() == null ? 0 : e.getDealPrice()));
            exportModel.setModelName10(String.valueOf(e.getSoldNum() == null ? 0 : e.getSoldNum()));
            exportModel.setModelName11(String.valueOf(e.getSoldArea() == null ? 0 : e.getSoldArea()));
            exportModel.setModelName12(e.getDataTime());
            exportModel.setModelName13(e.getUpdateTime());
            exportModels.add(exportModel);
        });
        String titleColumn[] = {"modelName1", "modelName2", "modelName3", "modelName4", "modelName5", "modelName6", "modelName7", "modelName8", "modelName9", "modelName10", "modelName11", "modelName12", "modelName13"};
        String titleName[] = {"城市", "区域", "类型", "成交套数", "上月成交套数", "成交套数环比", "成交面积", "成交均价", "成交金额", "可售套数", "可售面积", "数据时间", "更新时间"};
        int titleSize[] = {13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13};
        //其他设置 set方法可全不调用
        pee.wirteExcel(titleColumn, titleName, titleSize, exportModels, request);
    }

    /**
     * 批量更新
     **/
    @RequestMapping("batchUpdate")
    @ResponseBody
    public Object batchUpdate(String ids, Quotation quotation) throws Exception {
        List<Quotation> commentList = new ArrayList<>();
        String[] ides = ids.split(",", -1);
        for (String id : ides) {
            Quotation comment1 = new Quotation();
            comment1.setId(Integer.valueOf(id));
            comment1.setIsDelete(quotation.getIsDelete());
            commentList.add(comment1);
        }
        Map<String, Object> map = new HashMap<>();
        try {
            log.debug("updateCategoryById  {}", commentList);
            quotationServiceImpl.batchUpdate(commentList);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("updateCategoryById  {}", commentList, e);
            map.put("state", -1);
        }
        return map;
    }

}