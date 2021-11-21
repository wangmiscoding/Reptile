package com.example.xhs.pojo.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
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
public class PostDto extends DataEntity {

    private String theme;
    private String userId;
    private String username;
    private String password;
    private String email;
    private String ename;
    private String phone;
    private String img;
    private String title;
    private Integer type;
    private List<CommentListDto> list;
    private Date createDate;
    private Integer commentCount;

    public String getCreateDateStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
        String str = sdf.format(createDate);
        return str;
    }
}
