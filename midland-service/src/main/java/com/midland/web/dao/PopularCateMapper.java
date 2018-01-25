package com.midland.web.dao;

import com.midland.web.model.PopularCate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PopularCateMapper {

    PopularCate selectPopularCateById(Integer popularCate);

    int deletePopularCateById(Integer popularCate);

    int updatePopularCateById(PopularCate popularCate);

    int insertPopularCate(PopularCate popularCate);

    List<PopularCate> findPopularCateList(PopularCate popularCate);

}
