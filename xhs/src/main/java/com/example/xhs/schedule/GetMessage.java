package com.example.xhs.schedule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.xhs.pojo.entity.Food;
import com.example.xhs.service.FoodService;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
public class GetMessage {

    @Autowired
    private FoodService service;

    /**
     * 定时任务
     * jsoup获取美食信息
     */
    @Scheduled(cron = "0 0 1 ? * 7")
    public void food() throws IOException {
        Document document;

        List<Food> list = new ArrayList<>();
        String ht = "https://www.meishij.net/chufang/diy/";
        for (int i = 1; i < 57; i++) {
            String url = ht + "?page=" + i;
            document = Jsoup.connect(url).get();
            Elements meishi = document.getElementsByClass("listtyle1");
            for (Element m : meishi) {
                Food food = new Food();
                Elements title = m.getElementsByTag("strong");
                String name = title.text();
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
        service.batchInsert(list);
    }


}
	
