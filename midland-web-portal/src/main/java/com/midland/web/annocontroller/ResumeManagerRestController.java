package com.midland.web.annocontroller;

import com.google.common.collect.Maps;
import com.midland.core.util.UploadImgUtil;
import com.midland.web.Contants.Contant;
import com.midland.web.commons.FastJsonUtils;
import com.midland.web.model.ResumeManager;
import com.midland.web.service.ResumeManagerService;
import com.midland.base.ServiceBaseFilter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ResultStatusUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import java.util.Map;
import java.util.UUID;

import com.midland.web.util.MidlandHelper;
import javax.servlet.http.HttpServletRequest;

@RestController
/**
 * 简历
 */
@SuppressWarnings("all")
@RequestMapping("/resumeManager/")
public class ResumeManagerRestController extends ServiceBaseFilter {

	private Logger log = LoggerFactory.getLogger(ResumeManagerRestController.class);
	@Autowired
	private ResumeManagerService resumeManagerServiceImpl;

	/**
	 * 新增
	 **/
//	@RequestMapping("add")
//	public Object addResumeManager(@RequestBody ResumeManager obj) throws Exception {
//		 Result result=new Result();
//		try {
//			log.info("addResumeManager {}",obj);
//			obj.setIsDelete(Contant.isNotDelete);
//			resumeManagerServiceImpl.insertResumeManager(obj);
//			result.setCode(ResultStatusUtils.STATUS_CODE_200);
//			result.setMsg("success");
//		} catch(Exception e) {
//			log.error("addResumeManager异常 {}",obj,e);
//			result.setCode(ResultStatusUtils.STATUS_CODE_203);
//			result.setMsg("service error");
//		}
//		return result;
//	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getResumeManagerById(@RequestBody Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getResumeManagerById  {}",id);
			ResumeManager resumeManager = resumeManagerServiceImpl.selectResumeManagerById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(resumeManager);
		} catch(Exception e) {
			log.error("getResumeManager异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateResumeManagerById(@RequestBody ResumeManager obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateResumeManagerById  {}",obj);
			resumeManagerServiceImpl.updateResumeManagerById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateResumeManagerById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findResumeManagerList(@RequestBody ResumeManager  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findResumeManagerList  {}",obj);
			MidlandHelper.doPage(request);
			obj.setIsDelete(Contant.isNotDelete);
			Page<ResumeManager> list = (Page<ResumeManager>)resumeManagerServiceImpl.findResumeManagerList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findResumeManagerList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 上传简历
	 * @param params :fileContent
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String changeHeadImg(@RequestBody ResumeManager resumeManager, HttpServletRequest request) {
		Result result = new Result<>();
		try {
			String enclosureName = resumeManager.getEnclosureName();
			String code = resumeManager.getEnclosureCode();
			String path = "/home/upload/resume/";
			if (code == null ||StringUtils.isEmpty(enclosureName)) {
				result.setCode(ResultStatusUtils.STATUS_CODE_202);
				result.setMsg("请求参数为空!");
				return FastJsonUtils.toJSONStr(result);
			}
			String[] temp = enclosureName.split("\\.");
			if (temp.length<1){
				result.setCode(ResultStatusUtils.STATUS_CODE_202);
				result.setMsg("文件名格式不正确");
				return FastJsonUtils.toJSONStr(result);
			}

			String fileName= UUID.randomUUID().toString()+enclosureName;
			String filePath = UploadImgUtil.GenerateFile(fileName,code,path,"");
			resumeManager.setEnclosureUrl(filePath);
			resumeManagerServiceImpl.insertResumeManager(resumeManager);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg(Result.resultMsg.SUCCESS.toString());
		} catch (Exception e) {
			result.setCode(ResultStatusUtils.STATUS_CODE_500);
			result.setMsg("上传文件失败。");
			log.error("上传文件失败", e);
		}
		return FastJsonUtils.toJSONStr(result);
	}

	/**
	 * \
	 * @param params
	 * @param fileName 要把文件保存的名称
	 * @param oldFileName 旧文件的名称,删除
	 * @return
	 */
	private String getUploadFilePath(@RequestBody Map<String, String> params,String fileName,String oldFileName) {
		Map<String, String> map = Maps.newHashMap();
		map.put("path", "/home/upload/resume/");
		map.put("fileName", fileName);
		map.put("oldImg", oldFileName);
		map.put("imgContent", params.get("fileContent"));
		return UploadImgUtil.GenerateImage(map);
	}


}
