package com.midland.web.Contants;

/**
 * Created by 'ms.x' on 2017/9/7.
 */
public class Contant {

    /**
     * 资讯
     */
    public static final int PUBLISH_UP = 0;//资讯上架
    public static final int PUBLISH_DOWN = 1;//资讯下架

    /**
     * information
     */
    public static final String informationBannerKey = "informationBannerKey";//前端资讯滚动是否展示key,0关闭,1开启
    public static final int informationBannerClose = 0;//关闭前端资讯滚动,默认关闭
    public static final int informationBannerOpen = 1;//开启前端资讯滚动
    public static final int INFORMATION = 1;//资讯
    public static final int MARKET_RESEARCH = 0;//市场研究


    public static final int isNewHouse = 1;
    public static final int isOldHouse = 0;
    /**
     * 问答
     */
    public static final String answerAuditKey = "isAnswerAudit";
    public static final int answerAuditOpen = 1;//开启问答审核
    public static final int answerAuditClose = 0;//关闭问答审核,默认关闭
    public static final int answerAuditPass = 1;//问答审核通过

    /**
     * 超级管理员标识
     */
    public static final String isSuper = "1";

    /**
     * 系统数据逻辑删除：0正常，1删除
     */
    public static final int isNotDelete = 0;
    /**
     * 系统数据逻辑删除：0正常，1删除
     */
    public static final int isDelete = 1;
    /**
     * 系统数据显示与隐藏：0显示，1隐藏
     */
    public static final int isNotShow = 1;
    /**
     * 系统数据显示与隐藏：0显示，1隐藏
     */
    public static final int isShow = 0;

    /**
     * 委托类型
     */
    public static final int ENTRUST_BUY = 0;
    public static final int ENTRUST_SALE = 1;
    public static final int ENTRUST_RENT_OUT = 2;
    public static final int ENTRUST_RENT_IN = 3;
    public static final int ENTRUST_EVALUATE = 4;

    /**
     * 发送验证码
     */
    public static final String APPOINT_VCODE_KEY = "midland:appoint:vcode:";//预约
    public static final String QUESTON_THUMB_UP_KEY = "midland:queston:thumb_up:";//问答
    public static final int timeOutDays = 7;
    public static final String SIGN_UP_VCODE_KEY = "midland:sign_up:vcode";


    /**
     * 关注类型
     */
    public static final int ATTENTION_QUESTION = 1;//关注问题


    /**
     * 产生预约类型编号
     */
    public static final String APPOINT_SN_KEY = "midland:Appointment:SN:key";
    /**
     * 产生委托类型编号
     */
    public static final String ENTRUST_SN_KEY = "midland:entrust:SN:key";
    /**
     * 预约记录临时存放到redis
     */
    public static final String APPOINT_CACHE_KEY = "midland:appoint:cache:key";

    /**
     * 收藏功能的redis  KEY
     */
    public static final String COLLE_CACHE_KEY = "midland:colle:cache:key";


    /**
     * 敏感字库KEY（redis存放敏感字）
     */
    public static final String SENSITIVE_CACHE_KEY = "midland:sensitive:cache:key";

    /**
     * 美联首页轮播字符KEY
     */
    public static final String HEAD_CACHE_KEY = "midland:head:cache:key";

    /**
     * 外销网类型的houseType
     */

    public static final Integer OUT_SALE_CACHE_KEY = -1;
    /**
     * 短信模板
     */
    public static final int SMS_TEMPLATE_56849 = 56849;
    public static final int SMS_TEMPLATE_56848 = 56848;
    public static final int SMS_TEMPLATE_56846 = 56846;
    public static final int SMS_TEMPLATE_56845 = 56845;
    public static final int SMS_TEMPLATE_63647 = 63647;//预约记录超过24小时发送短信通知
    public static final int SMS_TEMPLATE_54711 = 54711;//发送短信验证码,预约短信

    public static final String ANSWER_TITLE = "回答了我的提问：";
    public static final String COMMENT_TITLE = "对你的评论进行了回复：";
    public static final String ENTRUST_TITLE = "您的买房委托，||经纪人已经受理";
    public static final String APPOINT_TITLE = "您的预约，||经纪人已经受理";
    public static final String ATTENTION_TITLE = "您关注的||有新增房源动态";
    public static final String TOURISTS = "游客";

}
