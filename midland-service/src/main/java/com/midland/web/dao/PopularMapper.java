package com.midland.web.dao;

import com.midland.web.model.Popular;
import java.util.List;

public interface PopularMapper {

	Popular selectById(Integer popular);

	int deleteById(Integer popular);

	int updateById(Popular popular);

	int insertPopular(Popular popular);

	List<Popular> findPopularList(Popular popular);

}
