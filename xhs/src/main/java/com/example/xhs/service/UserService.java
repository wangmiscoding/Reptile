package com.example.xhs.service;

import java.util.List;

import com.example.xhs.dao.FoodDao;
import com.example.xhs.dao.UserDao;
import com.example.xhs.pojo.dto.FoodSearchDto;
import com.example.xhs.pojo.dto.LoginDto;
import com.example.xhs.pojo.entity.Food;
import com.example.xhs.pojo.entity.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户 service
 * @author wangmeng
 * @since 2021/1/6
 */
@Service
public class UserService {

    @Autowired
    private UserDao dao;

  public List<User> listAll(){
      return dao.list();
  }

    /**
     * 根据用户名或邮箱匹配
     * @param dto
     * @return
     */
  public User get(LoginDto dto){
      return dao.get(dto);
  }

    public User getById(Integer id){
        return dao.getById(id);
    }

  @Transactional
  public String registerCheck(User user){
      User usernameResult = dao.checkUsername(user.getUsername());
      //账号已存在
      if(usernameResult != null) {
              return "username_exist";
      }
      //邮箱已存在
      User emailResult = dao.checkEmail(user.getEmail());
      if(emailResult !=null){
          return "email_exist";
      }
      //通过验证则将用户添加至数据库
      dao.insert(user);
      return "success";
  }

  @Transactional
  public void updateImg(User user){
      dao.updateImg(user);
  }


  @Transactional
    public void update(User user){
      dao.update(user);
  }
}
