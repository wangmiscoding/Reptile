package com.example.xhs.controller.comment;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.xhs.common.SensitiveWordBiz;
import com.example.xhs.dao.FoodDao;
import com.example.xhs.dao.RateDao;
import com.example.xhs.pojo.dto.CommentInsertDto;
import com.example.xhs.pojo.dto.CommentListDto;
import com.example.xhs.pojo.dto.CommentSearchDto;
import com.example.xhs.pojo.dto.DetailDto;
import com.example.xhs.pojo.dto.PersonalizeDto;
import com.example.xhs.pojo.dto.ShowDto;
import com.example.xhs.pojo.entity.Attraction;
import com.example.xhs.pojo.entity.Comment;
import com.example.xhs.pojo.entity.Film;
import com.example.xhs.pojo.entity.Food;
import com.example.xhs.pojo.entity.Hotel;
import com.example.xhs.pojo.entity.Rate;
import com.example.xhs.pojo.entity.Reply;
import com.example.xhs.pojo.entity.User;
import com.example.xhs.service.AttractionService;
import com.example.xhs.service.CommentService;
import com.example.xhs.service.FilmService;
import com.example.xhs.service.FoodService;
import com.example.xhs.service.HotelService;
import com.example.xhs.service.ShowService;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wangm
 * @since 2021/1/23
 */
@Controller
@RequestMapping(value = "comment")
public class CommentController {
    @Autowired
    private CommentService service;
    @Autowired
    private FilmService filmService;
    @Autowired
    private FoodService foodService;
    @Autowired
    private ShowService showService;
    @Autowired
    private AttractionService attractionService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private FoodDao foodDao;
    @Autowired
    private RateDao rateDao;
    public static Set<String> source = new HashSet<>();




    @GetMapping
    public String getCommentByProduct(CommentSearchDto dto, Model model, HttpServletRequest request) {
        User u = (User) request.getSession().getAttribute("user");
        if (u == null) {
            return "login";
        }
        int rate = Optional.ofNullable(rateDao.calAvg(dto.getItemId(), dto.getCategoryId())).orElse(0);
        if (dto.getCategoryId() == 1) {
            Film film = filmService.get(dto.getItemId());
            PageInfo<CommentListDto> info = service.listPage(dto);
            DetailDto detailDto = new DetailDto();
            detailDto.setFilm(film);
            detailDto.setCommentList(info);
            detailDto.setContent(film.getFilmIntro());
            detailDto.setTitle(film.getFilmName());
            detailDto.setCategoryId(1);
            detailDto.setItemId(dto.getItemId());
            detailDto.setRate(rate);
            model.addAttribute("detail", detailDto);
        }
        if (dto.getCategoryId() == 2) {
            Food food = foodService.get(dto.getItemId());
            food.setStepList(foodDao.listFoodStep(food.getUniqueId()));
            PageInfo<CommentListDto> info = service.listPage(dto);
            DetailDto detailDto = new DetailDto();
            detailDto.setFood(food);
            detailDto.setCommentList(info);
            detailDto.setContent(food.getComponent());
            detailDto.setTitle(food.getName());
            detailDto.setCategoryId(2);
            detailDto.setItemId(dto.getItemId());
            detailDto.setRate(rate);
            model.addAttribute("detail", detailDto);
        }
        if (dto.getCategoryId() == 3) {
            Attraction entity = attractionService.get(dto.getItemId());
            PageInfo<CommentListDto> info = service.listPage(dto);
            DetailDto detailDto = new DetailDto();
            detailDto.setAttraction(entity);
            detailDto.setCommentList(info);
            detailDto.setContent("null");
            detailDto.setTitle(entity.getName());
            detailDto.setCategoryId(3);
            detailDto.setItemId(dto.getItemId());
            detailDto.setRate(rate);
            model.addAttribute("detail", detailDto);
        }
        if (dto.getCategoryId() == 4) {
            Hotel entity = hotelService.get(dto.getItemId());
            PageInfo<CommentListDto> info = service.listPage(dto);
            DetailDto detailDto = new DetailDto();
            detailDto.setHotel(entity);
            detailDto.setCommentList(info);
            detailDto.setContent("null");
            detailDto.setTitle(entity.getName());
            detailDto.setCategoryId(4);
            detailDto.setItemId(dto.getItemId());
            detailDto.setRate(rate);
            model.addAttribute("detail", detailDto);
        }
        //个性化
        //从session获取
        HttpSession session = request.getSession();
        PersonalizeDto personalizeDto = (PersonalizeDto) session.getAttribute("personalize");
        List<ShowDto> show = showService.show(personalizeDto);
        model.addAttribute("show", show);
        return "comment/jie/detail";
    }


    @PostMapping
    public String addComment(CommentInsertDto dto, HttpServletRequest request) {
        source.add("傻逼");
        source.add("智障");
        source.add("卢本伟");
        source.add("操");
        source.add("妈");
        //敏感词过滤
        SensitiveWordBiz wordInit = new SensitiveWordBiz();
        //加载词库
        Map map = wordInit.addSensitiveWordToHashMap(source);
        String output = wordInit.replaceSensitiveWord(dto.getContent(), "*", map);
        System.out.println(output);
        HttpSession session = request.getSession();
        //从session获取用户
        User user = (User) session.getAttribute("user");
        if (dto.getCommentId().equals("null")) {
            Comment comment = new Comment();
            comment.setContent(output);
            comment.setCategoryId(dto.getCategory());
            comment.setItemId(dto.getItemId());
            comment.setCreateBy(user.getId());
            comment.preInsert();
            service.insert(comment);
        } else {
            Reply reply = new Reply();
            reply.setCommentId(Integer.parseInt(dto.getCommentId()));
            reply.setContent(output);
            reply.setCreateBy(user.getId());
            reply.preInsert();
            service.insertReply(reply);
        }
        if(dto.getCategory()==5){
            return "redirect:/post-detail?id="+dto.getItemId();
        }

        return "redirect:/comment?categoryId=" + dto.getCategory() + "&itemId=" + dto.getItemId();

    }


    @GetMapping("rate")
    @ResponseBody
    public String rate(CommentInsertDto dto, HttpServletRequest request) {
        User u = (User) request.getSession().getAttribute("user");
        Rate rate = rateDao.get(u.getId(), dto.getItemId(), dto.getCategory());
        if (rate == null) {
            Rate entity = new Rate();
            entity.setStar(dto.getValue());
            entity.setCategoryId(dto.getCategory());
            entity.setItemId(dto.getItemId());
            entity.setUserId(u.getId());
            rateDao.insert(entity);
        } else {
            rate.setStar(dto.getValue());
            rateDao.update(rate);
        }
        return "success";
    }


}
