package com.midland.web.service.impl;

import com.midland.web.model.Category;
import com.midland.web.dao.CategoryMapper;
import com.midland.web.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {

	private Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);
	@Autowired
	private CategoryMapper categoryMapper;

	/**
	 * 插入
	 **/
	@Override
	public void insertCategory(Category category) throws Exception {
		try {
			log.info("insert {}",category);
			categoryMapper.insertCategory(category);
		} catch(Exception e) {
			log.error("insertCategory异常 {}",category,e);
			throw e;
		}
	}

	/**
	 * 查询
	 **/
	@Override
	public Category selectCategoryById(Integer id) {
		log.info("selectCategoryById  {}",id);
		return categoryMapper.selectCategoryById(id);
	}

	/**
	 * 删除
	 **/
	@Override
	public void deleteCategoryById(Integer id)throws Exception {
		try {
			log.info("deleteCategoryById  {}",id);
			int result = categoryMapper.deleteCategoryById(id);
			if (result < 1) {
				throw new Exception("deleteCategoryById失败");
			}
		} catch(Exception e) {
			log.error("deleteCategoryById  {}",id,e);
			throw e;
		}
	}
	/**
	 * 更新
	 **/
	@Override
	public void updateCategoryById(Category category) throws Exception {
		try {
			log.info("updateCategoryById  {}",category);
			int result = categoryMapper.updateCategoryById(category);
			if (result < 1) {
				throw new Exception("updateCategoryById失败");
			}
		} catch(Exception e) {
			log.error("updateCategoryById  {}",category,e);
			throw e;
		}
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@Override
	public List<Category> findCategoryList(Category category) throws Exception {
		try {
			log.info("findCategoryList  {}",category);
			return categoryMapper.findCategoryList(category);
		} catch(Exception e) {
			log.error("findCategoryList  {}",category,e);
			throw e;
		}
	}
}
