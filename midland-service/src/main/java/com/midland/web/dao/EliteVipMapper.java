package com.midland.web.dao;

import com.midland.web.model.EliteVip;
import java.util.List;

public interface EliteVipMapper {

	EliteVip selectEliteVipById(Integer eliteVip);

	int deleteEliteVipById(Integer eliteVip);

	int updateEliteVipById(EliteVip eliteVip);

	int insertEliteVip(EliteVip eliteVip);

	List<EliteVip> findEliteVipList(EliteVip eliteVip);

}
