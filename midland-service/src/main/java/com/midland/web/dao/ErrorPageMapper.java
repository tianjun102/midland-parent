package com.midland.web.dao;

import com.midland.web.model.ErrorPage;
import java.util.List;

public interface ErrorPageMapper {

	ErrorPage selectErrorPageById(Integer errorPage);

	int deleteErrorPageById(Integer errorPage);

	int updateErrorPageById(ErrorPage errorPage);

	int insertErrorPage(ErrorPage errorPage);

	List<ErrorPage> findErrorPageList(ErrorPage errorPage);

}
