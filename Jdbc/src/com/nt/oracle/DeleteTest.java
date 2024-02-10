//DeleteTest.java
package com.nt.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteTest {
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);){
			try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","mydb9am","arvind");){
				try(Statement st = con.createStatement();){
					String addrs = null;
					
					System.out.println("Enter student address:: ");
					addrs = sc.next();
					
					//convert the input value as required for the SQL Query
					addrs = "'" + addrs + "'";
					
					//prepare the SQL Query
					String query = "DELETE FROM STUDENT_TEST WHERE SADD=" + addrs ;
					System.out.println(query);
					
					//send and execute the SQL Query
					int count=0;
					count = st.executeUpdate(query);
					
					if(count==0)
						System.err.println("record not found");
					else
						System.out.println(count + " no of record is found and delete");
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
