package com.midland.controller;

import com.midland.base.BaseFilter;
import com.midland.web.api.ApiHelper;
import com.midland.web.model.Area;
import com.midland.web.model.ResumeManager;
import com.midland.web.service.ResumeManagerService;
import com.midland.web.service.SettingService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;
import java.util.HashMap;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import java.util.List;
import com.midland.web.util.MidlandHelper;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@SuppressWarnings("all")
/**
 * 简历管理控制层
 */
@RequestMapping("/resumeManager/")
public class ResumeManagerController extends BaseFilter {

	private Logger log = LoggerFactory.getLogger(ResumeManagerController.class);
	@Autowired
	private ResumeManagerService resumeManagerServiceImpl;
	@Autowired
	private SettingService settingService;
	@Autowired
	private ApiHelper apiHelper;

	/**
	 * 
	 **/
	@RequestMapping("index")
	public String resumeManagerIndex(ResumeManager resumeManager,Model model) throws Exception {
		Map<String,String> parem = new HashMap<>();
		parem.put("flag","city");
		parem.put("id","*");
		Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
		List<Area> cityList = cityMap.get("city");
		model.addAttribute("cityList",cityList);
		return "resumeManager/resumeManagerIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddResumeManager(ResumeManager resumeManager,Model model) throws Exception {
		Map<String,String> parem = new HashMap<>();
		parem.put("flag","city");
		parem.put("id","*");
		Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
		List<Area> cityList = cityMap.get("city");
		model.addAttribute("cityList",cityList);
		return "resumeManager/addResumeManager";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addResumeManager(ResumeManager resumeManager) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.debug("addResumeManager {}",resumeManager);
			resumeManagerServiceImpl.insertResumeManager(resumeManager);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addResumeManager异常 {}",resumeManager,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_resumeManager")
	public String getResumeManagerById(Integer id,Model model) {
		log.debug("getResumeManagerById  {}",id);
		ResumeManager result = resumeManagerServiceImpl.selectResumeManagerById(id);
		model.addAttribute("item",result);
		return "resumeManager/updateResumeManager";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteResumeManagerById(Integer id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.debug("deleteResumeManagerById  {}",id);
			resumeManagerServiceImpl.deleteResumeManagerById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteResumeManagerById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateResumeManager(Integer id,Model model) throws Exception {
		ResumeManager result = resumeManagerServiceImpl.selectResumeManagerById(id);
		model.addAttribute("item",result);
		return "resumeManager/updateResumeManager";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateResumeManagerById(ResumeManager resumeManager) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.debug("updateResumeManagerById  {}",resumeManager);
			resumeManagerServiceImpl.updateResumeManagerById(resumeManager);
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("3332932@qq.com");
			message.setTo("977543176@qq.com");
			message.setSubject(resumeManager.getTitle());
			message.setText(resumeManager.getReply());
			apiHelper.emailSender("sendSimpleMail",message);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateResumeManagerById  {}",resumeManager,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findResumeManagerList(ResumeManager resumeManager,Model model, HttpServletRequest request) {
		try {
			log.debug("findResumeManagerList  {}",resumeManager);
			MidlandHelper.doPage(request);
			Page<ResumeManager> result = (Page<ResumeManager>)resumeManagerServiceImpl.findResumeManagerList(resumeManager);
			Paginator paginator=result.getPaginator();
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findResumeManagerList  {}",resumeManager,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "resumeManager/resumeManagerList";
	}

	@RequestMapping(value = "/fileDownload", method = RequestMethod.GET)
	@ResponseBody
	public void fileDownload(String filePath, HttpServletRequest request,
							   HttpServletResponse response) {
		Map result = new HashMap();

		File file = new File("D://bg_new.jpg");
		if (!file.exists()) {
			System.out.println("文件不存在");
		}

		try {
			response.reset(); //设置ContentType
			response.setContentType("application/octet-stream; charset=utf-8");
			String fileName = new String(file.getName().getBytes("utf-8"), "ISO-8859-1"); //处理中文文件名中文乱码问题
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
			IOUtils.copy(new FileInputStream(file), response.getOutputStream());
		}
		catch (Exception e) {
			System.out.println("文件下载失败");
		}

	}
}
