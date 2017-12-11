package com.midland.web.dao;

import com.midland.web.model.HotHand;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@Repository
public interface HotHandMapper {

	HotHand selectHotHandById(Integer hotHand);

	int deleteHotHandById(Integer hotHand);

	int updateHotHandById(HotHand hotHand);

	int insertHotHand(HotHand hotHand);
	Integer getMaxOrderBy();

	List<HotHand> findHotHandList(HotHand hotHand);

}
