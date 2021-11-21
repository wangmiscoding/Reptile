package com.example.xhs.common;


import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 数据Entity类(请勿擅自修改)
 *
 */
@Getter
@Setter
public abstract class DataEntity {

    private static final long serialVersionUID = 1L;
    /**
     * 实体编号(唯一标识)dto
     */
    private String id;

    private Integer delFlag; // 删除标记（0：正常；1：删除）
    private Date createDate; // 创建日期
    private Date updateDate; // 更新日期
    private String createBy; // 更新日期

    //插入前初始化，手动调用
    public void preInsert(){
        createDate=new Date();

    }





}
