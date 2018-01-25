package com.midland.web.dao;

import com.midland.web.model.Disclaimer;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DisclaimerMapper {

    Disclaimer selectDisclaimerById(Integer disclaimer);

    int deleteDisclaimerById(Integer disclaimer);

    int updateDisclaimerById(Disclaimer disclaimer);

    int insertDisclaimer(Disclaimer disclaimer);

    List<Disclaimer> findDisclaimerList(Disclaimer disclaimer);

}
