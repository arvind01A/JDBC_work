//DateTimeInsert.java
package com.nt.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateTimeInsert {
	private static final String INSERT_EMP_DATES_QUERY = "INSERT INTO EMPLOYEE VALUES(?,?,?,?,?)";
	
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:mysql:///NTAJ916DB","root","root");
				PreparedStatement ps = con.prepareStatement(INSERT_EMP_DATES_QUERY);
				){
			//read inputs
			int no=0;
			String name = null, dob = null, tob = null, doj=null;
			if(sc!=null) {
				System.out.print("enter employee number:: ");
				no = sc.nextInt();
				System.out.print("enter employee name:: ");
				name = sc.next();
				System.out.print("enter employee DOB(dd-MM-yyyy):: ");
				dob = sc.next();
				System.out.print("enter employee TOB(hh:mm:ss):: ");
				tob = sc.next();
				System.out.print("enter employee DOB(yyyy-MM-dd hh:mm:ss):: ");
				sc.nextLine();
				doj = sc.nextLine();
			}
			//convert String date value to java.sql.Date class object
			java.text.SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date udob = sdf.parse(dob);
			//convert java.util.Date obj to java.sql.Date class obj
			long ms = udob.getTime();
			java.sql.Date sqdob = new java.sql.Date(ms);
			//convert String time value to java.sql.Time class obj
			java.sql.Time sqtob = java.sql.Time.valueOf(tob);
			//convert String date time value to java.sql.Timestamp class obj
			java.sql.Timestamp sqdoj = java.sql.Timestamp.valueOf(doj);
			
			//set values the query params and execute the query
			if(ps!=null) {
				ps.setInt(1, no);
				ps.setString(2, name);
				ps.setDate(3, sqdob);
				ps.setTime(4, sqtob);
				ps.setTimestamp(5, sqdoj);
				//ps.setTimestamp(5, sqdoj);
				
				//execute the query
				int count = ps.executeUpdate();
				//process the result
				if(count==0)
					System.out.println("Record not inserted");
				else
					System.out.println("Record inserted");
			}//if
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//main
}//class
