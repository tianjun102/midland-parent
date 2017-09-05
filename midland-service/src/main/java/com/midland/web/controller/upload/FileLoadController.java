package com.midland.web.controller.upload;

import com.midland.core.util.AppSetting;
import com.midland.web.util.MidlandHelper;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletConfigAware;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by 'ms.x' on 2017/8/17.
 */
@Controller
@RequestMapping("/upload")
public class FileLoadController implements ServletConfigAware,ServletContextAware {
	
	private final Logger logger = LoggerFactory.getLogger(FileLoadController.class);
	private ServletContext servletContext;
	
	private ServletConfig servletConfig;
	
	private static String OS = System.getProperty("os.name").toLowerCase();
	
	@RequestMapping("/check")
	@ResponseBody
	public Object checkFileExist(String fileName){
		Map map = new HashMap<>();
		StringBuffer sb = new StringBuffer();
		String basePath = this.getClass().getClassLoader().getResource("").getPath();
		sb.append(basePath).append("/store").append("/").append(fileName);
		File file = new File(sb.toString());
		if(file.exists()) {
			map.put("state",-1);
		} else {
			map.put("state",0);
		}
		return map;
	}
	
	@RequestMapping("/img")
	public void upload(HttpServletRequest request, HttpServletResponse response) throws IOException, FileUploadException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		// Configure a repository (to ensure a secure temp location is used)
		ServletContext servletContext = this.servletContext;
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);
		String filePath = null;
		ServletFileUpload upload = new ServletFileUpload(factory);
		boolean multipartContent = upload.isMultipartContent(request);
		if(multipartContent) {
			try {
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {
					FileItem item = iter.next();
					
					if (item.isFormField()) {
						processFormField(item);
					} else {
						filePath=processUploadedFile(item);
					}
				}
				response.getWriter().print(filePath);
			} catch (FileUploadException e) {
				logger.error("upload error ",e);
				response.getWriter().print(filePath);
			}
		} else {
			response.getWriter().print("null");
		}
	}
	
	private String processUploadedFile(FileItem item) throws FileUploadException {
		// Process a file upload
		if (!item.isFormField()) {
			String fileName = item.getName();
			
			String storePath;
			String opposite;
			if (isMacOSX()){
				opposite="/store/";
				storePath = System.getProperty("test.webapp")+opposite;
			}else{
				storePath = AppSetting.getAppSetting("upload_dir");
				opposite = storePath;
			}
			File file =new File(storePath);
			if (!file.exists()){
				file.mkdirs();
			}
			File uploadedFile = new File(storePath + fileName);
			if(!uploadedFile.exists()) {
				try {
					item.write(uploadedFile);
				} catch (Exception e) {
					throw new FileUploadException("上传失败");
				}
			}
			return opposite+fileName;
		}
		return null;
	}
	
	private void processFormField(FileItem item) {
		// Process a regular form field
		if (item.isFormField()) {
			String name = item.getFieldName();
			String value = item.getString();
		}
	}
	public static boolean isMacOSX(){
		return OS.indexOf("mac")>=0&&OS.indexOf("os")>0&&OS.indexOf("x")>0;
	}
	
	@Override
	public void setServletConfig(ServletConfig servletConfig) {
		this.servletConfig=servletConfig;
	}
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}
