package com.example.xhs.service;

import com.example.xhs.dao.HotelDao;
import com.example.xhs.pojo.dto.FilmSearchDto;
import com.example.xhs.pojo.entity.Attraction;
import com.example.xhs.pojo.entity.Hotel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author wangmeng
 * @since 2021/1/6
 */
@Service
public class HotelService {

    @Autowired
    private HotelDao dao;

    public PageInfo<Hotel> listPage(FilmSearchDto dto){
        PageHelper.startPage(dto.getPageNum(),dto.getPageSize());
        return new PageInfo<>(dao.list(dto));
    }


    public Hotel get(Integer id) {
        return dao.get(id);
    }
}
