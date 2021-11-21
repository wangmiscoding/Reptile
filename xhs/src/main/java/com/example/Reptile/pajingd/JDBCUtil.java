package com.example.Reptile.pajingd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCUtil {
	
	public static void insert(String name,String kind,String price,String img) throws ClassNotFoundException, SQLException {
	Class.forName("com.mysql.jdbc.Driver");//5.0 1.加载驱动
	Connection  conn= DriverManager.getConnection(   //获取连接对象
			"jdbc:mysql://localhost:3306/test?characterEncoding=utf8&amp;zeroDateTimeBehavior=convertToNull",
			"root", "8888");

	String sql="insert into jidi(name,kind,price,img) values(?,?,?,?)";
	PreparedStatement pst = conn.prepareStatement(sql);
	pst.setString(1, name);
	pst.setString(2, kind);
	pst.setString(3, price);
	pst.setString(4, img);
	int i=pst.executeUpdate();
	conn.close();
	}

}
