//LoginApp.java
package com.nt.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LoginApp {
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","MYDB9AM","arvind");
				Statement st = con.createStatement();
				){
			//read inputs
			String user = null, pwd = null;
			if(sc!=null) {
				System.out.print("Enter username:: ");
				user = sc.nextLine();
				System.out.print("Enter password:: ");
				pwd = sc.nextLine();
			}
			
			//convert the input values to required SQL Query
			user = "'" + user + "'";
			pwd = "'" + pwd + "'";
			
			//prepare the SQL Query
			String query = "SELECT COUNT(*) FROM USERINFO WHERE UNAME= " + user + " " + " AND PWD= " + pwd;
			System.out.println(query);
			
			//send and execute SQL Query
			if(st!=null) {
				try(ResultSet rs = st.executeQuery(query)){
					if(rs!=null) {
						rs.next();
						int count = rs.getInt(1);
						if(count!=0)
							System.out.println("Valid Credentials");
						else 
							System.out.println("InValid Credentials");
					}//if-else
				}//try2
			}//if
		}//try1
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//main
}//class
