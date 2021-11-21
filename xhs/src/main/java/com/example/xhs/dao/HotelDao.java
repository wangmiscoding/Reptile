package com.example.xhs.dao;

import java.util.List;

import com.example.xhs.pojo.dto.FilmSearchDto;
import com.example.xhs.pojo.entity.Hotel;

import org.springframework.stereotype.Repository;

@Repository
public interface HotelDao {


    List<Hotel> list(FilmSearchDto dto);


    Hotel get(Integer id);
}
