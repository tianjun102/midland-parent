package com.midland.web.annocontroller;

import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.base.BaseFilter;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ResultStatusUtils;
import com.midland.web.model.Reply;
import com.midland.web.service.ReplyService;
import com.midland.web.util.MidlandHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@SuppressWarnings("all")
@RequestMapping("/reply/")
public class ReplyRestController extends BaseFilter {

    private Logger log = LoggerFactory.getLogger(ReplyRestController.class);
    @Autowired
    private ReplyService replyServiceImpl;


    /**
     * 新增
     **/
    @RequestMapping("add")
    public Object addRecord(@RequestBody Reply obj) throws Exception {
        Result result=new Result();
        try {
            log.info("addRecord {}",obj);
            replyServiceImpl.insertReply(obj);
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("success");
        } catch(Exception e) {
            log.error("addRecord异常 {}",obj,e);
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("service error");
        }
        return result;
    }

    /**
     * 查询
     **/
    @RequestMapping("get")
    public Object getRecordById(Map map) {
        Result result=new Result();
        try {
            Integer id =(Integer)map.get("id");
            log.info("getRecordById  {}",id);
            Reply record = replyServiceImpl.selectReplyById(id);
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("success");
            result.setModel(record);
        } catch(Exception e) {
            log.error("getRecord异常 {}",map,e);
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("service error");
        }
        return result;
    }


    /**
     * 更新
     **/
    @RequestMapping("update")
    public Object updateRecordById(@RequestBody Reply obj) throws Exception {
        Result result=new Result();
        try {
            log.info("updateRecordById  {}",obj);
            replyServiceImpl.updateReplyById(obj);
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("success");
        } catch(Exception e) {
            log.error("updateRecordById  {}",obj,e);
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("service error");
        }
        return result;
    }

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @RequestMapping("list")
    public Object findReplyList(Reply reply,Model model, HttpServletRequest request) {
        Result result=new Result();
        try {
            log.info("findReplyList  {}",reply);
            MidlandHelper.doPage(request);
            Page<Reply> list = (Page<Reply>)replyServiceImpl.findReplyList(reply);
            Paginator paginator=list.getPaginator();
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("success");
            result.setList(list);
            result.setPaginator(paginator);
        } catch(Exception e) {
            log.error("findRecordList  {}",reply,e);
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("service error");
        }
        return result;
    }
}
