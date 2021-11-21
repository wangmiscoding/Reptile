package com.example.xhs.controller.login;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.xhs.dao.CommentDao;
import com.example.xhs.dao.FilmDao;
import com.example.xhs.dao.FoodDao;
import com.example.xhs.dao.PostDao;
import com.example.xhs.pojo.dto.CommentListDto;
import com.example.xhs.pojo.dto.CommentSearchDto;
import com.example.xhs.pojo.dto.FilmSearchDto;
import com.example.xhs.pojo.dto.PersonalizeDto;
import com.example.xhs.pojo.dto.PostDetailDto;
import com.example.xhs.pojo.dto.PostDto;
import com.example.xhs.pojo.dto.ShowDto;
import com.example.xhs.pojo.entity.Film;
import com.example.xhs.pojo.entity.Food;
import com.example.xhs.pojo.entity.User;
import com.example.xhs.service.FoodService;
import com.example.xhs.service.ShowService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wangmeng
 * @since 2021/1/6
 */
@Controller
@RequestMapping
public class IndexController {

    @Autowired
    PostDao dao;
    @Autowired
    CommentDao commentDao;
    @Autowired
    private ShowService showService;
    @Autowired
    FoodDao foodDao;
    @Autowired
    FilmDao filmDao;

    /**
     * 跳转至注册页面
     *
     * @return
     */
    @RequestMapping("index")
    public String toRegister(FilmSearchDto dto,HttpServletRequest request, Model model) {
        dto.setPageSize(10);
        PageHelper.startPage(dto.getPageNum(),dto.getPageSize());
        PageInfo<PostDto> page = new PageInfo<>(dao.list(dto));
        List<Food> foods = foodDao.listByRate();
        foods = foods.subList(0, 10);
        List<Film> filmList = filmDao.listByRate().subList(0, 10);
        model.addAttribute("page", page);
        model.addAttribute("filmList", filmList);
        model.addAttribute("foodList", foods);
        return "comment/jie/index";
    }

    @RequestMapping("post")
    public String post(FilmSearchDto dto,HttpServletRequest request, Model model) {

        return "comment/jie/add";
    }

    @PostMapping("add-post")
    public String post(PostDto dto,HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        dto.setUserId(user.getId());
        dto.setCreateDate(new Date());
        dao.add(dto);
        return "redirect:/index";
    }


    @RequestMapping("detail2")
    public String detail2(FilmSearchDto dto,HttpServletRequest request, Model model) {

        return "comment/jie/detail2";
    }

    @GetMapping("post-detail")
    public String postDetail(Integer id,Model model,HttpServletRequest request){
        PostDto postDto = dao.get(id);
        List<CommentListDto> commentList = dao.listComment(id);
        commentList.forEach(item->{
            item.setReplyList(dao.listReply(Integer.parseInt(item.getId())));
        });
        PostDetailDto result = new PostDetailDto();
        result.setDetail(postDto);
        result.setList(commentList);
        model.addAttribute("result",result);
        HttpSession session = request.getSession();
        PersonalizeDto personalizeDto = (PersonalizeDto) session.getAttribute("personalize");
        List<ShowDto> show = showService.show(personalizeDto);
        model.addAttribute("show", show);
        return "comment/jie/detail2";

    }

}
