//SelectTest5.java
package com.nt.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest5 {
	public static void main(String[] args) {
		Scanner sc = null;
		Statement st = null;
		ResultSet rs = null;
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","mydb9am","arvind");
				){
			sc = new Scanner(System.in);
			
			//read the user input
			System.out.println("Enter deptno: ");
			int deptno = sc.nextInt();
			
			//create query
			String query = "SELECT * FROM DEPT WHERE DEPTNO=" + deptno;
			
			//create Statement
			if(con!=null)
				st = con.createStatement();
			
			//create ResultSet
			if(st!=null)
				rs = st.executeQuery(query);
			
			//process the ResultSet query
			if(rs!=null) {
				boolean isRSEmpty = true;
				while(rs.next()!=false) {
					System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
					isRSEmpty=false;
				}//while
				if(isRSEmpty)
					System.out.println("Record not found");
				else
					System.out.println("Record is displayed");
			}//if
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}//catch
		finally {
			try {
				if(sc!=null)
					sc.close();
			}
			catch(NullPointerException npe) {
				npe.printStackTrace();
			}
			try {
				if(st!=null)
					st.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}//finally
	}//main
}//class
