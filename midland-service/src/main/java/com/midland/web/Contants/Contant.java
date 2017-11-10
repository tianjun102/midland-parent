package com.midland.web.Contants;

/**
 * Created by 'ms.x' on 2017/9/7.
 */
public class Contant {
	public static final int isNewHouse = 1;
	public static final int isOldHouse = 0;
	public static final String answerAuditKey = "isAnswerAudit";
	public static final int answerAuditOpen = 1;//开启问答审核
	public static final int answerAuditClose = 0;//关闭问答审核,默认关闭

	/**
	 * 超级管理员标识
	 */
	public static final String isSuper = "1";

	/**
	 *  系统数据逻辑删除：0正常，1删除
	 */
	public static final int isNotDelete=0;
	/**
	 *  系统数据逻辑删除：0正常，1删除
	 */
	public static final int isDelete=1;


	/**
	 * 委托类型
	 */
	public static final int ENTRUST_BUY=0;
	public static final int ENTRUST_SALE=1;
	public static final int ENTRUST_RENT_OUT=2;
	public static final int ENTRUST_RENT_IN=3;
	public static final int ENTRUST_EVALUATE=4;

	/**
	 * 预约
	 */
	public static final String APPOINT_VCODE_KEY ="midland:appoint:vcode:";
	/**
	 * 问答
	 */
	public static final String QUESTON_THUMB_UP_KEY ="midland:queston:thumb_up:";
	public static final int timeOutDays=7;


	/**
	 * 关注类型
	 */
	public static final int ATTENTION_QUESTION=1;

}
