package com.midland.web.dao;

import com.midland.web.model.Footer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FooterMapper {

    Footer selectFooterById(Integer footer);

    Footer getFooter();

    int deleteFooterById(Integer footer);

    int updateFooterById(Footer footer);

    int insertFooter(Footer footer);

    List<Footer> findFooterList(Footer footer);

    int batchUpdate(@Param("footerList") List<Footer> footerList);

}
