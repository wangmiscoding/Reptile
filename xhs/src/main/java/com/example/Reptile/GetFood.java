package com.example.Reptile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.xhs.common.CutListUtil;
import com.example.xhs.dao.FoodDao;
import com.example.xhs.pojo.entity.Food;
import com.example.xhs.service.FoodService;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author wangm
 * @since 2021/4/30
 */
@Component
@EnableScheduling
public class GetFood {

    @Autowired
    private FoodService service;
    @Autowired
    private FoodDao foodDao;

    @Scheduled(cron = "0 0 12 * * ?")
    void getFoodList() throws IOException {
        List<String> hrefList=new ArrayList<>();
        Document document;
        //获取现存所有美食的名称集合
        List<String> nameList = foodDao.list(null)
                .parallelStream().map(Food::getName).collect(Collectors.toList());
        //用来存放页面的数据
        List<Food> list = new ArrayList<>();
        String ht = "https://www.meishij.net/chufang/diy/";
        for (int i = 1; i < 57; i++) {
            String url = ht + "?page=" + i;
            document = Jsoup.connect(url).get();
            Elements meishi = document.getElementsByClass("listtyle1");
            for (Element m : meishi) {

                //获取名称
                Elements title = m.getElementsByTag("strong");
                String name = title.text();
                //如果是已经获取过的美食，则跳过
                if(nameList.contains(name)){
                    continue;
                }
                //获取单个链接
                String attr = m.getElementsByTag("a").first().attr("href");
                hrefList.add(attr);
                Food food = new Food();
                if (StringUtils.isNotEmpty(name))
                    food.setName(name);
                Element li = m.getElementsByTag("li").get(0);
                String calo = li.text();
                if (StringUtils.isNotEmpty(calo))
                    food.setCalo(calo);
                Element ta = m.getElementsByTag("li").get(1);
                String taste = ta.text();
                if (StringUtils.isNotEmpty(taste))
                    food.setTaste(taste);
                Element imgs = m.getElementsByTag("img").get(0);
                String img = imgs.attr("src");
                if (StringUtils.isNotEmpty(img))
                    food.setImg(img);
                System.out.println(food);
                list.add(food);
            }
        }
        detail(hrefList,list);
    }


    public void detail(List<String> hrefList,List<Food> list) throws IOException {
        Document document;
        int i=0;
        //获取具体内容
        for (String s : hrefList) {
            document = Jsoup.connect(s).get();
            Element component = document.getElementsByClass("recipe_ingredientsw").first();
            Elements right = component.getElementsByClass("right");
            String com = document.getElementsByClass("author_words").text();
            try {

                list.get(i).setMainCom(Optional.ofNullable(right.get(0).text()).orElse(""));
                list.get(i).setSubCom(Optional.ofNullable(right.get(1).text()).orElse(""));
                list.get(i).setComponent(com);
                i++;
            }catch (Exception e){
                e.printStackTrace();
                System.out.println(s);
            }
        }
        //批处理插入
        if(CollectionUtils.isNotEmpty(list))
            CutListUtil.cutSlices(t -> service.batchInsert(t), list);
    }
}
