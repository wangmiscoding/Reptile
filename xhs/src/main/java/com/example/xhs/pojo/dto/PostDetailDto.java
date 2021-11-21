package com.example.xhs.pojo.dto;

import java.util.List;

import com.example.xhs.common.DataEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wangm
 * @since 2021/5/2
 */
@Setter
@Getter
public class PostDetailDto extends DataEntity {

    private PostDto detail;
    private List<CommentListDto> list;
}
