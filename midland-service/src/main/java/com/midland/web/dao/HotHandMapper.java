package com.midland.web.dao;

import com.midland.web.model.HotHand;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotHandMapper {

    HotHand selectHotHandById(Integer hotHand);

    HotHand shiftUp(HotHand hotHand);

    HotHand shiftDown(HotHand hotHand);

    int deleteHotHandById(Integer hotHand);

    int updateHotHandById(HotHand hotHand);

    int insertHotHand(HotHand hotHand);


    List<HotHand> findHotHandList(HotHand hotHand);

}
