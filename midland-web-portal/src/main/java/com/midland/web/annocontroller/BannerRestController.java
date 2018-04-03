package com.midland.web.annocontroller;

import com.midland.web.Contants.Contant;
import com.midland.web.model.Banner;
import com.midland.web.service.BannerService;
import com.midland.base.ServiceBaseFilter;
import org.slf4j.Logger;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ResultStatusUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import java.util.Map;
import com.midland.web.util.MidlandHelper;
import javax.servlet.http.HttpServletRequest;
@CrossOrigin(origins = "*", maxAge = 360)
@RestController
@SuppressWarnings("all")
@RequestMapping("/banner/")
/**
 * banner管理接口
 **/
public class BannerRestController extends ServiceBaseFilter {

	private Logger log = LoggerFactory.getLogger(BannerRestController.class);
	@Autowired
	private BannerService bannerServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addBanner(@RequestBody Banner obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addBanner {}",obj);
			obj.setIsDelete(Contant.isNotDelete);
			bannerServiceImpl.insertBanner(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addBanner异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getBannerById(@RequestBody Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getBannerById  {}",id);
			Banner banner = bannerServiceImpl.selectById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(banner);
		} catch(Exception e) {
			log.error("getBanner异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateBannerById(@RequestBody Banner obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateBannerById  {}",obj);
			bannerServiceImpl.updateById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateBannerById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findBannerList(@RequestBody Banner  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			obj.setEnabled(1);
			obj.setIsDelete(Contant.isNotDelete);
			log.info("findBannerList  {}",obj);
			MidlandHelper.doPage(request);
			Page<Banner> list = (Page<Banner>)bannerServiceImpl.findRestBannerList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findBannerList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 点击量
	 **/
	@RequestMapping("clickNum")
	public Object clickNum(@RequestBody Banner banner) throws Exception {
		Result result=new Result();
		try {
			if (banner.getId()==null){
				result.setCode(ResultStatusUtils.STATUS_CODE_202);
				result.setMsg("id不能为空!");
				return result;
			}
			log.info("updateBannerById  {}",banner);
			banner = bannerServiceImpl.selectById(banner.getId());
			Integer clickNum = banner.getClikNum();
			banner.setClikNum(clickNum==null?1:clickNum+1);
			bannerServiceImpl.updateById(banner);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateBannerById  {}",banner,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
