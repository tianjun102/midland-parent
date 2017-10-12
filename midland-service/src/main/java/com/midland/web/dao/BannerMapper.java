package com.midland.web.dao;

import com.midland.web.model.Banner;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BannerMapper {

	Banner selectById(Integer banner);

	int deleteById(Integer banner);

	int updateById(Banner banner);

	int insertBanner(Banner banner);

	List<Banner> findBannerList(Banner banner);

}
