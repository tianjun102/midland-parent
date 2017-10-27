package com.midland.web.service.impl;

import com.midland.web.model.MenuType;
import com.midland.web.dao.MenuTypeMapper;
import com.midland.web.service.MenuTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MenuTypeServiceImpl implements MenuTypeService {

	private Logger log = LoggerFactory.getLogger(MenuTypeServiceImpl.class);
	@Autowired
	private MenuTypeMapper menuTypeMapper;

	/**
	 * 插入
	 **/
	@Override
	public void insertMenuType(MenuType menuType) throws Exception {
		try {
			log.info("insert {}",menuType);
			menuTypeMapper.insertMenuType(menuType);
		} catch(Exception e) {
			log.error("insertMenuType异常 {}",menuType,e);
			throw e;
		}
	}

	/**
	 * 查询
	 **/
	@Override
	public MenuType selectMenuTypeById(Integer id) {
		log.info("selectMenuTypeById  {}",id);
		return menuTypeMapper.selectMenuTypeById(id);
	}

	/**
	 * 删除
	 **/
	@Override
	public void deleteMenuTypeById(Integer id)throws Exception {
		try {
			log.info("deleteMenuTypeById  {}",id);
			int result = menuTypeMapper.deleteMenuTypeById(id);
			if (result < 1) {
				throw new Exception("deleteMenuTypeById失败");
			}
		} catch(Exception e) {
			log.error("deleteMenuTypeById  {}",id,e);
			throw e;
		}
	}
	/**
	 * 更新
	 **/
	@Override
	public void updateMenuTypeById(MenuType menuType) throws Exception {
		try {
			log.info("updateMenuTypeById  {}",menuType);
			int result = menuTypeMapper.updateMenuTypeById(menuType);
			if (result < 1) {
				throw new Exception("updateMenuTypeById失败");
			}
		} catch(Exception e) {
			log.error("updateMenuTypeById  {}",menuType,e);
			throw e;
		}
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@Override
	public List<MenuType> findMenuTypeList(MenuType menuType) throws Exception {
		try {
			log.info("findMenuTypeList  {}",menuType);
			return menuTypeMapper.findMenuTypeList(menuType);
		} catch(Exception e) {
			log.error("findMenuTypeList  {}",menuType,e);
			throw e;
		}
	}

	@Override
	public List<MenuType> findRootMenuTypeList() throws Exception {
		try {
			List<MenuType> result = findRootMenuTypeList1();
			if (result==null){
				result = new ArrayList<>();
			}
			MenuType menuType = new MenuType();
			menuType.setId(0);
			menuType.setName("分类");
			result.add(0,menuType);
			return result;
		} catch(Exception e) {
			log.error("findMenuTypeList  ",e);
			throw e;
		}
	}
	@Override
	public List<MenuType> findRootMenuTypeList1() throws Exception {
		try {
			return menuTypeMapper.findRootMenuTypeList();
		} catch(Exception e) {
			log.error("findMenuTypeList  ",e);
			throw e;
		}
	}
	@Override
	public String findRootMenuTypeTree(MenuType menuType) throws Exception {
		try {
			List<MenuType> list =  menuTypeMapper.findMenuTypeTree(menuType);

			StringBuffer ret = new StringBuffer("");
			if (list != null   &&  list.size()>0) {
				for (int i = 0; i < list.size(); i++) {
					MenuType cat = list.get(i);
					ret.append("{id:").append(cat.getId()).append(", pId:").append(cat.getParentId())
							.append(", name:'").append(cat.getName()).append("',open:true,nocheck:true");

					if(!("0".equals(cat.getParentId().toString()))){
						ret.append(",iconSkin:'pIcon03'");
					}

					ret.append("},");
				}
				return ret.substring(0, ret.length() - 1);
			}

			return "";
		} catch(Exception e) {
			log.error("findMenuTypeList  ",e);
			throw e;
		}
	}

	@Override
	public void batchUpdate(List<MenuType> list){
		menuTypeMapper.batchUpdate(list);
	}
}
