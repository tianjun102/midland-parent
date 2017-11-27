package com.midland.web.dao;

import com.midland.web.model.LayoutMap;
import java.util.List;

public interface LayoutMapMapper {

	LayoutMap selectLayoutMapById(Integer layoutMap);

	int deleteLayoutMapById(Integer layoutMap);

	int updateLayoutMapById(LayoutMap layoutMap);

	int insertLayoutMap(LayoutMap layoutMap);

	List<LayoutMap> findLayoutMapList(LayoutMap layoutMap);

}
