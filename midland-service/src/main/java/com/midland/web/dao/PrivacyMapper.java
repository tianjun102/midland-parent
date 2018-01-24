package com.midland.web.dao;

import com.midland.web.model.Privacy;
import java.util.List;

public interface PrivacyMapper {

	Privacy selectPrivacyById(Integer privacy);

	int deletePrivacyById(Integer privacy);

	int updatePrivacyById(Privacy privacy);

	int insertPrivacy(Privacy privacy);

	List<Privacy> findPrivacyList(Privacy privacy);

}
