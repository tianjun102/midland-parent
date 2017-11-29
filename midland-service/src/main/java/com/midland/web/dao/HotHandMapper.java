package com.midland.web.dao;

import com.midland.web.model.HotHand;
import java.util.List;

public interface HotHandMapper {

	HotHand selectHotHandById(Integer hotHand);

	int deleteHotHandById(Integer hotHand);

	int updateHotHandById(HotHand hotHand);

	int insertHotHand(HotHand hotHand);
	int getMaxOrderBy();

	List<HotHand> findHotHandList(HotHand hotHand);

}
