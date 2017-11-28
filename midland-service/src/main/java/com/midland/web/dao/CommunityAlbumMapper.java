package com.midland.web.dao;

import com.midland.web.model.CommunityAlbum;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommunityAlbumMapper {

	CommunityAlbum selectCommunityAlbumById(Integer communityAlbum);

	int deleteCommunityAlbumById(Integer communityAlbum);

	int updateCommunityAlbumById(CommunityAlbum communityAlbum);

	int getMaxOrderBy(CommunityAlbum communityAlbum);

	int insertCommunityAlbum(CommunityAlbum communityAlbum);

	List<CommunityAlbum> findCommunityAlbumList(CommunityAlbum communityAlbum);

}
