package com.midland.web.dao;

import com.midland.web.model.PageConf;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageConfMapper {

    PageConf selectPageConfById(Integer pageConf);

    int deletePageConfById(Integer pageConf);

    int updatePageConfById(PageConf pageConf);

    int insertPageConf(PageConf pageConf);

    List<PageConf> findPageConfList(PageConf pageConf);

    List<PageConf> findRestPageConfList(PageConf pageConf);

    int batchUpdate(@Param("pageConfList") List<PageConf> pageConfList);

}
