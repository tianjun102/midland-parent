package com.midland.web.dao;

import com.midland.web.model.Entrust;
import com.midland.web.model.ErrorPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ErrorPageMapper {

	ErrorPage selectErrorPageById(Integer errorPage);

	int deleteErrorPageById(Integer errorPage);

	int updateErrorPageById(ErrorPage errorPage);

	int insertErrorPage(ErrorPage errorPage);

	List<ErrorPage> findErrorPageList(ErrorPage errorPage);

	int batchUpdate(@Param("errorPageList") List<ErrorPage> errorPageList);

}
