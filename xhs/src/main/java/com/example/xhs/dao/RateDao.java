package com.example.xhs.dao;

import com.example.xhs.pojo.entity.Rate;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RateDao {

    int insert(Rate entity);

    Rate get(@Param("userId") String userId,@Param("itemId") Integer itemId,@Param("categoryId") Integer categoryId);

    Integer calAvg(@Param("itemId") Integer itemId,@Param("categoryId") Integer categoryId);

    int update(Rate entity);
}
