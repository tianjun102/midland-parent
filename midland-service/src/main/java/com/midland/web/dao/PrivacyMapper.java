package com.midland.web.dao;

import com.midland.web.model.Privacy;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PrivacyMapper {

    Privacy selectPrivacyById(Integer privacy);

    int deletePrivacyById(Integer privacy);

    int updatePrivacyById(Privacy privacy);

    int insertPrivacy(Privacy privacy);

    List<Privacy> findPrivacyList(Privacy privacy);

}
