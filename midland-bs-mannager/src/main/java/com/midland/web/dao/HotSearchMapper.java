package com.midland.web.dao;

import com.midland.web.model.HotSearch;
import java.util.List;

public interface HotSearchMapper {

	HotSearch selectHotSearchById(Integer hotSearch);

	int deleteHotSearchById(Integer hotSearch);

	int updateHotSearchById(HotSearch hotSearch);

	int insertHotSearch(HotSearch hotSearch);

	List<HotSearch> findHotSearchList(HotSearch hotSearch);

}
