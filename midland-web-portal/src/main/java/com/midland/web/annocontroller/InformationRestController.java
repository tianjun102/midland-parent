package com.midland.web.annocontroller;

import com.midland.web.Contants.Contant;
import com.midland.web.model.Comment;
import com.midland.web.model.Information;
import com.midland.web.model.Meta;
import com.midland.web.service.CommentService;
import com.midland.web.service.InformationService;
import com.midland.base.ServiceBaseFilter;
import com.midland.web.service.MetaService;
import com.midland.web.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ResultStatusUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.midland.web.util.MidlandHelper;
import javax.servlet.http.HttpServletRequest;
@RestController
@SuppressWarnings("all")
@RequestMapping("/information/")
public class InformationRestController extends ServiceBaseFilter {

	private Logger log = LoggerFactory.getLogger(InformationRestController.class);
	@Autowired
	private InformationService informationServiceImpl;
	@Autowired
	private CommentService commentServiceImpl;
	@Autowired
	private RedisService redisServiceImpl;
	@Autowired
	private MetaService metaServiceImpl;
	@RequestMapping("/banner/get")
	@ResponseBody
	public Object getinformationBannerOpenAndClose(Integer id) {
		Result result=new Result();
		Map  map = new HashMap();
		try {
			int flag = redisServiceImpl.getInformationOpenFlag();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			map.put("flag",flag);
			result.setModel(map);
		} catch (Exception e) {
			log.error("openAudit ",e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
			map.put("flag",1);
			result.setModel(map);
		}
		return result;
	}
	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getInformationById(@RequestBody Map map) {
		 Result result=new Result();
		try {
			Integer id =Integer.valueOf(String.valueOf(map.get("id")));
			log.info("getInformationById  {}",id);
			Information information = informationServiceImpl.selectInformationById(id);
			if (information != null){
				Comment comment = new Comment();
				comment.setInformationId(id);
				Integer commentNum = 0;
				try {
					commentNum = commentServiceImpl.commentTotle(comment);
				}catch (Exception e){
					log.info("取评论数出错！");
				}
				information.setCommentNum(commentNum);
				information.setClickNum(information.getClickNum()==null?1:information.getClickNum()+1);
				informationServiceImpl.updateInformationById(information);
			}
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(information);
		} catch(Exception e) {
			log.error("getInformation异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}



	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findInformationList(@RequestBody Information  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			if (StringUtils.isEmpty(obj.getCityId())||obj.getSource()==null||obj.getArticeType()==null){
				result.setMsg("cityId,source,articeType不能为空");
				result.setCode(ResultStatusUtils.STATUS_CODE_202);
				return result;
			}
			Meta meta = new Meta();
			meta.setCityId(obj.getCityId());
			if (obj.getArticeType()==0){//市场研究
				meta.setModeId(9);//跟metaIndex页面的modeId对应
			}else if(obj.getArticeType()==1){//资讯
				meta.setModeId(10);//跟metaIndex页面的modeId对应
			}

			meta.setSecondModeId(obj.getCateId());
			meta.setSource(0);
			meta.setIsDelete(Contant.isNotDelete);
			List<Meta> res =  metaServiceImpl.findMetaList(meta);
			if (res.size()>0){
				result.setMeta(res.get(0));
			}
			log.info("findInformationList  {}",obj);
			MidlandHelper.doPage(request);
			obj.setIsDelete(Contant.isNotDelete);
			obj.setStatus(Contant.PUBLISH_UP);//上架
			Page<Information> list = (Page<Information>)informationServiceImpl.findInformationList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findInformationList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("newest")
	public Object findNewestInformationList(@RequestBody Information  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findInformationList  {}",obj);
			MidlandHelper.doPage(request);
			obj.setIsDelete(Contant.isNotDelete);
			obj.setStatus(Contant.PUBLISH_UP);//上架
			Page<Information> list = (Page<Information>)informationServiceImpl.findNewestInformationList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findInformationList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
