package com.midland.web.model;


public class FilmLibrary{
	/**
	 * 片库id
	 **/
	private Integer id;
	/**
	 * 城市id
	 **/
	private String cityId;
	/**
	 * 图片描述
	 **/
	private String imgDesc;
	/**
	 * 楼盘名称
	 **/
	private String housesName;
	/**
	 * 楼盘id
	 **/
	private String housesId;
	/**
	 * 简介
	 **/
	private String introduction;
	/**
	 * 视频url地址
	 **/
	private String videoUrl;
	/**
	 * 0未删除，1删除
	 **/
	private Integer isDelete;
	/**
	 * 点击数
	 **/
	private Integer clickNum;
	/**
	 * 类型，0住宅，1商铺，2写字楼
	 **/
	private Integer filmType;
	/**
	 * 图片地址
	 **/
	private String imgUrl;
	/**
	 * 录盘人id
	 **/
	private Integer operatorId;
	/**
	 * 录盘人名称
	 **/
	private String operatorName;
	/**
	 * 视频时长
	 **/
	private Double duration;
	/**
	 * 显示，0显示，1隐藏
	 **/
	private Integer isShow;
	/**
	 * 城市名称
	 **/
	private String cityName;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getImgDesc() {
		return imgDesc;
	}

	public void setImgDesc(String imgDesc) {
		this.imgDesc = imgDesc;
	}

	public String getHousesName() {
		return housesName;
	}

	public void setHousesName(String housesName) {
		this.housesName = housesName;
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

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getClickNum() {
		return clickNum;
	}

	public void setClickNum(Integer clickNum) {
		this.clickNum = clickNum;
	}

	public Integer getFilmType() {
		return filmType;
	}

	public void setFilmType(Integer filmType) {
		this.filmType = filmType;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public Double getDuration() {
		return duration;
	}

	public void setDuration(Double duration) {
		this.duration = duration;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Override
	public String toString() {
		 final StringBuffer sb=new StringBuffer("FilmLibrary{");
		if (id != null) {
			sb.append(", \"id\":\"").append(id).append("\"");
		}
		if (cityId != null) {
			sb.append(", \"cityId\":\"").append(cityId).append("\"");
		}
		if (imgDesc != null) {
			sb.append(", \"imgDesc\":\"").append(imgDesc).append("\"");
		}
		if (housesName != null) {
			sb.append(", \"housesName\":\"").append(housesName).append("\"");
		}
		if (housesId != null) {
			sb.append(", \"housesId\":\"").append(housesId).append("\"");
		}
		if (introduction != null) {
			sb.append(", \"introduction\":\"").append(introduction).append("\"");
		}
		if (videoUrl != null) {
			sb.append(", \"videoUrl\":\"").append(videoUrl).append("\"");
		}
		if (isDelete != null) {
			sb.append(", \"isDelete\":\"").append(isDelete).append("\"");
		}
		if (clickNum != null) {
			sb.append(", \"clickNum\":\"").append(clickNum).append("\"");
		}
		if (filmType != null) {
			sb.append(", \"filmType\":\"").append(filmType).append("\"");
		}
		if (imgUrl != null) {
			sb.append(", \"imgUrl\":\"").append(imgUrl).append("\"");
		}
		if (operatorId != null) {
			sb.append(", \"operatorId\":\"").append(operatorId).append("\"");
		}
		if (operatorName != null) {
			sb.append(", \"operatorName\":\"").append(operatorName).append("\"");
		}
		if (duration != null) {
			sb.append(", \"duration\":\"").append(duration).append("\"");
		}
		if (isShow != null) {
			sb.append(", \"isShow\":\"").append(isShow).append("\"");
		}
		if (cityName != null) {
			sb.append(", \"cityName\":\"").append(cityName).append("\"");
		}
		return sb.toString();
	}
}