//PS_BulkInsertTest.java
package com.nt.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PS_BulkInsertTest {
	private static final String INSERT_STUDENT_TEST_QUERY="INSERT INTO STUDENT_TEST VALUES(?,?,?,?)";
	
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","MYDB9AM","arvind");
				PreparedStatement ps = con.prepareStatement(INSERT_STUDENT_TEST_QUERY);
				){
			
			int count = 0;
			if(sc!=null) {
				System.out.println("Enter student count:: ");
				count = sc.nextInt();
			}//if
			
			if(sc!=null && ps!=null) {
				for(int i = 1; i<=count; i++ ) {
					System.out.println("Enter " + i + " student details ");
					System.out.print("enter no: ");
					int no = sc.nextInt();
					System.out.print("enter name: ");
					String name = sc.next();
					System.out.print("enter address: ");
					String addrs = sc.next();
					System.out.print("enter avg: ");
					float avg = sc.nextFloat();
					
					//set the above inputs to pre-compiled SQL Query param
					ps.setInt(1, no);
					ps.setString(2, name);
					ps.setString(3, addrs);
					ps.setFloat(4, avg);
					
					//execute the pre-compiled SQL Query
					int result = ps.executeUpdate();
					System.out.println();
					//process the results
					if(result==0)
						System.out.println(i + " student record is not inserted");
					else
						System.out.println(i + " student record is inserted");
				}//for
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
