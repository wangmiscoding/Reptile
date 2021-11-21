package com.example.Reptile.pajiud;

import java.io.IOException;
import java.sql.SQLException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Demo22 {
	public static void main(String[] args) throws IOException {
		
				Document document;
				String hname;
				String hpic = null;
				String hscore;
				String htag;
				String hprice;
				String haddress;
				
				
				document=Jsoup.connect("http://hotel.elong.com/search/list_cn_0101.html?changedatehandle=-100%7C%7C0%7Ctrue%7C%7C%7C0%7C%7Cfalse%7C0%7C0%7C0%7C0%7C%7C%7C%7C%7C%7C-1").get();
				
				Elements jiudian = document.getElementsByClass("h_info");
				for(Element m : jiudian) {
					Element title = m.getElementsByClass("info_cn").get(0);
					hname=title.text();
					System.out.println(hname);
					//扒取图片数据
					Element pics=m.getElementsByClass("h_info_pic").get(0);
					Element a = pics.getElementsByTag("a").get(0);
					Element b= a.getElementsByTag("img").get(0);
					hpic = b.attr("big-src");
					System.out.println(hpic);
					
					
					Element title2=m.getElementsByClass("h_info_comt_bg").get(0);	
					Element  c=title2.getElementsByClass("t20 c37e").get(0);
					hscore=c.text();
					System.out.println(hscore);
					Element title3=title2.getElementsByClass("t20").get(1);	
					htag=title3.text();
					System.out.println(htag);
					Element title4=m.getElementsByClass("pricesquare").get(0);
					hprice=title4.text();
					System.out.println(hprice);
					Element title5=m.getElementsByClass("h_info_b2").get(0);
					haddress=title5.text();
					System.out.println(haddress);
					try {
						System.out.print("--"+hname);
						JDBCUtil.insert(hname, hpic, hscore, htag,hprice,haddress);
					} catch (ClassNotFoundException | SQLException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}	
				
	}
}
	
