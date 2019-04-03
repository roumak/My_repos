package com.roumak.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJDBC {

	public static void main(String[] args) {
		
		try {
			String JDBC_URL="jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false";
			String user= "hbstudent";
			String password= "hbstudent";
			System.out.println("connecting to Mysql Database"+JDBC_URL);
			
			Connection con= DriverManager.getConnection(JDBC_URL, user ,password); 
			
			System.out.println("connected");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
