package com.example.xhs.dao;

import java.util.List;

import com.example.xhs.pojo.dto.LoginDto;
import com.example.xhs.pojo.dto.ShowDto;
import com.example.xhs.pojo.entity.User;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDao {

    List<User> list();

    User get(LoginDto dto);

    User checkUsername(String username);

    User checkEmail(String email);

    int insert(User user);

    int updateImg(User user);

    int update(User user);

    User getById(Integer id);

    List<ShowDto> listByPer(@Param("list") List<String> list);

}
