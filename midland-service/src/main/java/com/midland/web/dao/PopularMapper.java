package com.midland.web.dao;

import com.midland.web.model.Popular;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PopularMapper {

	Popular selectById(Integer popular);

	int deleteById(Integer popular);

	int updateById(Popular popular);

	Integer getMaxOrderBy(Popular popular);

	int insertPopular(Popular popular);

	List<Popular> findPopularList(Popular popular);

	int batchUpdate(@Param("popularList") List<Popular> popularList);

}
