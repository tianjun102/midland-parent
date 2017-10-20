package com.midland.web.dao;

import com.midland.web.model.LiaisonRecord;
import com.midland.web.model.LinkUrlManager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LinkUrlManagerMapper {

	LinkUrlManager selectById(Integer linkUrlManager);

	int deleteById(Integer linkUrlManager);

	int updateById(LinkUrlManager linkUrlManager);

	int insertLinkUrlManager(LinkUrlManager linkUrlManager);

	List<LinkUrlManager> findLinkUrlManagerList(LinkUrlManager linkUrlManager);

	int batchUpdate(@Param("linkUrlManagerList") List<LinkUrlManager> linkUrlManagerList);

}
