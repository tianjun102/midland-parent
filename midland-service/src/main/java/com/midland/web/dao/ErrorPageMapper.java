package com.midland.web.dao;

import com.midland.web.model.ErrorPage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ErrorPageMapper {

    ErrorPage selectErrorPageById(Integer errorPage);

    int deleteErrorPageById(Integer errorPage);

    int updateErrorPageById(ErrorPage errorPage);

    int insertErrorPage(ErrorPage errorPage);

    List<ErrorPage> findErrorPageList(ErrorPage errorPage);

    int batchUpdate(@Param("errorPageList") List<ErrorPage> errorPageList);

}
