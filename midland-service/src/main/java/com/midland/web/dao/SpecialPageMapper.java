package com.midland.web.dao;

import com.midland.web.model.SpecialPage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialPageMapper {

    SpecialPage selectSpecialPageById(Integer specialPage);

    int deleteSpecialPageById(Integer specialPage);

    int updateSpecialPageById(SpecialPage specialPage);

    int insertSpecialPage(SpecialPage specialPage);

    List<SpecialPage> findSpecialPageList(SpecialPage specialPage);

    int batchUpdate(@Param("specialPageList") List<SpecialPage> specialPageList);

    SpecialPage shiftUp(SpecialPage specialPage);

    SpecialPage shiftDown(SpecialPage specialPage);
}
