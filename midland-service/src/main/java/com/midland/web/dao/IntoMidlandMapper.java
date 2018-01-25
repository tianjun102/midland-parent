package com.midland.web.dao;

import com.midland.web.model.IntoMidland;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntoMidlandMapper {

    IntoMidland selectIntoMidlandById(Integer intoMidland);

    int deleteIntoMidlandById(Integer intoMidland);

    int updateIntoMidlandById(IntoMidland intoMidland);

    int insertIntoMidland(IntoMidland intoMidland);

    List<IntoMidland> findIntoMidlandList(IntoMidland intoMidland);

}
