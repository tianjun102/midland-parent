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
    int getCountByCateId(int cateId);



    int insertPopular(Popular popular);

    Popular shiftUp(Popular popular);
    Popular shiftUp_Up(Popular popular);

    Popular shiftDown(Popular popular);
    Popular shiftDown_Down(Popular popular);

    List<Popular> findPopularList(Popular popular);
    List<Popular> findCateGory(Popular popular);

    int batchUpdate(@Param("popularList") List<Popular> popularList);

}
