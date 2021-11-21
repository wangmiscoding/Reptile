package com.example.Reptile.pajiud;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCUtil {
	
	public static void insert(String hname,String hpic,String hscore,String htag,String hprice,String haddress) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
	Class.forName("com.mysql.jdbc.Driver");
	Connection  conn= DriverManager.getConnection(   
			"jdbc:mysql://172.16.237.37:3306/test?characterEncoding=utf8&amp;zeroDateTimeBehavior=convertToNull",
			"root", "8888");

	String sql="insert into hotel(hname,hpic,hscore,htag,hprice,haddress) values(?,?,?,?,?,?)";
	PreparedStatement pst = conn.prepareStatement(sql);
	pst.setString(1, hname);
	pst.setString(2, hpic);
	pst.setString(3, hscore);
	pst.setString(4, htag);
	pst.setString(5, hprice);
	pst.setString(6, haddress);
	int i=pst.executeUpdate();
	conn.close();
	}

}
