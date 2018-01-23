package com.midland.web.model;

public class Information{
	/**
	 * 资讯表，主键
	 **/
	private Integer id;
	/**
	 * 分类id
	 **/
	private Integer cateId;
	/**
	 * 分类父id
	 **/
	private Integer cateParentid;
	/**
	 * 楼盘类型；住宅；商铺等
	 **/
	private String type;
	/**
	 * 标题
	 **/
	private String title;
	/**
	 * 平台0网站,1微站
	 **/
	private Integer source;
	/**
	 * 附件地址
	 **/
	private String enclosure;
	/**
	 * meta关键字
	 **/
	private String metaKeywords;
	/**
	 * meta描述
	 **/
	private String metaDesc;
	/**
	 * 缩略图
	 **/
	private String imgUrl;
	/**
	 * 缩略图描述
	 **/
	private String imgDesc;
	/**
	 * 详细描述
	 **/
	private String details;
	/**
	 * 排序
	 **/
	private Integer orderBy;
	/**
	 * 点击次数
	 **/
	private Integer clickNum;
	/**
	 * 0未删除，1删除
	 **/
	private Integer isDelete;
	/**
	 * 0=市场调研；1=资讯
	 **/
	private Integer articeType;
	/**
	 * 发布时间
	 **/
	private String releaseTime;
	/**
	 * 评论
	 **/
	private String comment;
	/**
	 * 城市id
	 */
	private String cityId;
	/**
	 * 城市名称
	 */
	private String cityName;
	/**
	 * 分类名称
	 */
	private String cateName;
	/**
	 * 上架状态；0=上架；1=下架
	 */
	private Integer status;

	private String cateParentName;
	/**
	 * 上架状态；来源
	 */
	private String platform;

	private String startTime;

	private String endTime;
	/**
	 * 作者
	 */
	private String author;
	/**
	 * 评论次数
	 */
	private Integer commentNum;
	/**
	 * 摘要
	 */
	private String summary;

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCateId() {
		return cateId;
	}

	public void setCateId(Integer cateId) {
		this.cateId = cateId;
	}

	public Integer getCateParentid() {
		return cateParentid;
	}

	public void setCateParentid(Integer cateParentid) {
		this.cateParentid = cateParentid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getEnclosure() {
		return enclosure;
	}

	public void setEnclosure(String enclosure) {
		this.enclosure = enclosure;
	}

	public String getMetaKeywords() {
		return metaKeywords;
	}

	public void setMetaKeywords(String metaKeywords) {
		this.metaKeywords = metaKeywords;
	}

	public String getMetaDesc() {
		return metaDesc;
	}

	public void setMetaDesc(String metaDesc) {
		this.metaDesc = metaDesc;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getImgDesc() {
		return imgDesc;
	}

	public void setImgDesc(String imgDesc) {
		this.imgDesc = imgDesc;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public Integer getClickNum() {
		return clickNum;
	}

	public void setClickNum(Integer clickNum) {
		this.clickNum = clickNum;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getArticeType() {
		return articeType;
	}

	public void setArticeType(Integer articeType) {
		this.articeType = articeType;
	}

	public String getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCateParentName() {
		return cateParentName;
	}

	public void setCateParentName(String cateParentName) {
		this.cateParentName = cateParentName;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}


	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}


	public Integer getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	@Override
	public String toString() {
		 final StringBuffer sb=new StringBuffer("Information{");
		if (id != null) {
			sb.append(", \"id\":\"").append(id).append("\"");
		}
		if (cateId != null) {
			sb.append(", \"cateId\":\"").append(cateId).append("\"");
		}
		if (cateParentid != null) {
			sb.append(", \"cateParentid\":\"").append(cateParentid).append("\"");
		}
		if (type != null) {
			sb.append(", \"type\":\"").append(type).append("\"");
		}
		if (title != null) {
			sb.append(", \"title\":\"").append(title).append("\"");
		}
		if (source != null) {
			sb.append(", \"source\":\"").append(source).append("\"");
		}
		if (enclosure != null) {
			sb.append(", \"enclosure\":\"").append(enclosure).append("\"");
		}
		if (metaKeywords != null) {
			sb.append(", \"metaKeywords\":\"").append(metaKeywords).append("\"");
		}
		if (metaDesc != null) {
			sb.append(", \"metaDesc\":\"").append(metaDesc).append("\"");
		}
		if (imgUrl != null) {
			sb.append(", \"imgUrl\":\"").append(imgUrl).append("\"");
		}
		if (imgDesc != null) {
			sb.append(", \"imgDesc\":\"").append(imgDesc).append("\"");
		}
		if (details != null) {
			sb.append(", \"details\":\"").append(details).append("\"");
		}
		if (orderBy != null) {
			sb.append(", \"orderBy\":\"").append(orderBy).append("\"");
		}
		if (clickNum != null) {
			sb.append(", \"clickNum\":\"").append(clickNum).append("\"");
		}
		if (isDelete != null) {
			sb.append(", \"isDelete\":\"").append(isDelete).append("\"");
		}
		if (articeType != null) {
			sb.append(", \"articeType\":\"").append(articeType).append("\"");
		}
		if (releaseTime != null) {
			sb.append(", \"releaseTime\":\"").append(releaseTime).append("\"");
		}
		if (comment != null) {
			sb.append(", \"comment\":\"").append(comment).append("\"");
		}
		if (cityId != null) {
			sb.append(", \"cityId\":\"").append(cityId).append("\"");
		}
		if (cityName != null) {
			sb.append(", \"cityName\":\"").append(cityName).append("\"");
		}
		if (cateName != null) {
			sb.append(", \"cateName\":\"").append(cateName).append("\"");
		}
		if (status != null) {
			sb.append(", \"status\":\"").append(status).append("\"");
		}
		if (cateParentName != null) {
			sb.append(", \"cateParentName\":\"").append(cateParentName).append("\"");
		}
		return sb.toString();
	}
}