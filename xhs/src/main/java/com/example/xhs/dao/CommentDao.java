package com.example.xhs.dao;

import java.util.List;

import com.example.xhs.pojo.dto.CommentListDto;
import com.example.xhs.pojo.dto.CommentSearchDto;
import com.example.xhs.pojo.entity.Comment;
import com.example.xhs.pojo.entity.Reply;

import org.springframework.stereotype.Repository;

@Repository
public interface CommentDao {

    List<CommentListDto> list(CommentSearchDto dto);

    int insert(Comment entity);

    int insertReply(Reply entity);
}
