package com.midland.web.filter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.midland.core.util.AppSetting;

public class loginFilter implements Filter  {
	/**
	 * @author tianjun
	 * 此过滤器用于session超时后跳转到登录页面
	 */
	private String loginurl = AppSetting.getAppSetting("loginurl");
	//private String indexLoginAddress;
	public loginFilter(){
		
	}
	//public loginFilter(String indexLoginAddress){
	//	this.indexLoginAddress = indexLoginAddress;
	//}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)arg0;
		String requestURI = request.getRequestURI();
		HttpServletResponse response = (HttpServletResponse)arg1;
		//过滤掉修改密码的页面请求
		/*if(!requestURI.endsWith("/tsh-bs/WEB-INF/views/user/passwordModifyOk.jsp")&&!requestURI.endsWith(".css")&&!requestURI.endsWith(".png")&&!requestURI.endsWith(".js")){
		  if(request.getSession().getAttributeNames()!=null){
			  if(request.getSession().getAttributeNames().hasMoreElements()){
			  if(request.getSession().getAttributeNames().nextElement().equals("SPRING_SECURITY_SAVED_REQUEST")){
				  this.printPages(request, response);
			  }
			  }else{
				  this.printPages(request, response);
			  } 
		  }*/
		  //如果session超时的时候
		if(!requestURI.endsWith("/login")){
			
			if(request.getSession().isNew()){
				this.printPages(request, response);
			}
			if(request.getSession().getAttribute("userInfo")==null){
				this.printPages(request, response);
			}
		}
		/*}*/
		  
		arg2.doFilter(arg0, arg1);
	}

	private void printPages(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
	    PrintWriter out = response.getWriter();  
	    out.println("<html>");  
	    out.println("<script>");  
	    out.println("window.open ('"+loginurl+"','_top')"); 
	    out.println("</script>");  
	    out.println("</html>"); 
	    response.reset(); 
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException { 
		// TODO Auto-generated method stub
		
	}

}
