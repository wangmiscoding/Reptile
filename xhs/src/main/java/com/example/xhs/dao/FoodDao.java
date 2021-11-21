package com.example.xhs.dao;

import java.util.List;

import com.example.xhs.pojo.dto.FoodSearchDto;
import com.example.xhs.pojo.entity.Food;
import com.example.xhs.pojo.entity.FoodStep;

import org.springframework.stereotype.Repository;

@Repository
public interface FoodDao {

    List<Food> list(FoodSearchDto dto);

    int batchInsert(List<Food> list);

    Food get(Integer id);

    List<Food> listByRate();

    int batchInsertStep(List<FoodStep> list);

    List<FoodStep> listFoodStep(String uniqueId);
}
