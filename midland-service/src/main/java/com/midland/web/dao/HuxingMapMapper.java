package com.midland.web.dao;

import com.midland.web.model.HuxingMap;
import java.util.List;

public interface HuxingMapMapper {

	HuxingMap selectHuxingMapById(Integer huxingMap);

	int deleteHuxingMapById(Integer huxingMap);

	int updateHuxingMapById(HuxingMap huxingMap);

	int insertHuxingMap(HuxingMap huxingMap);

	List<HuxingMap> findHuxingMapList(HuxingMap huxingMap);

}
