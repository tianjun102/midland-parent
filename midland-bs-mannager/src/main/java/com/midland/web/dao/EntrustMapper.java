package com.midland.web.dao;

import com.midland.web.model.Entrust;
import java.util.List;

public interface EntrustMapper {

	Entrust selectEntrustById(Integer entrust);

	int deleteEntrustById(Integer entrust);

	int updateEntrustById(Entrust entrust);

	int insertEntrust(Entrust entrust);

	List<Entrust> findEntrustList(Entrust entrust);

}
