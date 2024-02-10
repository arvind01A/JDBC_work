//UpdateTest.java
package com.nt.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest {
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in)){
			try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","mydb9am","arvind")){
				try(Statement st = con.createStatement()){
					String newAddrs = null;
					float newAvg = 0.0f;
					int no = 0;
					
					System.out.print("Enter student number:: ");
					no = sc.nextInt();
					System.out.print("Enter new Address for the student:: ");
					newAddrs = sc.next();
					System.out.println("Enter new average for the student:: ");
					newAvg = sc.nextFloat();
					
					//convert the input value required for the SQL Query
					newAddrs = "'"+newAddrs+"'";
					
					//prepare the SQL Query
					String query = "UPDATE STUDENT_TEST SET SADD=" + newAddrs + ", AVG=" + newAvg + " WHERE STID=" + no;
					System.out.println(query);
					
					//send and executeUpdate
					int count = 0;
					count = st.executeUpdate(query);
				
					if(count==0)
						System.err.println("Record not found");
					else
						System.out.println(count + " no of record is found and update");
					
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
