package com.midland.web.dao;

import com.midland.web.model.Footer;
import java.util.List;

public interface FooterMapper {

	Footer selectFooterById(Integer footer);
	Footer getFooter();

	int deleteFooterById(Integer footer);

	int updateFooterById(Footer footer);

	int insertFooter(Footer footer);

	List<Footer> findFooterList(Footer footer);

}
