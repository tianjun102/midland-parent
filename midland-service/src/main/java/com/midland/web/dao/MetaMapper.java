package com.midland.web.dao;

import com.midland.web.model.Meta;
import java.util.List;

public interface MetaMapper {

	Meta selectMetaById(Integer meta);

	int deleteMetaById(Integer meta);

	int updateMetaById(Meta meta);

	int insertMeta(Meta meta);

	List<Meta> findMetaList(Meta meta);
	Meta shiftUp(Meta menu);

	Meta shiftDown(Meta menu);

}
