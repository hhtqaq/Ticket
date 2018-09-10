package com.ecjtu.rwx.dao;

import java.util.List;

import com.ecjtu.rwx.bean.City;

public interface CityDao {

	/**
	 * 查询所有的城市
	 * @param condition 条件
	 * @return
	 */
	List<City> findAllCity(String condition);

	void addCity(String name);

}
