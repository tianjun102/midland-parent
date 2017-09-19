package com.midland.web.dao;

import com.midland.web.model.EliteClub;
import java.util.List;

public interface EliteClubMapper {

	EliteClub selectEliteClubById(Integer eliteClub);

	int deleteEliteClubById(Integer eliteClub);

	int updateEliteClubById(EliteClub eliteClub);

	int insertEliteClub(EliteClub eliteClub);

	List<EliteClub> findEliteClubList(EliteClub eliteClub);

}
