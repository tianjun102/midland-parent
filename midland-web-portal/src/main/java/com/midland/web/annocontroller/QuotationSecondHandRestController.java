package com.midland.web.annocontroller;

import com.midland.web.PublicUtils.QuotationUtil;
import com.midland.web.model.QuotationSecondHand;
import com.midland.web.service.QuotationSecondHandService;
import com.midland.base.ServiceBaseFilter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ResultStatusUtils;

import java.util.*;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.midland.web.util.MidlandHelper;

import javax.servlet.http.HttpServletRequest;

@RestController
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
// @SuppressWarnings("all")
@RequestMapping("/quotationSecondHand/")
public class QuotationSecondHandRestController extends ServiceBaseFilter {

    private Logger log = LoggerFactory.getLogger(QuotationSecondHandRestController.class);
    @Autowired
    private QuotationSecondHandService quotationSecondHandServiceImpl;

    /**
     * 新增
     **/
    @RequestMapping("add")
    public Object addQuotationSecondHand(@RequestBody QuotationSecondHand obj) throws Exception {
        Result result = new Result();
        try {
            log.info("addQuotationSecondHand {}", obj);
            quotationSecondHandServiceImpl.insertQuotationSecondHand(obj);
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("success");
        } catch (Exception e) {
            log.error("addQuotationSecondHand异常 {}", obj, e);
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("service error");
        }
        return result;
    }

    /**
     * 查询
     **/
    @RequestMapping("get")
    public Object getQuotationSecondHandById(@RequestBody Map map) {
        Result result = new Result();
        try {
            Integer id = (Integer) map.get("id");
            log.info("getQuotationSecondHandById  {}", id);
            QuotationSecondHand quotationSecondHand = quotationSecondHandServiceImpl.selectQuotationSecondHandById(id);
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("success");
            result.setModel(quotationSecondHand);
        } catch (Exception e) {
            log.error("getQuotationSecondHand异常 {}", map, e);
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("service error");
        }
        return result;
    }

    /**
     * 更新
     **/
    @RequestMapping("update")
    public Object updateQuotationSecondHandById(@RequestBody QuotationSecondHand obj) throws Exception {
        Result result = new Result();
        try {
            log.info("updateQuotationSecondHandById  {}", obj);
            quotationSecondHandServiceImpl.updateQuotationSecondHandById(obj);
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("success");
        } catch (Exception e) {
            log.error("updateQuotationSecondHandById  {}", obj, e);
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("service error");
        }
        return result;
    }

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @RequestMapping("list")
    public Object findQuotationSecondHandList(@RequestBody QuotationSecondHand obj, HttpServletRequest request) {
        Result result = new Result();
        Map map = new HashMap();
        try {
            if (StringUtils.isEmpty(obj.getAreaId()) && StringUtils.isEmpty(obj.getAreaName())) {
                obj.setAreaId("0");
            } else {
                obj.setAreaId(obj.getAreaId());
                obj.setAreaName(obj.getAreaName());
            }
            if (StringUtils.isEmpty(obj.getCityId())) {
                obj.setCityId("085");
            }
            if (obj.getType() == null) {
                obj.setType(0);
            }
            if (obj.getStartTime() == null) {
                Date date = new Date();
                obj.setStartTime(MidlandHelper.getMonth(date, -12));
            }
            if (obj.getEndTime() == null) {
                obj.setEndTime(MidlandHelper.getCurrentTime());
            }
            List<String> month = new ArrayList<>();
            List<Object> numRatioList = new ArrayList<>();
            List<Object> acreageRatioList = new ArrayList<>();
            List<Object> numList = new ArrayList<>();
            List<Object> acreageList = new ArrayList<>();
            double dealNumMax = 0;
            double dealNumMin = 0;
            double acreageMax = 0;
            double acreageMin = 0;
            List<QuotationSecondHand> list = quotationSecondHandServiceImpl.findQuotationSecondHandList(obj);
            obj.setStartTime(MidlandHelper.getFormatPreMonth(obj.getStartTime(), -1));
            obj.setEndTime(MidlandHelper.getFormatPreMonth(obj.getEndTime(), -1));
            List<QuotationSecondHand> listTemp = quotationSecondHandServiceImpl.findQuotationSecondHandList(obj);
            for (QuotationSecondHand view : list) {
                month.add(view.getDataTime());

                QuotationSecondHand res = null;
                for (QuotationSecondHand quoTemp : listTemp) {
                    if (view.getDataTime().equals(MidlandHelper.getFormatyyMMToMonth(quoTemp.getDataTime(), +1))) {
                        res = quoTemp;
                    }
                }
                Double preNum = null;
                if (res != null && res.getDealNum() != null) {
                    preNum = Double.valueOf(res.getDealNum());
                }
                Double preAcreage = null;
                if (res != null&& res.getDealAcreage()!=null) {
                    preAcreage = Double.valueOf(res.getDealAcreage());
                }
                //
                dealNumMax = QuotationUtil.getMax(dealNumMax, view.getDealNum());
                dealNumMin = QuotationUtil.getMin(dealNumMin, view.getDealNum());
                //

                acreageMax = QuotationUtil.getMax(acreageMax, Double.valueOf(view.getDealAcreage()==null?"0":view.getDealAcreage()));
                acreageMin = QuotationUtil.getMin(acreageMin,  Double.valueOf(view.getDealAcreage()==null?"0":view.getDealAcreage()));

                numList.add(view.getDealNum());
                acreageList.add(view.getDealAcreage());
                Double numRatio = QuotationUtil.getRatio(Double.valueOf(view.getDealNum()), preNum);
                Double acreageRatio = QuotationUtil.getRatio(Double.valueOf(view.getDealAcreage()), preAcreage);
                numRatioList.add(numRatio);
                acreageRatioList.add(acreageRatio);

            }
            map.put("month",month);
            map.put("numList",numList);
            map.put("dealNumMax", dealNumMax );
            map.put("acreageMax", acreageMax);
            map.put("numRatioList",numRatioList);
            map.put("acreageList",acreageList);
            map.put("numRatioList",numRatioList);
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("success");
            result.setModel(map);
        } catch (Exception e) {
            log.error("findQuotationSecondHandList",e);
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("error");
            result.setModel(null);
        }
        return result;
    }
}
