package com.midland.web.model;


public class ErrorPage{
	/**
	 * 错误页面编辑
	 **/
	private Integer id;
	/**
	 * 404页面
	 **/
	private String page404;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPage404() {
		return page404;
	}

	public void setPage404(String page404) {
		this.page404 = page404;
	}

	@Override
	public String toString() {
		 final StringBuffer sb=new StringBuffer("ErrorPage{");
		if (id != null) {
			sb.append(", \"id\":\"").append(id).append("\"");
		}
		if (page404 != null) {
			sb.append(", \"page404\":\"").append(page404).append("\"");
		}
		return sb.toString();
	}
}