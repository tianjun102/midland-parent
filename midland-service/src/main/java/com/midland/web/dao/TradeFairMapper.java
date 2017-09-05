package com.midland.web.dao;

import com.midland.web.model.TradeFair;
import java.util.List;

public interface TradeFairMapper {

	TradeFair selectTradeFairById(Integer tradeFair);

	int deleteTradeFairById(Integer tradeFair);

	int updateTradeFairById(TradeFair tradeFair);

	int insertTradeFair(TradeFair tradeFair);

	List<TradeFair> findTradeFairList(TradeFair tradeFair);

}
