package com.example.xhs.service;

import com.example.xhs.dao.AttractionDao;
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
public class AttractionService {

    @Autowired
    private AttractionDao dao;

    public PageInfo<Attraction> listPage(FilmSearchDto dto){
        PageHelper.startPage(dto.getPageNum(),dto.getPageSize());
        return new PageInfo<>(dao.list(dto));
    }


    public Attraction get(Integer id) {
        return dao.get(id);
    }
}
