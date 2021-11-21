package com.example.xhs.service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.xhs.dao.UserDao;
import com.example.xhs.pojo.dto.FilmSearchDto;
import com.example.xhs.pojo.dto.PersonalizeDto;
import com.example.xhs.pojo.dto.ShowDto;
import com.example.xhs.pojo.entity.Film;
import com.github.pagehelper.PageInfo;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangm
 * @since 2021/3/28
 */
@Service
public class ShowService {

    @Autowired
    FoodService foodService;
    @Autowired
    FilmService filmService;
    @Autowired
    UserDao userDao;

    public List<ShowDto> show(PersonalizeDto dto){
        if(dto==null)
            dto=new PersonalizeDto();
        List<Film> list = filmService.listPage(new FilmSearchDto()).getList();
        List<ShowDto> showDtos = userDao.listByPer(dto.getParams());
        Collections.shuffle(showDtos);
        if (showDtos.size()>6){
            showDtos=showDtos.subList(0,6);

        }
        if(showDtos.size()<6){
            List<Film> filmList = list.subList(0, 6 - showDtos.size());
            List<ShowDto> finalShowDtos = showDtos;
            filmList.forEach(i->{
               ShowDto d=new ShowDto();
               d.setId(Integer.parseInt(i.getId()));
               d.setCategory(1);
               d.setImg(i.getUniqueId());
               finalShowDtos.add(d);
           });
            showDtos=finalShowDtos;
        }
        for (ShowDto item : showDtos) {
            if(item.getCategory()==1){
                item.setImg("/img/"+item.getImg()+".jpg");
            }
        }
        return showDtos;
    }
}
