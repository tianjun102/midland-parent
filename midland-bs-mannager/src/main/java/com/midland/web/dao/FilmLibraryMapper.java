package com.midland.web.dao;

import com.midland.web.model.FilmLibrary;
import java.util.List;

public interface FilmLibraryMapper {

	FilmLibrary selectFilmLibraryById(Integer filmLibrary);

	int deleteFilmLibraryById(Integer filmLibrary);

	int updateFilmLibraryById(FilmLibrary filmLibrary);

	int insertFilmLibrary(FilmLibrary filmLibrary);

	List<FilmLibrary> findFilmLibraryList(FilmLibrary filmLibrary);

}
