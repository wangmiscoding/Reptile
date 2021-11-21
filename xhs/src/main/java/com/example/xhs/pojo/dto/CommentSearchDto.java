package com.example.xhs.pojo.dto;

import com.example.xhs.common.PageRequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * TODO
 *
 * @author wangmeng
 * @since 2021/1/11
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentSearchDto extends PageRequestDto {
    private Integer categoryId;
    private Integer itemId;
}
