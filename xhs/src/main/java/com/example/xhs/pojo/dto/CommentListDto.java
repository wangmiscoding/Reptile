package com.example.xhs.pojo.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.example.xhs.common.DataEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wangm
 * @since 2021/1/30
 */
@Setter
@Getter
public class CommentListDto extends DataEntity {
    private String content;
    private Integer admire;
    private String username;
    private String ename;
    private String img;
    private Date createDate;
    private String createDateStr;
    private List<ReplyListDto> replyList;


    public String getCreateDateStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
        String str = sdf.format(createDate);
        return str;
    }
}
