//SelectTest6.java
package com.nt.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest6 {
	public static void main(String[] args) {
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","mydb9am","arvind");
				Statement st = con.createStatement();
				ResultSet rs  = st.executeQuery("SELECT COUNT(*) FROM STUDENT");
				){
			
			//
			if(rs!=null) {
				rs.next();
				System.out.println("Student count:: " + rs.getInt(1));
			}
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}//catch
	}//main
}//class
