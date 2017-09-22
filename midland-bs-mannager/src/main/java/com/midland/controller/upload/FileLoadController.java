package com.midland.controller.upload;

import com.midland.core.util.AppSetting;
import com.midland.web.Contants.Contant;
import com.midland.web.model.Area;
import com.midland.web.model.Quotation;
import com.midland.web.service.QuotationSecondHandService;
import com.midland.web.service.QuotationService;
import com.midland.web.service.SettingService;
import com.midland.web.util.Calculate;
import com.midland.web.util.MidlandHelper;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@SuppressWarnings("all")
@RequestMapping("/upload")
public class FileLoadController implements ServletConfigAware, ServletContextAware {
	
	private final Logger logger = LoggerFactory.getLogger(FileLoadController.class);
	private ServletContext servletContext;
	private ServletConfig servletConfig;
	@Autowired
	private QuotationService quotationServiceImpl;
	@Autowired
	private SettingService settingServiceImpl;
	@Autowired
	private QuotationSecondHandService quotationSecondHandServiceImpl;
	
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
	
	@RequestMapping(value = "excel_read", produces = "application/json")
	public void imports(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Workbook wb = null;
		InputStream is = null;
		try {
			FileItem fileItem = getUploadFileItem(request, response);
			is = fileItem.getInputStream();
			wb = getWorkbook(fileItem, is);
			quotationExcelReader(request, wb);
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			if (wb != null) {
				//wb.close();
			}
			if (is != null) {
				is.close();
			}
		}
	}
	
	/**
	 * 根据is生成workbook
	 *
	 * @param fileItem
	 * @param is
	 * @return
	 * @throws Exception
	 */
	private Workbook getWorkbook(FileItem fileItem, InputStream is) throws Exception {
		String name = fileItem.getName();
		Workbook wb;
		String fileType = name.substring(name.lastIndexOf(".") + 1, name.length());
		
		if (fileType.equals("xls")) {
			wb = new HSSFWorkbook(is);
		} else if (fileType.equals("xlsx")) {
			wb = new XSSFWorkbook(is);
		} else {
			throw new Exception("读取的不是excel文件");
		}
		return wb;
	}
	
	
	private void quotationExcelReader(HttpServletRequest request, Workbook wb) throws Exception {
		List result = new ArrayList<>();//对应excel文件
		Sheet sheet = wb.getSheetAt(0);
		String readType = request.getParameter("readType");
		if ("1".equals(readType)) {
			//读取新房excel数据
			newHouseResource(result, sheet, request);
			quotationServiceImpl.insertQuotationBatch(result);
			
		} else if ("0".equals(readType)) {
			//读取二手房excel数据
			secondHandHouseResource(result, sheet, request);
			quotationSecondHandServiceImpl.insertQuotationSecondHandBatch(result);
			
		}
	}
	
	/**
	 * 二手房详情导入数据专用
	 */
	private void secondHandHouseResource(List result, Sheet sheet, HttpServletRequest request) throws Exception {
		
		
		ExcelCity excelCity = new ExcelCity(request).invoke();
		String cityId = excelCity.getCityId();
		String cityName = excelCity.getCityName();
		String distName = null;
		
		String dateMonth = null;
		String houseType = null;
		
		if (StringUtils.isEmpty(cityId) || StringUtils.isEmpty(cityName)) {
			throw new Exception("请选择城市");
		}
		int rowSize = sheet.getLastRowNum() + 1;
		List<String> areaList = null;
		List<String> soldNum = null;
		for (int j = 0; j < rowSize; j++) {//遍历行
			Row row = sheet.getRow(j);
			if (row == null) {//略过空行
				continue;
			}
			List<String> list = null;
			
			if (j > 0 && j % 2 == 1) {
				areaList = new ArrayList<>();
				list = areaList;
			} else if (j > 0 && j % 2 == 0) {
				soldNum = new ArrayList<>();
				list = soldNum;
				
			}
			int cellSize = row.getLastCellNum();//行中有多少个单元格，也就是有多少列
			for (int k = 0; k < cellSize; k++) {
				Cell cell = row.getCell(k);
				String value = null;
				if (cell != null) {
					;
					value = cell.toString();
				}
				if (j == 0) {//获取日期
					if (k == 1) {
						dateMonth = value;
					}
					continue;
				}
				if (k == 0) {
					;
					if (value != null && !value.equals("")) {
						distName = value;
					}
				} else if (k == 1) {
					if (value != null && !value.equals("")) {
						houseType = value;
					}
				} else if (k == 2) {
					
				} else {
					list.add(value);
				}
				
			}
			Area area;
			if ("全市".equals(distName)) {
				area = new Area();
				area.setId("0");
				area.setName("全市");
			} else {
				area = getArea(cityId, distName);
			}
			if (j > 0 && j % 2 == 0) {
				int length = areaList.size() < 12 ? areaList.size() : 12;
				for (int x = 0; x < length; x++) {
					if (areaList.get(x) != null && !areaList.get(x).equals("")) {
						Quotation quotation = new Quotation();
						quotation.setCityId(cityId);
						quotation.setCityName(cityName);
						quotation.setAreaName(area.getName());
						quotation.setAreaId(area.getId());
						int houseTypeId = getHouseTypeId(houseType);
						int i = x + 1;
						String month = "-" + i;
						String day = "-01";
						quotation.setIsNew(Contant.isOldHouse);
						if (dateMonth != null) {
							quotation.setDataTime(dateMonth + month + day);
						} else {
							throw new Exception("日期错误");
						}
						quotation.setUpdateTime(MidlandHelper.getCurrentTime());
						quotation.setType(houseTypeId);
						quotation.setDealAcreage(String.valueOf(Calculate.keepTwoDecimal(Double.valueOf(areaList.get(x)))));
						quotation.setDealNum(Double.valueOf(soldNum.get(x)).intValue());
						result.add(quotation);
					}
				}
			}
		}
	}
	
	public Area getArea(String cityId, String distName) {
		Map<String, Object> map = new HashedMap();
		String key = cityId + "_" + distName;
		Object obj = map.get(key);
		if (obj == null) {
			Area area = settingServiceImpl.getDistByCityIdAndDistName(cityId, distName);
			map.put(key, area);
			return area;
		} else {
			return (Area) obj;
		}
	}
	
	
	/**
	 * 新房详情导入数据专用
	 */
	private void newHouseResource(List result, Sheet sheet, HttpServletRequest request) throws Exception {
		
		ExcelCity excelCity = new ExcelCity(request).invoke();
		String cityId = excelCity.getCityId();
		String cityName = excelCity.getCityName();
		
		String dateMonth = null;
		String houseType = null;
		String distName = null;
		if (StringUtils.isEmpty(cityId) || StringUtils.isEmpty(cityName)) {
			throw new Exception("请选择城市");
		}
		int rowSize = sheet.getLastRowNum() + 1;
		List<String> dealNum = null;//成交套数
		List<String> dealArea = null;//成交面积
		List<String> dealprice = null;//成交金额
		List<String> dealAvgPriceList = null;//成交均价
		List<String> soldAbleNumList = null;//可售套数
		List<String> soldAbleAreaList = null;//可售面积
		List<String> list = null;
		
		for (int j = 0; j < rowSize; j++) {//遍历行
			Row row = sheet.getRow(j);
			if (row == null) {//略过空行
				continue;
			}
			if (j == 0) {//获取日期
				Cell cell = row.getCell(1);
				String value = null;
				if (cell != null) {
					;
					if (StringUtils.isNotEmpty(cell.toString())){
						
					}
					dateMonth = String.valueOf(Double.valueOf(cell.toString()).intValue());
					if (dateMonth==null){
						throw new Exception("日期错误");
					}
				}else {
					throw new Exception("日期错误");
				}
			}
			int row_27 = j % 27;
			if (j > 0 ) {
				//每个市27行
				
				if (j!=27 && row_27<13){//每个市的前12行，为住宅信息
					int row_4=row_27%4;
					if (row_4==1){
						dealNum=new ArrayList<>();
						list=dealNum;
					}else if (row_4==2){
						dealArea=new ArrayList<>();
						list=dealArea;
					}else if (row_4==3){
						dealAvgPriceList=new ArrayList<>();
						list=dealAvgPriceList;
					}else if (row_4==0){
						dealprice=new ArrayList<>();
						list=dealprice;
					}
					int cellSize = row.getLastCellNum();//行中有多少个单元格，也就是有多少列
					for (int k = 0; k < cellSize; k++) {
						Cell cell = row.getCell(k);
						String value = null;
						if (cell != null) {
							;
							value = cell.toString();
						}
						
						if (k == 0) {
							;
							if (value != null && !value.equals("")) {
								distName = value;
							}
						} else if (k == 1) {
							if (value != null && !value.equals("")) {
								houseType = value;
							}
						} else if (k == 2) {
							
						} else {
							list.add(value);
						}
						
					}
					Area area;
					if ("全市".equals(distName)) {
						area = new Area();
						area.setId("0");
						area.setName("全市");
					} else {
						area = getArea(cityId, distName);
					}
					if (j > 0 && j % 4 == 0) {
						int length = dealNum.size() < 12 ? dealNum.size() : 12;
						for (int x = 0; x < length; x++) {
							if (dealNum.get(x) != null && !dealNum.get(x).equals("")) {
								Quotation quotation = new Quotation();
								quotation.setCityId(cityId);
								quotation.setCityName(cityName);
								quotation.setAreaName(area.getName());
								quotation.setAreaId(area.getId());
								int houseTypeId;
								String houseAcreage=null;
								if (houseType.equals("商业")) {
									houseTypeId = 0;
								} else if (houseType.equals("90㎡以下")) {
									houseTypeId = 1;
									houseAcreage="0";
								} else if (houseType.equals("90~144㎡")) {
									houseTypeId = 1;
									houseAcreage="1";
									
								} else if (houseType.equals("144㎡以上")) {
									houseTypeId = 1;
									houseAcreage="2";
									
								} else if (houseType.equals("其他")) {
									houseTypeId = 2;
									;
								} else if (houseType.equals("办公楼")) {
									houseTypeId = 3;
								} else {
									throw new Exception("房源类型错误：" + houseType);
								}
								int i = x + 1;
								String month = "-" + i;
								String day = "-01";
								quotation.setIsNew(Contant.isOldHouse);
								if (dateMonth != null) {
									quotation.setDataTime(dateMonth + month + day);
								} else {
									throw new Exception("日期错误");
								}
								quotation.setHouseAcreage(houseAcreage);
								quotation.setUpdateTime(MidlandHelper.getCurrentTime());
								quotation.setType(houseTypeId);
								quotation.setDealNum(Double.valueOf(dealNum.get(x)).intValue());
								quotation.setDealAcreage(String.valueOf(Calculate.keepTwoDecimal(Double.valueOf(dealArea.get(x)))));
								quotation.setPrice(MidlandHelper.scientificNotation(dealAvgPriceList.get(x)));
								quotation.setDealPrice(MidlandHelper.scientificNotation(dealprice.get(x)));
								result.add(quotation);
							}
						}
					}
					
				}else{
					//每个市的后16行，为商业、办公楼、其他信息
					int row_5=(j-12)%5;
					if (row_5==1){
						dealNum=new ArrayList<>();
						list=dealNum;
					}else if (row_5==2){
						dealArea=new ArrayList<>();
						list=dealArea;
					}else if (row_5==3){
						dealAvgPriceList=new ArrayList<>();
						list=dealAvgPriceList;
					}else if (row_5==4){
						soldAbleNumList=new ArrayList<>();
						list=soldAbleNumList;
					}else if (row_5==0){
						soldAbleAreaList=new ArrayList<>();
						list=soldAbleAreaList;
					}
					int cellSize = row.getLastCellNum();//行中有多少个单元格，也就是有多少列
					for (int k = 0; k < cellSize; k++) {
						Cell cell = row.getCell(k);
						String value = null;
						if (cell != null) {
							;
							value = cell.toString();
						}
						if (k == 0) {
							;
							if (value != null && !value.equals("")) {
								distName = value;
							}
						} else if (k == 1) {
							if (value != null && !value.equals("")) {
								houseType = value;
							}
						} else if (k == 2) {
							
						} else {
							list.add(value);
						}
						
					}
					Area area;
					if ("全市".equals(distName)) {
						area = new Area();
						area.setId("0");
						area.setName("全市");
					} else {
						area = getArea(cityId, distName);
					}
					if (j > 13 && (j-12) % 5 == 0) {
						int length = dealNum.size() < 12 ? dealNum.size() : 12;
						for (int x = 0; x < length; x++) {
							if (dealNum.get(x) != null && !dealNum.get(x).equals("")) {
								Quotation quotation = new Quotation();
								quotation.setCityId(cityId);
								quotation.setCityName(cityName);
								quotation.setAreaName(area.getName());
								quotation.setAreaId(area.getId());
								int houseTypeId;
								String houseAcreage=null;
								if (houseType.equals("商业")) {
									houseTypeId = 0;
								}  else if (houseType.equals("其他")) {
									houseTypeId = 2;
									;
								} else if (houseType.equals("办公楼")) {
									houseTypeId = 3;
								} else {
									throw new Exception("房源类型错误：" + houseType);
								}
								int i = x + 1;
								String month = "-" + i;
								String day = "-01";
								quotation.setIsNew(Contant.isOldHouse);
								if (dateMonth != null) {
									quotation.setDataTime(dateMonth + month + day);
								} else {
									throw new Exception("日期错误");
								}
								quotation.setUpdateTime(MidlandHelper.getCurrentTime());
								quotation.setType(houseTypeId);
								quotation.setDealAcreage(dealArea.get(x));
								quotation.setDealNum(Double.valueOf(dealNum.get(x)).intValue());
								quotation.setSoldNum(Double.valueOf(soldAbleNumList.get(x)).intValue());
								quotation.setSoldArea(soldAbleAreaList.get(x));
								quotation.setPrice(MidlandHelper.scientificNotation(dealAvgPriceList.get(x)));
								result.add(quotation);
							}
						}
					}
				}
				
			}
			
		}
	}
	
	
	private int getNewHouseTypeId(String houseType) throws Exception {
		int houseTypeId;
		int houseAcreage;
		if (houseType.equals("商业")) {
			houseTypeId = 0;
		} else if (houseType.equals("90㎡以下")) {
			houseTypeId = 1;
		} else if (houseType.equals("90~144㎡")) {
			houseTypeId = 1;
		} else if (houseType.equals("144㎡以上")) {
			houseTypeId = 1;
		} else if (houseType.equals("其他")) {
			houseTypeId = 2;
			;
		} else if (houseType.equals("办公")) {
			houseTypeId = 3;
		} else {
			throw new Exception("房源类型错误：" + houseType);
		}
		return houseTypeId;
	}
	
	private int getHouseTypeId(String houseType) throws Exception {
		int houseTypeId;
		if (houseType.equals("商业")) {
			houseTypeId = 0;
		} else if (houseType.equals("住宅")) {
			houseTypeId = 1;
		} else if (houseType.equals("其他")) {
			houseTypeId = 2;
		} else if (houseType.equals("办公")) {
			houseTypeId = 3;
			;
		} else {
			throw new Exception("房源类型错误：" + houseType);
		}
		return houseTypeId;
	}
	
	
	public String ascii2native(String ascii) {
		int n = ascii.length() / 6;
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0, j = 2; i < n; i++, j += 6) {
			String code = ascii.substring(j, j + 4);
			char ch = (char) Integer.parseInt(code, 16);
			sb.append(ch);
		}
		return sb.toString();
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
	
	private class ExcelCity {
		private HttpServletRequest request;
		private String cityId;
		private String cityName;
		
		public ExcelCity(HttpServletRequest request) {
			this.request = request;
		}
		
		public String getCityId() {
			return cityId;
		}
		
		public String getCityName() {
			return cityName;
		}
		
		public HttpServletRequest getRequest() {
			return request;
		}
		
		public void setRequest(HttpServletRequest request) {
			this.request = request;
		}
		
		public void setCityId(String cityId) {
			this.cityId = cityId;
		}
		
		public void setCityName(String cityName) {
			this.cityName = cityName;
		}
		
		
		public ExcelCity invoke() {
			String provinceId = request.getParameter("provinceId");
			String provinceName = request.getParameter("provinceName");
			if (provinceName != null) {
				provinceName = ascii2native(provinceName);
			}
			if ("上海".equals(provinceName)) {
				cityId = provinceId;
				cityName = provinceName;
			} else {
				cityId = request.getParameter("cityId");
				cityName = ascii2native(request.getParameter("cityName"));
			}
			
			return this;
		}
	}
	
}
