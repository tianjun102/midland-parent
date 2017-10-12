package com.midland.web.dao;

import com.midland.web.model.TradeFair;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TradeFairMapper {

	TradeFair selectTradeFairById(Integer tradeFair);

	int deleteTradeFairById(Integer tradeFair);

	int updateTradeFairById(TradeFair tradeFair);

	int insertTradeFair(TradeFair tradeFair);

	List<TradeFair> findTradeFairList(TradeFair tradeFair);

}
