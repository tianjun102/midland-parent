package com.midland.web.dao;

import com.midland.web.model.CornerFile;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CornerFileMapper {

    CornerFile selectCornerFileById(Integer cornerFile);

    int deleteCornerFileById(Integer cornerFile);

    int updateCornerFileById(CornerFile cornerFile);

    int insertCornerFile(CornerFile cornerFile);

    List<CornerFile> findCornerFileList(CornerFile cornerFile);

}
