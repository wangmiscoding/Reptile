package com.example.Reptile.pajingd;

import java.io.IOException;
import java.sql.SQLException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Pafan {
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		
				Document document;
				String name;
				String img;
				String price;
				String kind;
				
			
					
					
				document=Jsoup.connect("https://vacations.ctrip.com/around?startcity=577").get();
				
				//Element left = document.getElementById("listtyle1_list");
				Elements jingdian = document.getElementsByClass("sale_list_mod");
				
				for(Element j:jingdian) {
					Element n=j.getElementsByClass("tit").get(0);
					 name=n.text();
					 System.out.println(name);
					 Element k=j.getElementsByClass("tag_gray").get(0);
					 kind=k.text();
					 System.out.println(kind);
					 Element p=j.getElementsByClass("h_price").get(0);
					 price=p.text().substring(1);
					 System.out.println(price);
					 Element i=j.getElementsByTag("img").get(0);
					 img=i.attr("src");
					 System.out.println(img);
					 JDBCUtil.insert(name, kind, price, img);
				}
		
		
		
	}	
}

	
