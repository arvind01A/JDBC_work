//InsertTest_SurrogateKey.java
package com.nt.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertTest_SurrogateKey {
	private static final String INSET_STUDENT_QUERY = "INSERT INTO STUDENT(SNAME, SADD, AVG) VALUES(?,?,?)";
	
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:mysql://@localhost:3306/NTAJ916DB","root","root");
				PreparedStatement ps = con.prepareStatement(INSET_STUDENT_QUERY);
				){
			//read input values
			String name = null, addrs = null;
			float avg = 0.0f;
			if(sc!=null) {
				System.out.print("Enter student name:: ");
				name = sc.next();
				System.out.print("Enter student address:: ");
				addrs = sc.next();
				System.out.println("Enter student average:: ");
				avg = sc.nextFloat();
			}
			
			if(ps!=null) {
				//set values to query param
				ps.setString(1, name);
				ps.setString(2, addrs);
				ps.setFloat(3, avg);
				//execute the SQL param
				int count = ps.executeUpdate();
				//process the record result
				if(count==0)
					System.err.println("Record not inserted");
				else
					System.out.println("Record inserted");
				
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
