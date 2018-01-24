package com.midland.web.dao;

import com.midland.web.model.Disclaimer;
import java.util.List;

public interface DisclaimerMapper {

	Disclaimer selectDisclaimerById(Integer disclaimer);

	int deleteDisclaimerById(Integer disclaimer);

	int updateDisclaimerById(Disclaimer disclaimer);

	int insertDisclaimer(Disclaimer disclaimer);

	List<Disclaimer> findDisclaimerList(Disclaimer disclaimer);

}
