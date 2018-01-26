package com.midland.web.dao;

import com.midland.web.model.MenuType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuTypeMapper {

    MenuType selectMenuTypeById(Integer menuType);

    int deleteMenuTypeById(Integer menuType);

    int updateMenuTypeById(MenuType menuType);

    int insertMenuType(MenuType menuType);
    MenuType shiftUp(MenuType menuType);
    MenuType shiftDown(MenuType menuType);

    List<MenuType> findMenuTypeList(MenuType menuType);

    List<MenuType> findRootMenuTypeList();

    List<MenuType> findMenuTypeTree(MenuType menuType);

    int batchUpdate(List<MenuType> list);
}
