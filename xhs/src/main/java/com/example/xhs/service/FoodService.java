package com.example.xhs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.xhs.dao.FoodDao;
import com.example.xhs.pojo.dto.FoodSearchDto;
import com.example.xhs.pojo.entity.Food;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 美食 service
 * @author wangmeng
 * @since 2021/1/6
 */
@Service
public class FoodService {

    @Autowired
    private FoodDao dao;

    public PageInfo<Food> listPage(FoodSearchDto dto){
        PageHelper.startPage(dto.getPageNum(),dto.getPageSize());
        return new PageInfo<>(dao.list(dto));
    }

    public int batchInsert(List<Food> list){
        list.forEach(Food::preInsert);
        return dao.batchInsert(list);
    }

    public Food get(Integer id){
        return dao.get(id);
    }
}
