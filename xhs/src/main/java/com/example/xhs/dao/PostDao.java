package com.example.xhs.dao;

import java.util.List;

import com.example.xhs.pojo.dto.CommentListDto;
import com.example.xhs.pojo.dto.FilmSearchDto;
import com.example.xhs.pojo.dto.PostDto;
import com.example.xhs.pojo.dto.ReplyListDto;

import org.springframework.stereotype.Repository;

@Repository
public interface PostDao {

    List<PostDto> list(FilmSearchDto dto);

    int add(PostDto dto);

    PostDto get(Integer id);

    List<CommentListDto> listComment(Integer id);

    List<ReplyListDto> listReply(Integer id);
}
