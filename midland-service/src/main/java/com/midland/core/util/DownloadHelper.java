package com.midland.core.util;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class DownloadHelper {
	/**
	 * 下载文件 hyz
	 * @param path 绝对路径 
	 * @param response 
	 */
	public static void download(String path,HttpServletResponse response){  
        //获取网站部署路径(通过ServletContext对象)，用于确定下载文件位置，从而实现下载  
		File file = new File(path);  
		if(!file.exists()){
			return;
		}
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型  
        response.setContentType("multipart/form-data");  //mime type
        //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)  
        response.setHeader("Content-Disposition", "attachment;fileName="+file.getName());   
        //通过文件路径获得File对象(假如此路径中有一个download.pdf文件)  
        
  
        
		try {
			InputStream in = new FileInputStream(path);
			OutputStream out = response.getOutputStream();  
	          
	        //写文件  
	        int b;  
	        while((b=in.read())!= -1)  
	        {  
	            out.write(b);  
	        }  
	          
	        in.close();  
	        out.close(); 
		} catch (Exception e) {
			e.printStackTrace();
		}  
         

    }  

	
}
