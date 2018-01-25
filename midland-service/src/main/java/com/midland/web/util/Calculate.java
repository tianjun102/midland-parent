package com.midland.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class Calculate {


    private static final Logger logger = LoggerFactory.getLogger(Calculate.class);

    /**
     * 保留两位小数
     *
     * @param decimal
     * @return
     */
    public static double keepTwoDecimal(double decimal) {
        BigDecimal decimal1 = new BigDecimal(decimal);
        return decimal1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    /**
     * double加法,解决精度丢失问题
     *
     * @param money
     * @param payMoney
     * @return
     */
    public static Double add(Double money, Double payMoney) {

        String moneyStr = String.valueOf(money);

        String payMoneyStr = String.valueOf(payMoney);

        BigDecimal moneyDecimal = new BigDecimal(moneyStr);

        BigDecimal payMoneyDecimal = new BigDecimal(payMoneyStr);

        return moneyDecimal.add(payMoneyDecimal).setScale(2, BigDecimal.ROUND_FLOOR).doubleValue();

    }

    /**
     * double乘法,解决精度丢失问题
     *
     * @param multiplier
     * @param multiplier1
     * @return
     */
    public static Double multiply(Double multiplier, Double multiplier1) {

        String multiplierStr = String.valueOf(multiplier);

        String multiplierStr1 = String.valueOf(multiplier1);

        BigDecimal multiplierDecimal = new BigDecimal(multiplierStr);

        BigDecimal multiplierDecimal1 = new BigDecimal(multiplierStr1);

        return multiplierDecimal.multiply(multiplierDecimal1).setScale(2, BigDecimal.ROUND_FLOOR).doubleValue();

    }

    /**
     * double除法,解决精度丢失问题
     * 四舍五入
     *
     * @param divider
     * @param divider1
     * @return
     */
    public static Double divide(Double divider, Double divider1) {
        if (divider1 == 0 || divider1 == null) {
            divider1 = 1.0;
        }
        if (divider == null) {
            divider = 0.0;
        }
        String dividerStr = String.valueOf(divider);

        String dividerStr1 = String.valueOf(divider1);

        BigDecimal dividerDecimal = new BigDecimal(dividerStr);

        BigDecimal dividerStrDecimal1 = new BigDecimal(dividerStr1);

        return dividerDecimal.divide(dividerStrDecimal1, 4, BigDecimal.ROUND_HALF_UP).doubleValue();

    }


    /**
     * double减法,解决精度丢失问题
     *
     * @param money
     * @param payMoney
     * @return
     */
    public static Double minus(Double money, Double payMoney) {
        return minus(money, payMoney, null);
    }

    /**
     * double减法,解决精度丢失问题
     *
     * @param minus1
     * @param minus2
     * @param printLog
     * @return
     */
    public static Double minus(Double minus1, Double minus2, Boolean printLog) {
        if (minus2 - 0 == 0 || minus2 == null) {
            minus2 = 1.0;
        }
        if (minus1 == null) {
            minus1 = 0.0;
        }
        if (printLog == null) printLog = true;
        if (printLog) {
            logger.debug("minus :　substr1={},substr2={}", minus1, minus2);
        }

        String minusTemp = String.valueOf(minus1);

        String minus2Temp = String.valueOf(minus2);

        BigDecimal minusDecimal = new BigDecimal(minusTemp);

        BigDecimal minus2TempDecimal = new BigDecimal(minus2Temp);

        Double result = minusDecimal.subtract(minus2TempDecimal).setScale(2, BigDecimal.ROUND_FLOOR).doubleValue();
        if (printLog) {
            logger.debug("minus :　substr1={},substr2={},result={}", minus1, minus2, result);
        }
        return result;
    }

}