package com.example.xhs.pojo.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wangm
 * @since 2021/1/30
 */
@Setter
@Getter
public class CommentInsertDto {
    private String content;
    private String commentId;
    private Integer category;
    private Integer itemId;
    private Integer value;
    private Integer postId;
}
