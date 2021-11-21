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
public class Post extends DataEntity {

    private String theme;
    private String userId;
}
