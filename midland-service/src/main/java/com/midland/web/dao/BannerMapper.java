package com.midland.web.dao;

import com.midland.web.model.Banner;
import java.util.List;

public interface BannerMapper {

	Banner selectById(Integer banner);

	int deleteById(Integer banner);

	int updateById(Banner banner);

	int insertBanner(Banner banner);

	List<Banner> findBannerList(Banner banner);

}
