package com.example.xhs.dao;

import java.util.List;

import com.example.xhs.pojo.dto.FilmSearchDto;
import com.example.xhs.pojo.dto.FoodSearchDto;
import com.example.xhs.pojo.entity.Film;
import com.example.xhs.pojo.entity.Food;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmDao {

	List<Film> list(FilmSearchDto dto);

	int batchInsert(List<Film> list);

	List<Film> listAll();

	Film get(Integer id);

	List<Film> listByRate();
}
