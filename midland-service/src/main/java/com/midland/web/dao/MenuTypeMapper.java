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

	List<MenuType> findMenuTypeList(MenuType menuType);

	List<MenuType> findRootMenuTypeList();

}
