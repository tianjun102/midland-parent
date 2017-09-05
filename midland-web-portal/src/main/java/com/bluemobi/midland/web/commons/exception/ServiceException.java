package com.bluemobi.midland.web.commons.exception;
/**   
* @Title: ServiceException.java
* @Package com.huixin.wks.web.commons.exception
* @Description: TODO(用一句话描述该文件做什么)
* @author yek
* @date 2017年3月22日 上午8:59:27 
*/
public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public ServiceException() {
		super();
	}

	public ServiceException(String msg) {
		super(msg);
	}

	public ServiceException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}
}
