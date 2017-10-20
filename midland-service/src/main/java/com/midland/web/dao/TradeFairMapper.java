package com.midland.web.dao;

import com.midland.web.model.SpecialPage;
import com.midland.web.model.TradeFair;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TradeFairMapper {

	TradeFair selectTradeFairById(Integer tradeFair);

	int deleteTradeFairById(Integer tradeFair);

	int updateTradeFairById(TradeFair tradeFair);

	int insertTradeFair(TradeFair tradeFair);

	List<TradeFair> findTradeFairList(TradeFair tradeFair);

	int batchUpdate(@Param("tradeFairList") List<TradeFair> tradeFairList);

}
