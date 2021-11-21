package com.example.xhs.pojo.dto;

import com.example.xhs.common.PageRequestDto;

import lombok.Getter;
import lombok.Setter;

/**
 * TODO
 *
 * @author wangmeng
 * @since 2021/1/11
 */
@Setter
@Getter
public class FoodSearchDto extends PageRequestDto {
private String taste;
private String name;
private String calo;
}
