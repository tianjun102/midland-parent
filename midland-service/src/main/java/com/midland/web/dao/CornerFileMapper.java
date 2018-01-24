package com.midland.web.dao;

import com.midland.web.model.CornerFile;
import java.util.List;

public interface CornerFileMapper {

	CornerFile selectCornerFileById(Integer cornerFile);

	int deleteCornerFileById(Integer cornerFile);

	int updateCornerFileById(CornerFile cornerFile);

	int insertCornerFile(CornerFile cornerFile);

	List<CornerFile> findCornerFileList(CornerFile cornerFile);

}
