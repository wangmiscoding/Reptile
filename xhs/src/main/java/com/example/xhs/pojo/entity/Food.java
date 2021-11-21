package com.example.xhs.pojo.entity;

import java.util.List;

import com.example.xhs.common.DataEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * TODO
 *
 * @author wangmeng
 * @since 2021/1/8
 */
@Getter
@Setter
@ToString
public class Food extends DataEntity {
    private String name;
    private String taste;
    private String img;
    private String calo;
    private String categoryId;
    private String mainCom;
    private String subCom;
    private String component;
    private Integer commentCount;
    private List<FoodStep> stepList;
    private String uniqueId;
}
