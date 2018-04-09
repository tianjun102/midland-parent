package com.midland.web.annocontroller;

import com.midland.web.Contants.Contant;
import com.midland.web.model.Quotation;
import com.midland.web.service.QuotationService;
import com.midland.base.ServiceBaseFilter;
import org.slf4j.Logger;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ResultStatusUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import java.util.Map;
import com.midland.web.util.MidlandHelper;
import javax.servlet.http.HttpServletRequest;
@RestController
@SuppressWarnings("all")
@RequestMapping("/quotation/")
public class QuotationRestController extends ServiceBaseFilter {

	private Logger log = LoggerFactory.getLogger(QuotationRestController.class);
	@Autowired
	private QuotationService quotationServiceImpl;



	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getQuotationById(@RequestBody Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getQuotationById  {}",id);
			Quotation quotation = quotationServiceImpl.selectQuotationById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(quotation);
		} catch(Exception e) {
			log.error("getQuotation异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}



	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findQuotationList(@RequestBody Quotation  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findQuotationList  {}",obj);
			MidlandHelper.doPage(request);
			obj.setIsDelete(Contant.isNotDelete);
			Page<Quotation> list = (Page<Quotation>)quotationServiceImpl.findQuotationList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findQuotationList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
