//SelectTest3.java			(with java condition standards)
package com.nt.core;
/*
 * App giving emp details based on the given initial chars of employee name
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SelectTest3 {
	public static void main(String[] args) {
		Scanner sc = null;
		Statement st = null;
		ResultSet rs = null;
		
			try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","mydb9am","arvind");
					){
				
				//read inputs
				System.out.print("Enter charater:: ");
				sc = new Scanner(System.in);
				String initChars = sc.next();
				
				//convert input values as required for the SQL Query
				initChars = "'" + initChars.toUpperCase() + "%'";
				
				//prepare SQL Query
				String query = "SELECT EMPNO, ENAME, JOB, SAL, DEPTNO FROM EMP WHERE ENAME LIKE " + initChars + " ORDER BY ENAME";
				System.out.println(query);
				
				if(con!=null)
					st = con.createStatement();
				
				if(st!=null)
					rs = st.executeQuery(query);
				
				//process the ResultSet object
				if(rs!=null) {
					boolean isRsEmpty = true;
					while(rs.next()!=false) {
						System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getDouble(4) + "  " + rs.getInt(5));
						isRsEmpty = false;
					}//while
					if(isRsEmpty)
						System.out.println("Record is not found");
					else
						System.out.println("Records found and displayed");
				}//if
		}//try
		catch(SQLException se) {		//for known exception 
			se.printStackTrace();
		}
		catch(Exception e) {			  //for unknown exceptions
			e.printStackTrace();
		}//catch
		finally {
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
			try {
				if(sc!=null)
					sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}//finally
	}//main
}//class
