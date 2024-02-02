//InsertTest.java
package com.nt.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTest {
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in)){
			try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","mydb9am","arvind")){
				try(Statement st = con.createStatement()){
					
					String name = null, addrs = null;
					float avg=0.0f;
					int no = 0;
					
					System.out.print("Enter student number: ");
					no = sc.nextInt();
					System.out.print("Enter student name: ");
					name = sc.next();
					System.out.print("Enter student addrs: ");
					addrs = sc.next();
					System.out.print("Enter student avg: ");
					avg = sc.nextFloat();
					
					//convert input values as required for the SQL Query
					name = "'" + name + "'";
					addrs = "'" + addrs + "'";
					
					//prepare SQL Query
					String query = "INSERT INTO STUDENT_TEST VALUES(" + no + ", " + name + ", " + addrs + ", " + avg + ")";
					System.out.println(query);
					
					//send and execute SQL Query
					int count = 0;
					
					count = st.executeUpdate(query);
					
					if(count == 0)
						System.err.println("record not inserted");
					else
						System.out.println("record inserted");
				}//try3
			}//try2
		}//try1
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//main
}//class
