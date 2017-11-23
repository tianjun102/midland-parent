package com.midland.web.model;


public class HuxingMap{
	/**
	 * 户型分布图
	 **/
	private Integer id;
	/**
	 * 图片url
	 **/
	private String imgUrl;
	/**
	 * 图片类型；1=一居室2=二居室3=三居室
	 **/
	private String type;
	/**
	 * 描述
	 **/
	private String description;
	/**
	 * 0=正常；1=删除
	 **/
	private Integer isDelete;
	/**
	 * 0=正常；1=隐藏
	 **/
	private Integer isShow;
	/**
	 * 排序字段
	 **/
	private Integer orderBy;
	/**
	 * 计算价格
	 **/
	private String price;
	/**
	 * hot_hand表的id
	 **/
	private Integer hotHandId;
	/**
	 * 创建时间
	 **/
	private String createTime;


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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Integer getHotHandId() {
		return hotHandId;
	}

	public void setHotHandId(Integer hotHandId) {
		this.hotHandId = hotHandId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		 final StringBuffer sb=new StringBuffer("HuxingMap{");
		if (id != null) {
			sb.append(", \"id\":\"").append(id).append("\"");
		}
		if (imgUrl != null) {
			sb.append(", \"imgUrl\":\"").append(imgUrl).append("\"");
		}
		if (type != null) {
			sb.append(", \"type\":\"").append(type).append("\"");
		}
		if (description != null) {
			sb.append(", \"description\":\"").append(description).append("\"");
		}
		if (isDelete != null) {
			sb.append(", \"isDelete\":\"").append(isDelete).append("\"");
		}
		if (isShow != null) {
			sb.append(", \"isShow\":\"").append(isShow).append("\"");
		}
		if (orderBy != null) {
			sb.append(", \"orderBy\":\"").append(orderBy).append("\"");
		}
		if (price != null) {
			sb.append(", \"price\":\"").append(price).append("\"");
		}
		if (hotHandId != null) {
			sb.append(", \"hotHandId\":\"").append(hotHandId).append("\"");
		}
		if (createTime != null) {
			sb.append(", \"createTime\":\"").append(createTime).append("\"");
		}
		sb.append("}");
		return sb.toString();
	}
}