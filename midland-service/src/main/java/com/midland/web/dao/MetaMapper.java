package com.midland.web.dao;

import com.midland.web.model.Meta;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetaMapper {

	Meta selectMetaById(Integer meta);

	int deleteMetaById(Integer meta);

	int updateMetaById(Meta meta);

	int insertMeta(Meta meta);
	int ifExist(Meta meta);
	int ifExist_update(Meta meta);

	List<Meta> findMetaList(Meta meta);
	Meta shiftUp(Meta menu);

	Meta shiftDown(Meta menu);

}
