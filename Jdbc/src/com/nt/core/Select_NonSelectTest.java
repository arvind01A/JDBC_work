//Select_NonSelectTest.java
package com.nt.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Select_NonSelectTest {
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","MYDB9AM","arvind");
				Statement st = con.createStatement();
				){
			//read input value from enduser
			String query = null;
			if(sc!=null) {
				System.out.println("Enter SQL Query (select or non-select)");
				query=sc.nextLine();
			}
			//send and execute the SQL Query
			boolean flag = false;
			if(st!=null)
				flag=st.execute(query);
			
			//process the ResultSet object
			if(flag) {
				System.out.println("Select SQL Query is execute");
				try(ResultSet rs = st.getResultSet()){
					if(rs!=null) {
						while(rs.next()) {
							System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getFloat(4));
						}//while
					}//if
				}//try2
			} else {
				System.out.println("Non-Select SQL Query is execute");
				int count= st.getUpdateCount();
				System.out.println("no. of records that are effected:: " + count);
			}
		}//try1
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
