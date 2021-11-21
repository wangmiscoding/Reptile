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
public class Comment extends DataEntity {
    private String content;
    private Integer itemId;
    private Integer categoryId;
    private Integer admire;

}
