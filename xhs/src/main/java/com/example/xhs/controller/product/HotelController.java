package com.example.xhs.controller.product;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.xhs.pojo.dto.FilmSearchDto;
import com.example.xhs.pojo.dto.PersonalizeDto;
import com.example.xhs.pojo.dto.ShowDto;
import com.example.xhs.pojo.entity.Attraction;
import com.example.xhs.pojo.entity.Hotel;
import com.example.xhs.service.HotelService;
import com.example.xhs.service.ShowService;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author wangmeng
 * @since 2021/1/6
 */
@Controller
@RequestMapping(value = "hotel")
public class HotelController {
    @Autowired
    private HotelService service;
    @Autowired
    private ShowService showService;

    @RequestMapping()
    public String list(FilmSearchDto dto, Model model, HttpServletResponse response, HttpServletRequest request) {
        PageInfo<Hotel> info = service.listPage(dto);
        Cookie cookie=new Cookie("name",dto.getFilmName());
        response.addCookie(cookie);
        model.addAttribute("info", info);
        //个性化
        //从session获取
        HttpSession session = request.getSession();
        PersonalizeDto personalizeDto= (PersonalizeDto)session.getAttribute("personalize");
        if(dto.getFilmName()!=null){
            if(personalizeDto==null){
                personalizeDto=new PersonalizeDto();
            }
            if (personalizeDto.getParams()==null&& !dto.getFilmName().equals(""))
                personalizeDto.setParams(new ArrayList<>());
            personalizeDto.getParams().add(dto.getFilmName());
            session.setAttribute("personalize",personalizeDto);
        }
        List<ShowDto> show = showService.show(personalizeDto);
        model.addAttribute("show", show);
        return "hotel";
    }

}
