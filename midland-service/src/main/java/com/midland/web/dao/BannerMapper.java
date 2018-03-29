package com.midland.web.dao;

import com.midland.web.model.Banner;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BannerMapper {

    Banner selectById(Integer banner);

    int deleteById(Integer banner);

    int updateById(Banner banner);

    int insertBanner(Banner banner);

    List<Banner> findBannerList(Banner banner);
    List<Banner> findRestBannerList(Banner banner);

    int batchUpdate(@Param("bannerList") List<Banner> bannerList);

    Banner shiftUp(Banner banner);

    Banner shiftDown(Banner banner);

}
