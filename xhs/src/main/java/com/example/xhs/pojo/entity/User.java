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
public class User extends DataEntity {
    private String username;
    private String password;
    private String email;
    private String ename;
    private String phone;
    private String img;
    private Integer type;
}
