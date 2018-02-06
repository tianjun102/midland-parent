package com.midland.web.service;

import com.midland.web.model.Category;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CategoryService {

    @Transactional
    void shiftUp(Category category) throws Exception;

    @Transactional
    void shiftDown(Category category) throws Exception;

    /**
     * 主键查询
     **/
    Category selectCategoryById(Integer id);

    /**
     * 主键删除
     **/
    void deleteCategoryById(Integer id) throws Exception;

    /**
     * 主键更新
     **/
    void updateCategoryById(Category category) throws Exception;

    /**
     * 插入
     **/
    void insertCategory(Category category) throws Exception;

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    List<Category> findCategoryList(Category category) throws Exception;

    List<Category> findCategoryList1(Category category) throws Exception;

    List<Category> findCategoryListFromCityIdById(Category category) throws Exception;

    List<Category> findCategoryListByIdList(List ids) throws Exception;

    List<Category> findCategoryTreeList(Category category) throws Exception;

    Category selectCategoryParentNameById(Integer id);

    void batchUpdate(List<Category> categoryList) throws Exception;

    public List<Category> findleveCategory(Category category) throws Exception;

}
