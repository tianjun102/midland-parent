package com.midland.web.controller.upload;

import com.alibaba.fastjson.JSONArray;
import com.midland.core.util.AppSetting;
import com.midland.web.model.Quotation;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import java.io.InputStream;
import java.util.*;

/**
 * Created by 'ms.x' on 2017/8/17.
 */
@Controller
@RequestMapping("/upload")
public class FileLoadController implements ServletConfigAware, ServletContextAware {
	
	private final Logger logger = LoggerFactory.getLogger(FileLoadController.class);
	private ServletContext servletContext;
	
	private ServletConfig servletConfig;
	
	private static String OS = System.getProperty("os.name").toLowerCase();
	
	@RequestMapping("/check")
	@ResponseBody
	public Object checkFileExist(String fileName) {
		Map map = new HashMap<>();
		StringBuffer sb = new StringBuffer();
		String basePath = this.getClass().getClassLoader().getResource("").getPath();
		sb.append(basePath).append("/store").append("/").append(fileName);
		File file = new File(sb.toString());
		if (file.exists()) {
			map.put("state", -1);
		} else {
			map.put("state", 0);
		}
		return map;
	}
	
	@RequestMapping("excel_read")
	public void imports(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String dateMonth = null;//excel表格的月份
		Workbook wb = null;
		InputStream is = null;
		try {
			FileItem fileItem = getUploadFileItem(request, response);
			String name = fileItem.getName();
			is = fileItem.getInputStream();
			String fileType = name.substring(name.lastIndexOf(".") + 1, name.length());
			if (fileType.equals("xls")) {
				wb = new HSSFWorkbook(is);
			} else if (fileType.equals("xlsx")) {
				wb = new XSSFWorkbook(is);
			} else {
				throw new Exception("读取的不是excel文件");
			}
			List result = new ArrayList<>();//对应excel文件
			
			Sheet sheet = wb.getSheetAt(0);
			
			int rowSize = sheet.getLastRowNum() + 1;
			List<String> areaList =null;
			List<String> distNum = null;
			String areaName = null;
			String houseType = null;
			for (int j = 0; j < rowSize; j++) {//遍历行
				Row row = sheet.getRow(j);
				if (row == null) {//略过空行
					continue;
				}
				List <String> list =null;
				
				if (j>0 && j%2==1){
					areaList = new ArrayList<>();
					list=areaList;
				}else if (j>0 && j%2==0){
					distNum = new ArrayList<>();
					list=distNum;
					
				}
				int cellSize = row.getLastCellNum();//行中有多少个单元格，也就是有多少列
				for (int k = 0; k < cellSize; k++) {
					Cell cell = row.getCell(k);
					String value = null;
					if (cell != null) {
						value = cell.toString();
					}
					if (j == 0) {//获取日期
						if (k == 1) {
							dateMonth = value;
							System.out.println(dateMonth);
						}
						continue;
					}
					if (k==0){
						if (value!=null && !value.equals("")){
							areaName=value;
						}
					}
					else if (k==1){
						if (value!=null && !value.equals("")){
							houseType=value;
						}
					}
					else if (k==2){
						
					}else{
						list.add(value);
					}
					
				}
				if (j>0 && j%2==0){
					int length =areaList.size()<31?areaList.size():31;
					for (int x=0;x<length;x++){
						if (areaList.get(x)!=null&& !areaList.get(x).equals("")) {
							Quotation quotation = new Quotation();
							quotation.setCityName("深圳");
							quotation.setAreaName(areaName);
							int houseTypeId = 3;
							if (houseType.equals("商业")) {
								houseTypeId = 0;
							}
							if (houseType.equals("住宅")) {
								houseTypeId = 1;
							}
							if (houseType.equals("其他")) {
								houseTypeId = 2;
							}
							if (houseType.equals("办公")) {
								houseTypeId = 3;
							}
							quotation.setType(houseTypeId);
							quotation.setDealAcreage(String.valueOf(areaList.get(x)));
							quotation.setDealNum(Double.valueOf(distNum.get(x)).intValue());
							result.add(quotation);
						}
					}
				}
			}
			System.out.println(JSONArray.toJSONString(result));
		} catch (Exception e) {
			logger.error("",e);
		} finally {
			if (wb != null) {
				//wb.close();
			}
			if (is != null) {
				is.close();
			}
		}
	}
	
	
	private FileItem getUploadFileItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		if (upload.isMultipartContent(request)) {
			try {
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> iterator = items.iterator();
				while (iterator.hasNext()) {
					FileItem item = iterator.next();
					if (!item.isFormField()) {
						return item;
					}
				}
			} catch (FileUploadException e) {
				logger.error("upload error ", e);
				return null;
			}
		}
		logger.error("not found file");
		return null;
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
		if (multipartContent) {
			try {
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {
					FileItem item = iter.next();
					if (item.isFormField()) {
						processFormField(item);
					} else {
						filePath = processUploadedFile(item);
					}
				}
				response.getWriter().print(filePath);
			} catch (FileUploadException e) {
				logger.error("upload error ", e);
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
			if (isMacOSX()) {
				opposite = "/store/";
				storePath = System.getProperty("test.webapp") + opposite;
			} else {
				storePath = AppSetting.getAppSetting("upload_dir");
				opposite = storePath;
			}
			File file = new File(storePath);
			if (!file.exists()) {
				file.mkdirs();
			}
			File uploadedFile = new File(storePath + fileName);
			if (!uploadedFile.exists()) {
				try {
					item.write(uploadedFile);
				} catch (Exception e) {
					throw new FileUploadException("上传失败");
				}
			}
			return opposite + fileName;
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
	
	public boolean isMultipartContent(HttpServletRequest request) {
		return !"POST".equalsIgnoreCase(request.getMethod()) ? false : FileUploadBase.isMultipartContent(new ServletRequestContext(request));
		
	}
	
	
	public static boolean isMacOSX() {
		return OS.indexOf("mac") >= 0 && OS.indexOf("os") > 0 && OS.indexOf("x") > 0;
	}
	
	@Override
	public void setServletConfig(ServletConfig servletConfig) {
		this.servletConfig = servletConfig;
	}
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}
