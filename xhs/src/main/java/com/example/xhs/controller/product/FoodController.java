package com.example.xhs.controller.product;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.jsoup.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.xhs.pojo.dto.FoodSearchDto;
import com.example.xhs.pojo.dto.PersonalizeDto;
import com.example.xhs.pojo.dto.ShowDto;
import com.example.xhs.pojo.entity.Food;
import com.example.xhs.service.FoodService;
import com.example.xhs.service.ShowService;
import com.github.pagehelper.PageInfo;

/**
 * TODO
 *
 * @author wangmeng
 * @since 2021/1/6
 */
@Controller
@RequestMapping(value = "food")
public class FoodController {
    @Autowired
    private FoodService service;
    @Autowired
    private ShowService showService;

    @RequestMapping()
    public String  list(FoodSearchDto dto, Model model, HttpServletResponse response, HttpServletRequest request){

        PageInfo<Food> info = service.listPage(dto);
        model.addAttribute("info",info);
        Cookie cookie=new Cookie("name",dto.getName());
        response.addCookie(cookie);
        //个性化
        //从session获取
        HttpSession session = request.getSession();
        PersonalizeDto personalizeDto= (PersonalizeDto)session.getAttribute("personalize");
        if(dto.getName()!=null&& !dto.getName().equals("")){

            if(personalizeDto==null){
                personalizeDto=new PersonalizeDto();
            }
            if (personalizeDto.getParams()==null)
                personalizeDto.setParams(new ArrayList<>());
            personalizeDto.getParams().add(dto.getName());
            session.setAttribute("personalize",personalizeDto);
        }
        List<ShowDto> show = showService.show(personalizeDto);
        model.addAttribute("show", show);
        return "food";
    }



    @RequestMapping(value = "index")
    public String  index(Model model){

        return "index";
    }
}
