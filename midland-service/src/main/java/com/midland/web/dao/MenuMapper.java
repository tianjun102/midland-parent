package com.midland.web.dao;

import com.midland.web.model.Menu;
import java.util.List;

public interface MenuMapper {

	Menu selectMenuById(Integer menu);

	int deleteMenuById(Integer menu);

	int updateMenuById(Menu menu);

	int insertMenu(Menu menu);

	List<Menu> findMenuList(Menu menu);

}
