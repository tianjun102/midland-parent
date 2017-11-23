package com.midland.web.dao;

import com.midland.web.model.CommunityAlbum;
import java.util.List;

public interface CommunityAlbumMapper {

	CommunityAlbum selectCommunityAlbumById(Integer communityAlbum);

	int deleteCommunityAlbumById(Integer communityAlbum);

	int updateCommunityAlbumById(CommunityAlbum communityAlbum);

	int insertCommunityAlbum(CommunityAlbum communityAlbum);

	List<CommunityAlbum> findCommunityAlbumList(CommunityAlbum communityAlbum);

}
