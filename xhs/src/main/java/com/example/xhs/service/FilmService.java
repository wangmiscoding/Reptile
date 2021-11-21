package com.example.xhs.service;

import java.util.List;

import com.example.xhs.dao.FilmDao;
import com.example.xhs.pojo.dto.FilmSearchDto;
import com.example.xhs.pojo.entity.Film;
import com.example.xhs.service.inter.Product;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * TODO
 *
 * @author wangmeng
 * @since 2021/1/6
 */
@Service
public class FilmService implements Product<Film> {

    @Autowired
    private FilmDao dao;

    public List<Film> list(String filmName){
        FilmSearchDto dto=new FilmSearchDto();
        dto.setFilmName(filmName);
        return dao.list(dto);
    }

    public PageInfo<Film> listPage(FilmSearchDto dto){
        //开启分页
        PageHelper.startPage(dto.getPageNum(),dto.getPageSize());
        return new PageInfo<>(dao.list(dto));
    }
    @Transactional
    public void batchInsert(List<Film> list){
        list.forEach(Film::preInsert);
        dao.batchInsert(list);
    }


    public List<Film> listAll(){
        return dao.listAll();
    }

    @Override
    public Film get(Integer id) {
        return dao.get(id);
    }
}
