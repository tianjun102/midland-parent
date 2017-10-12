package com.midland.web.service.impl;

import com.midland.web.model.Menu;
import com.midland.web.dao.MenuMapper;
import com.midland.web.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class MenuServiceImpl implements MenuService {

	private Logger log = LoggerFactory.getLogger(MenuServiceImpl.class);
	@Autowired
	private MenuMapper menuMapper;

	/**
	 * 插入
	 **/
	@Override
	public void insertMenu(Menu menu) throws Exception {
		try {
			log.debug("insert {}",menu);
			menuMapper.insertMenu(menu);
		} catch(Exception e) {
			log.error("insertMenu异常 {}",menu,e);
			throw e;
		}
	}

	/**
	 * 查询
	 **/
	@Override
	public Menu selectMenuById(Integer id) {
		log.debug("selectMenuById  {}",id);
		return menuMapper.selectMenuById(id);
	}

	/**
	 * 删除
	 **/
	@Override
	public void deleteMenuById(Integer id)throws Exception {
		try {
			log.debug("deleteMenuById  {}",id);
			int result = menuMapper.deleteMenuById(id);
			if (result < 1) {
				throw new Exception("deleteMenuById失败");
			}
		} catch(Exception e) {
			log.error("deleteMenuById  {}",id,e);
			throw e;
		}
	}
	/**
	 * 更新
	 **/
	@Override
	public void updateMenuById(Menu menu) throws Exception {
		try {
			log.debug("updateMenuById  {}",menu);
			int result = menuMapper.updateMenuById(menu);
			if (result < 1) {
				throw new Exception("updateMenuById失败");
			}
		} catch(Exception e) {
			log.error("updateMenuById  {}",menu,e);
			throw e;
		}
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@Override
	public List<Menu> findMenuList(Menu menu) throws Exception {
		try {
			log.debug("findMenuList  {}",menu);
			return menuMapper.findMenuList(menu);
		} catch(Exception e) {
			log.error("findMenuList  {}",menu,e);
			throw e;
		}
	}

	@Override
	public int getMaxOrderBy() {
		Integer result = menuMapper.getMaxOrderBy();
		return result==null?0:result+1;
	}
}
