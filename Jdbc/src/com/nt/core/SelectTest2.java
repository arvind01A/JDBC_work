package com.nt.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SelectTest2 {
	public static void main(String[] args) {
		//try with resourses
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","mydb9am","arvind");
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM STUDENT");
						){
			//process the ResultSet object
			while(rs.next()!=false) {
				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getInt(3));
			}//while
		}//try
		catch(Exception e) {
			e.printStackTrace();
		}//catch
	}//main
}//class
