package com.midland.web.dao;

import com.midland.web.model.EliteClub;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EliteClubMapper {

	EliteClub selectEliteClubById(Integer eliteClub);

	int deleteEliteClubById(Integer eliteClub);

	int updateEliteClubById(EliteClub eliteClub);

	int insertEliteClub(EliteClub eliteClub);

	List<EliteClub> findEliteClubList(EliteClub eliteClub);

	int batchUpdate(@Param("eliteClubList") List<EliteClub> eliteClubList);

}
