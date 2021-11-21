package com.example.xhs;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GetAttraction {

    //存放所有景点链接
    public static List<String> attractions = new ArrayList<>();


    @Test
    public void getAttractions() throws IOException {
        Document document = Jsoup.connect("https://www.lvyougl.com/jingdian/")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36")
                .timeout(99999)
                .get();
        Elements elementsByTag = document.getElementsByTag("mip-img");
        //System.out.println(elementsByTag);
        Element nav = document.getElementById("nav");
        Elements li = nav.getElementsByTag("li");
        li.forEach(l->{
            if (StringUtils.isNotEmpty(l.text())){
                String attr = l.getElementsByTag("a").attr("href");
                attractions.add("https://www.lvyougl.com/"+attr);
            }
        });
        System.out.println(attractions.size());
    }

    @Test
    public void attractionInfo() throws IOException {
        String url="https://www.lvyougl.com/gansu.html";
        Document document = Jsoup.connect(url)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36")
                .timeout(99999)
                .get();
        Element ddxy = document.getElementsByClass("ddxy").first();
        Element li = ddxy.getElementsByTag("li").first();

    }


}
