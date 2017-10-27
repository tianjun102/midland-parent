package com.midland.web.model;


public class SmsConfig{
	/**
	 * 短信配置表
	 **/
	private Integer id;
	/**
	 * 伊莱特负责短信
	 **/
	private String enaitDesc;
	/**
	 * 电饭煲负责人短信
	 **/
	private String riceDesc;
	/**
	 * 精品
	 **/
	private String fineDesc;
	/**
	 * 料理
	 **/
	private String cuisineDesc;
	/**
	 * 配件事业部短信
	 **/
	private String accessoriesDesc;
	/**
	 * 
	 **/
	private Integer isDelete;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEnaitDesc() {
		return enaitDesc;
	}

	public void setEnaitDesc(String enaitDesc) {
		this.enaitDesc = enaitDesc;
	}

	public String getRiceDesc() {
		return riceDesc;
	}

	public void setRiceDesc(String riceDesc) {
		this.riceDesc = riceDesc;
	}

	public String getFineDesc() {
		return fineDesc;
	}

	public void setFineDesc(String fineDesc) {
		this.fineDesc = fineDesc;
	}

	public String getCuisineDesc() {
		return cuisineDesc;
	}

	public void setCuisineDesc(String cuisineDesc) {
		this.cuisineDesc = cuisineDesc;
	}

	public String getAccessoriesDesc() {
		return accessoriesDesc;
	}

	public void setAccessoriesDesc(String accessoriesDesc) {
		this.accessoriesDesc = accessoriesDesc;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	@Override
	public String toString() {
		 final StringBuffer sb=new StringBuffer("SmsConfig{");
		if (id != null) {
			sb.append(", \"id\":\"").append(id).append("\"");
		}
		if (enaitDesc != null) {
			sb.append(", \"enaitDesc\":\"").append(enaitDesc).append("\"");
		}
		if (riceDesc != null) {
			sb.append(", \"riceDesc\":\"").append(riceDesc).append("\"");
		}
		if (fineDesc != null) {
			sb.append(", \"fineDesc\":\"").append(fineDesc).append("\"");
		}
		if (cuisineDesc != null) {
			sb.append(", \"cuisineDesc\":\"").append(cuisineDesc).append("\"");
		}
		if (accessoriesDesc != null) {
			sb.append(", \"accessoriesDesc\":\"").append(accessoriesDesc).append("\"");
		}
		if (isDelete != null) {
			sb.append(", \"isDelete\":\"").append(isDelete).append("\"");
		}
		return sb.toString();
	}
}