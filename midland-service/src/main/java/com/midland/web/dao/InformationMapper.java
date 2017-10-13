package com.midland.web.dao;

import com.midland.web.model.Information;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface InformationMapper {

	Information selectInformationById(Integer information);

	int deleteInformationById(Integer information);

	int updateInformationById(Information information);

	int insertInformation(Information information);

	List<Information> findInformationList(Information information);

}
