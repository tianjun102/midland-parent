package com.midland.web.dao;

import com.midland.web.model.SpecialPage;
import java.util.List;

public interface SpecialPageMapper {

	SpecialPage selectSpecialPageById(Integer specialPage);

	int deleteSpecialPageById(Integer specialPage);

	int updateSpecialPageById(SpecialPage specialPage);

	int insertSpecialPage(SpecialPage specialPage);

	List<SpecialPage> findSpecialPageList(SpecialPage specialPage);

}
