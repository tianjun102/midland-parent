package com.midland.web.model;

public class HeadMsg{
	/**
	 * 
	 **/
	private Integer id;
	/**
	 * 创建时间
	 **/
	private String createTime;
	/**
	 * 标题
	 **/
	private String title;
	/**
	 * 内容
	 **/
	private String content;
	/**
	 * 是否删除,0未删除,1删除
	 **/
	private Integer isDelete;
	/**
	 * 是否显示,0隐藏,1显示
	 **/
	private Integer isShow;
	private String startTime;
	private String endTime;


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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	@Override
	public String toString() {
		 final StringBuffer sb=new StringBuffer("HeadMsg{");
		if (id != null) {
			sb.append(", \"id\":\"").append(id).append("\"");
		}
		if (createTime != null) {
			sb.append(", \"createTime\":\"").append(createTime).append("\"");
		}
		if (title != null) {
			sb.append(", \"title\":\"").append(title).append("\"");
		}
		if (content != null) {
			sb.append(", \"content\":\"").append(content).append("\"");
		}
		if (isDelete != null) {
			sb.append(", \"isDelete\":\"").append(isDelete).append("\"");
		}
		if (isShow != null) {
			sb.append(", \"isShow\":\"").append(isShow).append("\"");
		}
		sb.append("}");
		return sb.toString();
	}
}