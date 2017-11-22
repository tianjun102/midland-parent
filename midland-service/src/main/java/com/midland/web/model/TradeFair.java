package com.midland.web.model;


import java.util.List;

public class TradeFair{
	/**
	 * 楼盘展销会主键id
	 **/
	private Integer id;
	/**
	 * 楼盘图片连接
	 **/
	private String imgUrl;


	private List<String> imgUrlList;
	/**
	 * 楼盘名称
	 **/
	private String title;
	/**
	 * 楼盘id
	 **/
	private String housesId;
	/**
	 * 简介
	 **/
	private String introduction;
	/**
	 * 显示,0开启，1关闭
	 **/
	private Integer isShow;
	/**
	 * 0未删除，1删除
	 **/
	private Integer isDelete;
	/**
	 * 图片描述
	 **/
	private String description;
	/**
	 * 图片描述
	 **/
	private List<String> descriptionList;
	/**
	 * 录盘人
	 **/
	private String operatorId;
	/**
	 * 录盘人名称
	 **/
	private String operatorName;
	/**
	 * 类型：0楼盘展销会，1看楼团
	 **/
	private Integer tradeType;

	public List<String> getDescriptionList() {
		return descriptionList;
	}

	public void setDescriptionList(List<String> descriptionList) {
		this.descriptionList = descriptionList;
	}

	public List<String> getImgUrlList() {
		return imgUrlList;
	}

	public void setImgUrlList(List<String> imgUrlList) {
		this.imgUrlList = imgUrlList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHousesId() {
		return housesId;
	}

	public void setHousesId(String housesId) {
		this.housesId = housesId;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public Integer getTradeType() {
		return tradeType;
	}

	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}

	@Override
	public String toString() {
		 final StringBuffer sb=new StringBuffer("TradeFair{");
		if (id != null) {
			sb.append(", \"id\":\"").append(id).append("\"");
		}
		if (imgUrl != null) {
			sb.append(", \"imgUrl\":\"").append(imgUrl).append("\"");
		}
		if (title != null) {
			sb.append(", \"title\":\"").append(title).append("\"");
		}
		if (housesId != null) {
			sb.append(", \"housesId\":\"").append(housesId).append("\"");
		}
		if (introduction != null) {
			sb.append(", \"introduction\":\"").append(introduction).append("\"");
		}
		if (isShow != null) {
			sb.append(", \"isShow\":\"").append(isShow).append("\"");
		}
		if (isDelete != null) {
			sb.append(", \"isDelete\":\"").append(isDelete).append("\"");
		}
		if (description != null) {
			sb.append(", \"description\":\"").append(description).append("\"");
		}
		if (operatorId != null) {
			sb.append(", \"operatorId\":\"").append(operatorId).append("\"");
		}
		if (operatorName != null) {
			sb.append(", \"operatorName\":\"").append(operatorName).append("\"");
		}
		if (tradeType != null) {
			sb.append(", \"tradeType\":\"").append(tradeType).append("\"");
		}
		return sb.toString();
	}
}