package com.midland.web.dao;

import com.midland.web.model.IntoMidland;
import java.util.List;

public interface IntoMidlandMapper {

	IntoMidland selectIntoMidlandById(Integer intoMidland);

	int deleteIntoMidlandById(Integer intoMidland);

	int updateIntoMidlandById(IntoMidland intoMidland);

	int insertIntoMidland(IntoMidland intoMidland);

	List<IntoMidland> findIntoMidlandList(IntoMidland intoMidland);

}
