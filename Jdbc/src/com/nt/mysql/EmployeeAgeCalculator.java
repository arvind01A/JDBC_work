//EmployeeAgeCalculator.java
package com.nt.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class EmployeeAgeCalculator {
	private static final String AGE_CALCULATOR = "SELECT (TO_DAYS(CURDATE())-TO_DAYS(DOB))/365.25 FROM EMPLOYEE WHERE ENO=?";
	
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:mysql:///NTAJ916DB","root","root");
				PreparedStatement ps = con.prepareStatement(AGE_CALCULATOR);
				){
			//read input
			int no = 0;
			if(sc!=null) {
				System.out.print("Enter employee no:: ");
				no = sc.nextInt();
			}//if
			if(ps!=null) {
				ps.setInt(1, no);
				
				try(ResultSet rs = ps.executeQuery()){
					if(rs.next())
						System.out.println("Employee age:: " + rs.getFloat(1));
					else
						System.err.println("Employee not found");
				}
			}
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//main
}//class
