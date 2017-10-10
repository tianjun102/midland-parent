package com.midland.web.dao;

import com.midland.web.model.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MenuMapper {

	Menu selectMenuById(Integer menu);

	int deleteMenuById(Integer menu);

	int updateMenuById(Menu menu);

	int insertMenu(Menu menu);

	Integer getMaxOrderBy();

	List<Menu> findMenuList(Menu menu);

}
