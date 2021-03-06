package com.midland.controller;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.base.BaseFilter;
import com.midland.web.Contants.Contant;
import com.midland.web.PublicUtils.QuotationUtil;
import com.midland.web.model.Area;
import com.midland.web.model.ExportModel;
import com.midland.web.model.QuotationSecondHand;
import com.midland.web.model.user.User;
import com.midland.web.service.QuotationSecondHandService;
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
import java.util.stream.Collectors;

@Controller

@RequestMapping("/quotationSecondHand/")
public class QuotationSecondHandController extends BaseFilter {

    private Logger log = LoggerFactory.getLogger(QuotationSecondHandController.class);
    @Autowired
    private QuotationSecondHandService quotationSecondHandServiceImpl;

    @Autowired
    private SettingService settingService;

    /**
     *
     **/
    @RequestMapping("index")
    public String quotationSecondHandIndex(QuotationSecondHand quotationSecondHand, Model model, HttpServletRequest request) throws Exception {
        List<ParamObject> paramObjects = JsonMapReader.getMap("quotation_type");
        model.addAttribute("types", paramObjects);
        settingService.getAllProvinceList(model);
        User user = MidlandHelper.getCurrentUser(request);
        model.addAttribute("isSuper", user.getIsSuper());

        List<ParamObject> ojb = JsonMapReader.getMap("is_delete");
        model.addAttribute("isDeletes", ojb);
        return "quotationSecondHand/quotationSecondHandIndex";
    }

    @RequestMapping("to_import")
    public String toImport(HttpServletRequest request, Model model) throws Exception {
        List<ParamObject> paramObjects = JsonMapReader.getMap("quotation_type");
        model.addAttribute("types", paramObjects);
        model.addAttribute("isNew", "0");
        User user = MidlandHelper.getCurrentUser(request);
        model.addAttribute("isSuper", user.getIsSuper());
        settingService.getAllProvinceList(model);
        return "quotationSecondHand/toImport";
    }

    @RequestMapping("toolsTip_index")
    public String toolsTipIndex(Model model) {
        List<Area> list1 = settingService.queryAllCityByRedis();
        settingService.getAllProvinceList(model);
        List<ParamObject> paramObjects = JsonMapReader.getMap("quotation_type");
        model.addAttribute("types", paramObjects);
        model.addAttribute("citys", list1);
        model.addAttribute("showType", "0");
        return "quotationSecondHand/contentIndex";
    }

    /**
     *
     **/
    @RequestMapping("toolsTip")
    public String toolsTip(QuotationSecondHand obj, String areaId, String areaName, String url, String showType, Model model) throws Exception {
        try {
            String date = MidlandHelper.getCurrentTime();
            String startTime = MidlandHelper.getFormatyyMMToMonth(date, -12);
            if (StringUtils.isEmpty(areaId) && StringUtils.isEmpty(areaName)) {
                /**
                 * 如果没有选择区域,默认为全市
                 */
                obj.setAreaId("0");
            }
            if (StringUtils.isEmpty(obj.getCityId())) {
                /**
                 * 没有选择城市,默认为当前用户的城市
                 */
                obj.setCityId("085");
            }
            if (obj.getType() == null) {
                /**
                 * 没有选择类型,默认为住宅:  0商业;1住宅;2其他;3办公
                 */
                obj.setType(1);
            }
            if (obj.getStartTime() == null) {
                obj.setStartTime(MidlandHelper.getFormatyyMMToMonth(date, -12));
            }
            if (obj.getEndTime() == null) {
                obj.setEndTime(MidlandHelper.getCurrentTime());
            }
            List<String> month = new ArrayList<>();
            List<Object> numRatioList = new ArrayList<>();
            List<Object> acreageRatioList = new ArrayList<>();
            List<Object> numList = new ArrayList<>();
            List<Object> acreageList = new ArrayList<>();
            final double[] listMax = {0};
            double listMin = 0;
            final double[] ratioMax = {0};
            final double[] ratioMin = {0};
            List<QuotationSecondHand> listTemp = quotationSecondHandServiceImpl.findQuotationSecondHandList(obj);

            List<String> time = MidlandHelper.getBettenTime(obj.getStartTime(),obj.getEndTime());

            /**
             * jdk 1.8 lamada表达式,stream ,filter,查询12个月前的数据到当前月的数据
             */
            List<QuotationSecondHand> list = listTemp.stream().filter(e -> {
                return e.getDataTime().compareTo(startTime) >= 0;
            }).collect(Collectors.toList());
            List<String> timelist= new ArrayList<>();
            list.forEach(e->{
                timelist.add(e.getDataTime());
            });
            time.forEach(e->{
                if (!timelist.contains(e)){
                    QuotationSecondHand q=new QuotationSecondHand();
                    q.setDataTime(e);
                    q.setDealNum(0);
                    q.setDealAcreage("0");
                    list.add(q) ;
                }
            });

            list.stream().sorted((s1,s2)->{
                return s1.getDataTime().compareTo(s2.getDataTime());
            }).forEach(e -> {
                month.add(e.getDataTime());
                final QuotationSecondHand[] res = {null};
                listTemp.forEach(e1 -> {
                    if (e.getDataTime().equals(MidlandHelper.getFormatyyMMToMonth(e1.getDataTime(), +1))) {
                        res[0] = e1;
                    }
                });
                if ("0".equals(showType)) {
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
                } else {
                    Double preAcreage = null;
                    if (res[0] != null && res[0].getDealAcreage() != null) {
                        preAcreage = Double.valueOf(res[0].getDealAcreage());
                    }
                    acreageList.add(e.getDealAcreage());
                    Double ratio = QuotationUtil.getRatio(Double.valueOf(e.getDealAcreage()), preAcreage);
                    listMax[0] = QuotationUtil.getMax(listMax[0], Double.valueOf(e.getDealAcreage()));
                    ratioMax[0] = QuotationUtil.getMax(ratioMax[0], ratio);
                    ratioMin[0] = QuotationUtil.getMin(ratioMin[0], ratio);
                    acreageRatioList.add(ratio);
                }

            });
            model.addAttribute("numList", numList);
            model.addAttribute("numRatioList", numRatioList);
            model.addAttribute("acreageList", acreageList);
            model.addAttribute("acreageRatioList", acreageRatioList);
            listMax[0] = QuotationUtil.getDoubleUp(listMax[0]);
            listMin = 0;
            ratioMax[0] = QuotationUtil.getRatioDoubleUp(ratioMax[0]);
            ratioMin[0] = QuotationUtil.getRatioDoubleUp(ratioMin[0]);
//
            model.addAttribute("months", JSONArray.toJSONString(month));
            model.addAttribute("listMax", listMax[0]);
            model.addAttribute("listMin", listMin);
            model.addAttribute("listStep", (listMax[0] - listMin) / 10);
            model.addAttribute("ratioMax", ratioMax[0]);
            model.addAttribute("ratioMin", ratioMin[0]);
            model.addAttribute("ratioStep", (ratioMax[0] - ratioMin[0]) / 10);

            List<ParamObject> paramObjects = JsonMapReader.getMap("quotation_type");
            model.addAttribute("types", paramObjects);

            if (url != null) {
                return "quotationSecondHand/" + url;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "quotationSecondHand/dealNumContent";
    }




    @RequestMapping("list")
    public String list(QuotationSecondHand obj, Model model, HttpServletRequest request) throws Exception {

        User user = MidlandHelper.getCurrentUser(request);
        model.addAttribute("isSuper", user.getIsSuper());
        if (!Contant.isSuper.equals(user.getIsSuper())) {//不是超级管理员，只能看属性城市的相关信息
            obj.setCityId(user.getCityId());
        }
        MidlandHelper.doPage(request);
        Page<QuotationSecondHand> list = (Page<QuotationSecondHand>) quotationSecondHandServiceImpl.findQuotationSecondHandList(obj);
        Paginator paginator = list.getPaginator();
        List<ParamObject> paramObjects = JsonMapReader.getMap("quotation_type");
        model.addAttribute("types", paramObjects);
        model.addAttribute("paginator", paginator);
        model.addAttribute("items", list);
        return "quotationSecondHand/quotationSecondHandList";
    }

    /**
     *
     **/
    @RequestMapping("to_add")
    public String toAddQuotationSecondHand(QuotationSecondHand quotationSecondHand, Model model) throws Exception {
        settingService.getAllProvinceList(model);
        List<ParamObject> paramObjects = JsonMapReader.getMap("quotation_type");
        model.addAttribute("types", paramObjects);
        return "quotationSecondHand/addQuotationSecondHand";
    }

    /**
     * 新增
     **/
    @RequestMapping("add")
    @ResponseBody
    public Object addQuotationSecondHand(QuotationSecondHand quotationSecondHand) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            log.debug("addQuotationSecondHand {}", quotationSecondHand);
            quotationSecondHand.setUpdateTime(MidlandHelper.getCurrentTime());
            quotationSecondHandServiceImpl.insertQuotationSecondHand(quotationSecondHand);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("addQuotationSecondHand异常 {}", quotationSecondHand, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     * 查询
     **/
    @RequestMapping("get_quotationSecondHand")
    public String getQuotationSecondHandById(Integer id, Model model) {
        log.debug("getQuotationSecondHandById  {}", id);
        QuotationSecondHand result = quotationSecondHandServiceImpl.selectQuotationSecondHandById(id);
        model.addAttribute("item", result);
        return "quotationSecondHand/updateQuotationSecondHand";
    }

    /**
     * 删除
     **/
    @RequestMapping("delete")
    @ResponseBody
    public Object deleteQuotationSecondHandById(Integer id) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            log.debug("deleteQuotationSecondHandById  {}", id);
            quotationSecondHandServiceImpl.deleteQuotationSecondHandById(id);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("deleteQuotationSecondHandById  {}", id, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     *
     **/
    @RequestMapping("to_update")
    public String toUpdateQuotationSecondHand(Integer id, Model model) throws Exception {
        QuotationSecondHand result = quotationSecondHandServiceImpl.selectQuotationSecondHandById(id);
        result.setProvinceName(StringUtils.trim(result.getProvinceName()));
        result.setDataTime(result.getDataTime() + "-01");
        List<ParamObject> paramObjects = JsonMapReader.getMap("quotation_type");
        Area province = settingService.getCityByCityId(result.getCityId());
        model.addAttribute("area", province);
        model.addAttribute("types", paramObjects);
        settingService.getAllProvinceList(model);
        model.addAttribute("item", result);
        return "quotationSecondHand/updateQuotationSecondHand";
    }

    /**
     * 更新
     **/
    @RequestMapping("update")
    @ResponseBody
    public Object updateQuotationSecondHandById(QuotationSecondHand quotationSecondHand) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            log.debug("updateQuotationSecondHandById  {}", quotationSecondHand);
            quotationSecondHand.setUpdateTime(MidlandHelper.getCurrentTime());
            quotationSecondHandServiceImpl.updateQuotationSecondHandById(quotationSecondHand);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("updateQuotationSecondHandById  {}", quotationSecondHand, e);
            map.put("state", -1);
        }
        return map;
    }

    @RequestMapping("/export")
    public void quotationSecondHandExportExcel(QuotationSecondHand obj, HttpServletResponse response, HttpServletRequest request) throws Exception {
        User user = MidlandHelper.getCurrentUser(request);
        if (!Contant.isSuper.equals(user.getIsSuper())) {//不是超级管理员，只能看属性城市的相关信息
            obj.setCityId(user.getCityId());
        }
        List<String> month = new ArrayList<>();
        List<QuotationSecondHand> listTemp = quotationSecondHandServiceImpl.findQuotationSecondHandList(obj);
        /**
         * jdk 1.8 lamada表达式,stream ,filter,查询12个月前的数据到当前月的数据
         */
        List<QuotationSecondHand> list = listTemp;

        List<QuotationSecondHand> listRes = new ArrayList<>();
        list.forEach(e -> {
            month.add(e.getDataTime());
            listTemp.forEach(e1 -> {
                if (e.getDataTime().equals(MidlandHelper.getFormatyyMMToMonth(e1.getDataTime(), +1))
                        && e.getType().equals(e1.getType()) && e.getCityId().equals(e1.getCityId()) &&
                        e.getAreaId().equals(e1.getAreaId())) {
                    e.setPreNum(e1.getDealNum() == null ? 0 : e1.getDealNum());
                }
            });
            listRes.add(e);
        });

        PoiExcelExport pee = new PoiExcelExport(response, "二手房信息", "sheet1");
        //调用

        List<ExportModel> exportModels = new ArrayList<>();
        listRes.forEach(e -> {
            ExportModel exportModel = new ExportModel();
            exportModel.setModelName1(e.getCityName());
            exportModel.setModelName2(e.getAreaName());
            List<ParamObject> quotationType = JsonMapReader.getMap("quotation_type");

            exportModel.setModelName3(MidlandHelper.getNameById(e.getType(), quotationType));
            exportModel.setModelName4(String.valueOf(e.getDealNum()==null?0:e.getDealNum()));
            exportModel.setModelName5(String.valueOf(e.getPreNum()==null?0:e.getPreNum()));
            exportModel.setModelName6(String.valueOf(e.getDealAcreage()));
            //（当前月数据-上个月数据)/上个月数据=当月环比
            Double ratio = QuotationUtil.getRatio(Double.valueOf(e.getDealNum()), Double.valueOf(e.getPreNum() == null ? 0 : e.getPreNum()));
            exportModel.setModelName7(String.valueOf(ratio));
            exportModel.setModelName8(e.getDataTime());
            exportModel.setModelName9(e.getUpdateTime());
            exportModels.add(exportModel);
        });
        String titleColumn[] = {"modelName1", "modelName2", "modelName3", "modelName4", "modelName5", "modelName6", "modelName7", "modelName8", "modelName9"};
        String titleName[] = {"城市", "区域", "类型", "成交套数", "上月成交套数", "成交面积", "环比", "数据时间", "更新时间"};
        int titleSize[] = {13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13};
        //其他设置 set方法可全不调用
        pee.wirteExcel(titleColumn, titleName, titleSize, exportModels, request);
    }

    /**
     * 批量更新
     **/
    @RequestMapping("batchUpdate")
    @ResponseBody
    public Object batchUpdate(String ids, QuotationSecondHand quotationSecondHand) throws Exception {
        List<QuotationSecondHand> commentList = new ArrayList<>();
        String[] ides = ids.split(",", -1);
        for (String id : ides) {
            QuotationSecondHand comment1 = new QuotationSecondHand();
            comment1.setId(Integer.valueOf(id));
            comment1.setIsDelete(quotationSecondHand.getIsDelete());
            commentList.add(comment1);
        }
        Map<String, Object> map = new HashMap<>();
        try {
            log.debug("updateCategoryById  {}", commentList);
            quotationSecondHandServiceImpl.batchUpdate(commentList);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("updateCategoryById  {}", commentList, e);
            map.put("state", -1);
        }
        return map;
    }

}
