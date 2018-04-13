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
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.midland.web.util.MidlandHelper;

import javax.servlet.http.HttpServletRequest;

@RestController
// @SuppressWarnings("all")
@RequestMapping("/quotationSecondHand/")
public class QuotationSecondHandRestController extends ServiceBaseFilter {

    private Logger log = LoggerFactory.getLogger(QuotationSecondHandRestController.class);
    @Autowired
    private QuotationSecondHandService quotationSecondHandServiceImpl;



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
     * 获取同比数据
     * @param obj
     * @param request
     * @return
     */
    @RequestMapping("getAvgPriceAndTb")
    public Object getQuotationSecondHandTongBiByDate(@RequestBody QuotationSecondHand obj ,HttpServletRequest request){
        Result result = new Result();
        Map map = new HashMap();
        String date = MidlandHelper.getCurrentTime();
        //String date = "2017-05-01 12:12:12";
        try {
            if (obj.getCityId()==null){
                result.setMsg("城市id不能为空");
                result.setCode(ResultStatusUtils.STATUS_CODE_203);
                return result;
            }
            obj.setType(1);//1住宅
            obj.setDataTime(MidlandHelper.formatMonth(date));
            List<QuotationSecondHand> quotationSecondHands = quotationSecondHandServiceImpl.getQuotationSecondHandByDate(obj);
            if (quotationSecondHands==null||quotationSecondHands.size()<1){
                map.put("dealAvgPrice",0);//当前月的均价
                map.put("ratio",0);//同比去年
                result.setModel(map);
                result.setCode(ResultStatusUtils.STATUS_CODE_200);
                result.setMsg("成功");
                return result;
            }
            map.put("dealAvgPrice",quotationSecondHands.get(0).getDealAvgPrice());//当前月的均价
            obj.setDataTime(MidlandHelper.getFormatyyMMToMonth(date,-12));
            List<QuotationSecondHand> quotationSecondHand1 = quotationSecondHandServiceImpl.getQuotationSecondHandByDate(obj);
            if (quotationSecondHand1==null||quotationSecondHand1.size()<1){
                map.put("ratio",0);//同比去年
                result.setModel(map);
                result.setCode(ResultStatusUtils.STATUS_CODE_200);
                result.setMsg("成功");
                return result;
            }

            Double ratio = QuotationUtil.getRatio(Double.valueOf(quotationSecondHands.get(0).getDealAvgPrice()),
                    Double.valueOf(quotationSecondHand1.get(0).getDealAvgPrice()));
            map.put("ratio",ratio);//同比去年
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("成功");
            result.setModel(map);
        } catch (Exception e) {
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("系统繁忙,请重试!");
            result.setModel(map);
        }
        return  result;
    }

    @RequestMapping("getAvgPriceAndRatio")
    public Object getQuotationSecondHandByDate(@RequestBody QuotationSecondHand obj ,HttpServletRequest request){
        Result result = new Result();
        Map map = new HashMap();
        String date = MidlandHelper.getCurrentTime();
        //String date = "2017-05-01 12:12:12";
        try {
            if (obj.getCityId()==null){
                result.setMsg("城市id不能为空");
                result.setCode(ResultStatusUtils.STATUS_CODE_203);
                return result;
            }
            obj.setType(1);//1住宅
            obj.setDataTime(MidlandHelper.formatMonth(date));
            List<QuotationSecondHand> quotationSecondHands = quotationSecondHandServiceImpl.getQuotationSecondHandByDate(obj);
            if (quotationSecondHands==null||quotationSecondHands.size()<1){
                map.put("dealAvgPrice",0);//当前月的均价
                map.put("ratio",0);//环比上个月
                result.setModel(map);
                result.setCode(ResultStatusUtils.STATUS_CODE_200);
                result.setMsg("成功");
                return result;
            }
            map.put("dealAvgPrice",quotationSecondHands.get(0).getDealAvgPrice());//当前月的均价
            obj.setDataTime(MidlandHelper.getFormatyyMMToMonth(date,-1));
            List<QuotationSecondHand> quotationSecondHand1 = quotationSecondHandServiceImpl.getQuotationSecondHandByDate(obj);
            if (quotationSecondHand1==null||quotationSecondHand1.size()<1){
                map.put("ratio",0);//环比上个月
                result.setModel(map);
                result.setCode(ResultStatusUtils.STATUS_CODE_200);
                result.setMsg("成功");
                return result;
            }

            Double ratio = QuotationUtil.getRatio(Double.valueOf(quotationSecondHands.get(0).getDealAvgPrice()),
                    Double.valueOf(quotationSecondHand1.get(0).getDealAvgPrice()));
            map.put("ratio",ratio);//环比上个月
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("成功");
            result.setModel(map);
        } catch (Exception e) {
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("系统繁忙,请重试!");
            result.setModel(map);
        }
        return  result;
    }


    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @RequestMapping("list")
    public Object findQuotationSecondHandList(@RequestBody QuotationSecondHand obj, HttpServletRequest request) {
        Result result = new Result();
        Map map = new HashMap();
        String date = MidlandHelper.getCurrentTime();
        String startTime;
        try {
            if (StringUtils.isEmpty(obj.getAreaId()) && StringUtils.isEmpty(obj.getAreaName())) {
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
                startTime=MidlandHelper.getFormatyyMMToMonth(date, -12);
                obj.setStartTime(MidlandHelper.getFormatyyMMToMonth(date, -13));
            }else{
                startTime=obj.getStartTime();
            }
            if (obj.getEndTime() == null) {
                obj.setEndTime(MidlandHelper.formatMonth(date));
            }
            List<String> month = new ArrayList<>();
            /**
             * 成交套数
             */
            List<Object> numList = new ArrayList<>();
            List<Object> numRatioList = new ArrayList<>();
            final double[] dealNumMax = {0};
            final double[] dealNumMin = {0};
            /**
             * 成交面积
             */
            List<Object> acreageRatioList = new ArrayList<>();
            List<Object> acreageList = new ArrayList<>();
            final double[] acreageMax = {0};
            final double[] acreageMin = {0};

            /**
             * 成交均价
             */
            List<Object> avgPriceList = new ArrayList<>();
            List<Object> avgPriceRatioList = new ArrayList<>();
            final double[] avgPriceMax = {0};
            final double[] avgPriceMin = {0};
            /**
             * 思路:  只查询最近一年的数据,所以从当前月往前退12个月,因为要计算(环比上个月)数据,
             * 那么12个月前的环比上个月就是13个月前的数据,所有一次性查询13个月前到现在的数据
             *
             */
            List<QuotationSecondHand> listTemp = quotationSecondHandServiceImpl.findQuotationSecondHandList(obj);
            /**
             * jdk 1.8 lamada表达式,stream ,filter,查询12个月前的数据到当前月的数据
             */
            String initTime=MidlandHelper.formatMonth(startTime);
            List<QuotationSecondHand> list = listTemp.stream().filter(e->{
                return e.getDataTime().compareTo(initTime)>=0;
            }).collect(Collectors.toList());

            list.forEach(e->{
                month.add(e.getDataTime());
                final QuotationSecondHand[] res = {null};
                /**
                 * 计算环比,(当前月的数据-上个月的数据)/上个月的数据 * 100%=环比
                 */
                listTemp.forEach(e1->{
                    if (e.getDataTime().equals(MidlandHelper.getFormatyyMMToMonth(e1.getDataTime(), +1))) {
                        res[0] = e1;
                    }
                });
                Double preNum = 0.0;
                if (res[0] != null && res[0].getDealNum() != null) {
                    preNum = Double.valueOf(res[0].getDealNum());
                }
                Double preAcreage = 0.0;
                if (res[0] != null&& res[0].getDealAcreage()!=null) {
                    preAcreage = Double.valueOf(res[0].getDealAcreage());
                }
                Double avgPrice = 0.0;
                if (res[0] != null&& res[0].getDealAvgPrice()!=null) {
                    avgPrice = Double.valueOf(res[0].getDealAvgPrice());
                }
                /**
                 * 给前端图表用的图形最大值
                 */
                dealNumMax[0] = QuotationUtil.getMax(dealNumMax[0], e.getDealNum());
                dealNumMin[0] = QuotationUtil.getMin(dealNumMin[0], e.getDealNum());
                /**
                 * 给前端图表用的图形最大值
                 */
                acreageMax[0] = QuotationUtil.getMax(acreageMax[0], Double.valueOf(e.getDealAcreage()==null?"0":e.getDealAcreage()));
                acreageMin[0] = QuotationUtil.getMin(acreageMin[0],  Double.valueOf(e.getDealAcreage()==null?"0":e.getDealAcreage()));
                /**
                 * 给前端图表用的图形最大值
                 */
                avgPriceMax[0] = QuotationUtil.getMax(avgPriceMax[0], Double.valueOf(e.getDealAvgPrice()==null?"0":e.getDealAvgPrice()));
                avgPriceMin[0] = QuotationUtil.getMin(avgPriceMin[0],  Double.valueOf(e.getDealAvgPrice()==null?"0":e.getDealAvgPrice()));

                Double numRatio = QuotationUtil.getRatio(Double.valueOf(e.getDealNum()), preNum);
                Double acreageRatio = QuotationUtil.getRatio(Double.valueOf(e.getDealAcreage()), preAcreage);
                Double avgPriceRatio = QuotationUtil.getRatio(Double.valueOf(e.getDealAvgPrice()), avgPrice);

                numList.add(e.getDealNum());
                numRatioList.add(numRatio);

                acreageList.add(e.getDealAcreage());
                acreageRatioList.add(acreageRatio);

                avgPriceList.add(e.getDealAvgPrice());
                avgPriceRatioList.add(avgPriceRatio);
            });


            map.put("month",month);

            map.put("numList",numList);
            map.put("dealNumMax", dealNumMax[0]);
            map.put("numRatioList",numRatioList);

            map.put("acreageList",acreageList);
            map.put("acreageMax", acreageMax[0]);
            map.put("acreageRatioList",acreageRatioList);

            map.put("avgPriceList",avgPriceList);
            map.put("avgPriceMax", avgPriceMax[0]);
            map.put("avgPriceRatioList",avgPriceRatioList);
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
