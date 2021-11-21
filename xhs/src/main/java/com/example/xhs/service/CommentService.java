package com.example.xhs.service;

import com.example.xhs.dao.CommentDao;
import com.example.xhs.pojo.dto.CommentListDto;
import com.example.xhs.pojo.dto.CommentSearchDto;
import com.example.xhs.pojo.entity.Comment;
import com.example.xhs.pojo.entity.Reply;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author wangmeng
 * @since 2021/1/6
 */
@Service
public class CommentService {
    @Autowired
    private CommentDao dao;

    public PageInfo<CommentListDto> listPage(CommentSearchDto dto) {
        PageHelper.startPage(dto.getPageNum(), 100);
        return new PageInfo<>(dao.list(dto));
    }

    public void insert(Comment comment){
        dao.insert(comment);
    }

    public void insertReply(Reply reply){
        dao.insertReply(reply);
    }


}
