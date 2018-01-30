package com.midland.web.PublicUtils;

import com.midland.web.util.Calculate;
import com.midland.web.util.MidlandHelper;

/**
 * Created by 'ms.x' on 2017/9/26.
 */
public class QuotationUtil {
    public static double getDoubleUp(double value) {
        String val = String.valueOf(value);
        if (val.contains("E")) {
            val = MidlandHelper.scientificNotation(val);
        }
        int length;
        int len = val.indexOf(".");
        if (len == -1) {
            length = val.length();
        } else {
            length = len;
        }
        int intVal = Integer.valueOf(val.substring(0, 1)) + 1;
        return intVal * Math.pow(10, length - 1);
    }


    public static double getRatioDoubleUp(double value) {
        String val = String.valueOf(value);

        int length;
        int len = val.indexOf(".");
        if (len == -1) {
            length = val.length();
        } else {
            length = len;
        }
        int intVal;
        if (val.contains("-")) {
            intVal = Integer.valueOf(val.substring(0, 2)) - 1;
            return intVal * Math.pow(10, length - 2);
        } else {
            intVal = Integer.valueOf(val.substring(0, 1)) + 1;
            return intVal * Math.pow(10, length - 1);
        }
    }

    public static Double getRatio(Double dealNum, Double preDealNum) {
        if (dealNum ==null|| dealNum-0.0==0 || preDealNum == null || preDealNum -0.0==0){
            return 0.0;
        }
        double minus = preDealNum == null ? dealNum : preDealNum;
        double numRes = Calculate.minus(Double.valueOf(dealNum), minus);
        double numRatio = Calculate.divide(numRes, minus);
        return Calculate.multiply(numRatio, 100.00);
    }

    public static double getMin(double x, double y) {
        return x > y ? y : x;
    }

    public static double getMax(double x, double y) {
        return x > y ? x : y;
    }
}
