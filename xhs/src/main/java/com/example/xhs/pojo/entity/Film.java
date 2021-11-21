package com.example.xhs.pojo.entity;

import com.example.xhs.common.DataEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wangm
 * @since 2021/2/1
 */
@Setter
@Getter
public class Film extends DataEntity {
    private String filmName;
    private String score;
    private String country;
    private String releaseTime;
    private String filmIntro;
    private String imgUrl;
    private String mainActor;
    private String filmType;
    private String uniqueId;
    private Integer commentCount;

}
