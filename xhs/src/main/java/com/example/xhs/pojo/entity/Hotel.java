package com.example.xhs.pojo.entity;

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
public class Hotel extends DataEntity {
    private String name;
    private String tag;
    private String score;
    private String address;
    private String price;
    private String img;
}
