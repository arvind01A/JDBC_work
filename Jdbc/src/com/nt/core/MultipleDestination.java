package com.nt.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MultipleDestination {
	public static void main(String[] args) {
		Scanner sc = null;
		Statement st = null;
		ResultSet rs = null;
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","mydb9am","arvind");
				){
			sc = new Scanner(System.in);
			
			//read destination
			System.out.print("Enter first destination:: ");
			String firstDestination = sc.nextLine().toUpperCase();
			System.out.print("Enter second destination:: ");
			String secondDestination = sc.nextLine().toUpperCase();
			System.out.print("Enter third destination:: ");
			String thirdDestination = sc.nextLine().toUpperCase();
			
			//create query
			String query = "SELECT EMPNO, ENAME, JOB, SAL FROM EMP WHERE JOB IN('" + firstDestination +"', '" + secondDestination + "', '" + thirdDestination +"')";
			System.out.println(query);
			
			if(con!=null)
				st = con.createStatement();
			
			if(st!=null)
				rs = st.executeQuery(query);
			
			//process the ResultSet object
			if(rs!=null) {
				boolean isRsEmpty = true;
				while(rs.next()!=false) {
					System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t\t" + rs.getString(3) + "\t\t" + rs.getDouble(4));
					isRsEmpty = false;
				}
				if(isRsEmpty)
					System.out.println("Record not found");
				else 
					System.out.println("Record found and dispaly");
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
