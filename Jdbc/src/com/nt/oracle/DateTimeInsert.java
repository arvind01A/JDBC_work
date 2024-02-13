//DateTimeInsert.java
package com.nt.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateTimeInsert {
	private static final String INSERT_EMP_DATES_QUERY = "INSERT INTO EMPLOYEE VALUES(?,?,?,?)";
	
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","MYDB9AM","arvind");
				PreparedStatement ps = con.prepareStatement(INSERT_EMP_DATES_QUERY);
				){
			//read inputs
			int no=0;
			String name = null, dob = null, doj = null;
			if(sc!=null) {
				System.out.print("Enter employee number:: ");
				no = sc.nextInt();
				System.out.print("Enter employee name:: ");
				name = sc.next();
				System.out.print("Enter employee DOB (dd-MM-yyyy):: ");
				dob = sc.next();
				System.out.print("Enter employee DOJ(yyyy-MM-dd hh:mm:ss):: ");
				sc.nextLine();
				doj = sc.nextLine();
			}//if
			java.text.SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date udob = sdf.parse(dob);
			long ms = udob.getTime();
			java.sql.Date sqdob = new java.sql.Date(ms);
			java.sql.Timestamp sqdoj = java.sql.Timestamp.valueOf(doj);
			if(ps!=null) {
				ps.setInt(1, no);
				ps.setString(2, name);
				ps.setDate(3, sqdob);
				ps.setTimestamp(4, sqdoj);
				
				int count = ps.executeUpdate();
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
