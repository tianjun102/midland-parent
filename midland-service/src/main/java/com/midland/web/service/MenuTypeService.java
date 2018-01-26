package com.midland.web.service;

import com.midland.web.model.MenuType;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MenuTypeService {

    /**
     * 主键查询
     **/
    MenuType selectMenuTypeById(Integer id);

    /**
     * 主键删除
     **/
    void deleteMenuTypeById(Integer id) throws Exception;

    @Transactional
    void shiftUp(MenuType menuType) throws Exception;

    @Transactional
    void shiftDown(MenuType menuType) throws Exception;

    /**
     * 主键更新
     **/
    void updateMenuTypeById(MenuType menuType) throws Exception;

    /**
     * 插入
     **/
    void insertMenuType(MenuType menuType) throws Exception;

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    List<MenuType> findMenuTypeList(MenuType menuType) throws Exception;

    List<MenuType> findRootMenuTypeList() throws Exception;

    List<MenuType> findRootMenuTypeList1() throws Exception;


    String findRootMenuTypeTree(MenuType menuType) throws Exception;

    void batchUpdate(List<MenuType> list);
}
