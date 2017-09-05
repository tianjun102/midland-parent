package com.midland.web.dao;

import com.midland.web.model.PageConf;
import java.util.List;

public interface PageConfMapper {

	PageConf selectPageConfById(Integer pageConf);

	int deletePageConfById(Integer pageConf);

	int updatePageConfById(PageConf pageConf);

	int insertPageConf(PageConf pageConf);

	List<PageConf> findPageConfList(PageConf pageConf);

}
