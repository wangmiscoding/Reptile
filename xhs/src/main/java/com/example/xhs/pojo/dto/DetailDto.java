package com.example.xhs.pojo.dto;

import com.example.xhs.pojo.entity.Attraction;
import com.example.xhs.pojo.entity.Film;
import com.example.xhs.pojo.entity.Food;
import com.example.xhs.pojo.entity.Hotel;
import com.github.pagehelper.PageInfo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wangm
 * @since 2021/3/24
 */
@Setter
@Getter
public class DetailDto {
    private PageInfo<CommentListDto> commentList;
    private String content;
    private String title;
    private Integer categoryId;
    private Integer itemId;
    private Food food;
    private Film film;
    private Attraction attraction;
    private Hotel hotel;
    private Integer rate;
}
