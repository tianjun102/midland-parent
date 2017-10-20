package com.midland.web.dao;

import com.midland.web.model.Feedback;
import com.midland.web.model.FilmLibrary;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FilmLibraryMapper {

	FilmLibrary selectFilmLibraryById(Integer filmLibrary);

	int deleteFilmLibraryById(Integer filmLibrary);

	int updateFilmLibraryById(FilmLibrary filmLibrary);

	int insertFilmLibrary(FilmLibrary filmLibrary);

	List<FilmLibrary> findFilmLibraryList(FilmLibrary filmLibrary);

	int batchUpdate(@Param("filmLibraryList") List<FilmLibrary> filmLibraryList);

}
