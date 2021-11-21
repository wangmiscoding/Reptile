package com.example.xhs;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.xhs.common.CutListUtil;
import com.example.xhs.pojo.entity.Film;
import com.example.xhs.service.FilmService;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GetMovie {

    @Autowired
    private FilmService service;
    //存放
    public static List<String> movieList = new ArrayList<>();

    @Test
    void contextLoads() throws IOException, InterruptedException {
        String address = "https://movie.douban.com/j/search_subjects?type=movie&tag=%E7%83%AD%E9%97%A8&sort=recommend&page_limit=10000&page_start=0";
        URL url = new URL(address);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36");
        connection.setConnectTimeout(8000);
        //设置从主机读取数据超时（单位：毫秒）
        connection.setReadTimeout(8000);
        Document parse = Jsoup.parse(connection.getInputStream(), "UTF-8", address);
        JSONObject jsonObject = JSONObject.parseObject(parse.body().text());
        JSONArray subjects = jsonObject.getJSONArray("subjects");
        for (int i = 0; i < subjects.size(); i++) {
            movieList.add(String.valueOf(subjects.getJSONObject(i).get("url")));
        }
        List<Film> list = service.listAll();
        List<String> collect = list.stream().map(Film::getUniqueId).collect(Collectors.toList());
        movieList.removeIf(s -> {
            String[] split = s.split("/");
            return collect.contains(split[4]);
        });
        get(movieList);
    }

    public void get(List<String> movieList) throws IOException, InterruptedException {
        List<Film> filmList = new ArrayList<>();
        for (String url : movieList) {
            URL request = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) request.openConnection();
            connection.setRequestMethod("GET");
                connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36");
            connection.setConnectTimeout(8000);
            //设置从主机读取数据超时（单位：毫秒）
            connection.setReadTimeout(8000);
            Document parse = Jsoup.parse(connection.getInputStream(), "UTF-8", url);
            //获取名称
            Elements span = parse.getElementsByTag("span");
            String filmName = span.get(2).text();
            //下载图片
            Element mainpic = parse.getElementById("mainpic");
            String img = mainpic.getElementsByTag("img").attr("src");
            String[] split = url.split("/");
            String uniqueId=split[4];
            downloadImg(img, uniqueId,filmName);
            Thread.sleep(3000);
            //获取电影信息
            Film film = new Film();
            String info = parse.getElementById("info").text();
            try {
                film.setFilmType(StringUtils.substring(info, StringUtils.indexOf(info, "类型") + 3, StringUtils.indexOf(info, "制片国家")));
                film.setCountry(StringUtils.substring(info, StringUtils.indexOf(info, "制片国家") + 8, StringUtils.indexOf(info, "语言")));
                film.setReleaseTime(StringUtils.substring(info, StringUtils.indexOf(info, "上映日期") + 6, StringUtils.indexOf(info, "片长")));
                film.setFilmName(filmName);
                film.setMainActor(StringUtils.substring(info, StringUtils.indexOf(info, "主演") + 3, StringUtils.indexOf(info, "类型")));
                Elements strong = parse.getElementsByTag("strong");
                film.setScore(strong.get(0).text());
                Element intro = parse.getElementById("link-report");
                if(intro!=null) {
                    film.setFilmIntro(Optional.ofNullable(intro.getElementsByTag("span")).orElse(new Elements()).text());
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            film.setUniqueId(uniqueId);
            filmList.add(film);
        }
        if(CollectionUtils.isNotEmpty(filmList)) {
            CutListUtil.cutSlices(t -> service.batchInsert(t), filmList);
        }
    }


    /**
     * 下载图片
     *
     * @param img
     * @param filmName
     * @throws IOException
     * @throws InterruptedException
     */
    public void downloadImg(String img, String filmName,String name) throws IOException, InterruptedException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(img);
        CloseableHttpResponse response = null;
        response = httpClient.execute(httpget);
        InputStream contentStream = response.getEntity().getContent();
        FileOutputStream out = new FileOutputStream("src\\main\\resources\\static\\img\\" + filmName + ".jpg");
        System.out.println("正在下载" + name);
        int temp;
        while ((temp = contentStream.read()) != -1) {
            out.write(temp);
        }
        out.close();
    }


}
