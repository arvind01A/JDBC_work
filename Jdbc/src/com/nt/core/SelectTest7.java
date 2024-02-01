//SelectTest7.java
package com.nt.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest7 {
	public static void main(String[] args) {
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","mydb9am","arvind")){
			try(Statement st = con.createStatement()){
				try(ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM STUDENT")){
					if(rs!=null) {
						rs.next();
						System.out.println("Student count:: " + rs.getInt(1));
					}//if
				}//try3
			}//try2
		}//try1
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//main
}//class
