package com.example.xhs.pojo.entity;

import com.example.xhs.common.DataEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wangm
 * @since 2021/5/2
 */
@Setter
@Getter
public class FoodStep extends DataEntity {
    private String foodId;
    private String img;
    private String content;
}
