package com.midland.web.model;


public class CommunityAlbum{
	/**
	 * 小区图片表
	 **/
	private Integer id;
	/**
	 * 图片类型 0=实景图；1=户型图
	 **/
	private Integer type;
	/**
	 * 图片url
	 **/
	private String imgUrl;
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
	 * 图片描述
	 **/
	private String description;
	/**
	 * hot_hand表id
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		 final StringBuffer sb=new StringBuffer("CommunityAlbum{");
		if (id != null) {
			sb.append(", \"id\":\"").append(id).append("\"");
		}
		if (type != null) {
			sb.append(", \"type\":\"").append(type).append("\"");
		}
		if (imgUrl != null) {
			sb.append(", \"imgUrl\":\"").append(imgUrl).append("\"");
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
		if (description != null) {
			sb.append(", \"description\":\"").append(description).append("\"");
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