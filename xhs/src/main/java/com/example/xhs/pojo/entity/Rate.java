package com.example.xhs.pojo.entity;

import com.example.xhs.common.DataEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wangm
 * @since 2021/1/30
 */
@Setter
@Getter
public class Rate extends DataEntity {
    private Integer itemId;
    private Integer categoryId;
    private Integer star;
    private String userId;
}
