package com.midland.web.service;

import com.midland.web.model.LinkUrlManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LinkUrlManagerService {

    /**
     * 主键查询
     **/
    LinkUrlManager selectById(Integer id);

    /**
     * 主键删除
     **/
    void deleteById(Integer id) throws Exception;

    /**
     * 主键更新
     **/
    void updateById(LinkUrlManager linkUrlManager) throws Exception;

    /**
     * 插入
     **/
    void insertLinkUrlManager(LinkUrlManager linkUrlManager) throws Exception;

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    List<LinkUrlManager> findLinkUrlManagerList(LinkUrlManager linkUrlManager) throws Exception;
	
	@Transactional
	void shiftUp(LinkUrlManager category) throws Exception;
	
	@Transactional
	void shiftDown(LinkUrlManager linkUrlManager) throws Exception;
	
	void batchUpdate(List<LinkUrlManager> linkUrlManagerList) throws Exception;
}
