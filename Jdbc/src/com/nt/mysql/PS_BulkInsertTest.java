//PS_BulkInsertTest.java
package com.nt.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PS_BulkInsertTest {
	private static final String INSERT_STUDENT_QUERY = "INSERT INTO STUDENT VALUES(?,?,?,?)";
	
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:mysql:///NTAJ916DB","root","root");
				PreparedStatement ps = con.prepareStatement(INSERT_STUDENT_QUERY);
				){
			int count = 0;
			if(sc!=null) {
				System.out.print("Enter student count:: ");
				count = sc.nextInt();
			}//if
			
			if(sc!=null && ps!=null) {
				for(int i = 1 ; i<=count ; i++) {
					System.out.println("Enter " + i + " student details");
					System.out.print("Enter no:: ");
					int no = sc.nextInt();
					System.out.print("Enter name:: ");
					String name = sc.next();
					System.out.print("Enter address:: ");
					String addrs = sc.next();
					System.out.print("Enter average:: ");
					float avg = sc.nextFloat();
					
					//set the above inputs to pre-compiled SQL Query params
					ps.setInt(1, no);
					ps.setString(2, name);
					ps.setString(3, addrs);
					ps.setFloat(4, avg);
					
					//execute the pre-compiled SQL Query
					int result = ps.executeUpdate();
					if(result == 0)
						System.err.println(i + " student record is not inserted");
					else
						System.out.println(i + " student record sucessfully\n");
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
