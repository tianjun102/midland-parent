package com.midland.web.dao;

import com.midland.web.model.PopularCate;
import java.util.List;

public interface PopularCateMapper {

	PopularCate selectPopularCateById(Integer popularCate);

	int deletePopularCateById(Integer popularCate);

	int updatePopularCateById(PopularCate popularCate);

	int insertPopularCate(PopularCate popularCate);

	List<PopularCate> findPopularCateList(PopularCate popularCate);

}
