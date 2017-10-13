package com.midland.web.dao;

import com.midland.web.model.City;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CityMapper {

	City selectById(Integer city);

	int deleteById(Integer city);

	int updateById(City city);

	int insertCity(City city);

	List<City> findCityList(City city);

}
