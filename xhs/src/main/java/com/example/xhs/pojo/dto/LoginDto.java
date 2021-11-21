package com.example.xhs.pojo.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wangm
 * @since 2021/1/30
 */
@Setter
@Getter
public class LoginDto {
    private String usernameOrMail;
    private String password;
}
