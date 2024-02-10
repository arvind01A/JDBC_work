//PS_LoginApp.java
package com.nt.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PS_LoginApp {
	private static final String AUTH_QUERY = "SELECT COUNT(*) FROM USERINFO WHERE UNAME = ? AND PWD = ?";
	
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","mydb9am","arvind");
				PreparedStatement ps = con.prepareStatement(AUTH_QUERY);
				){
			
			//read inputs
			String user = null, pass = null;
			if(sc!=null) {
				System.out.print("Enter username:: ");
				user = sc.nextLine();
				System.out.print("Enter password:: ");
				pass = sc.nextLine();
			}
			
			//set values to query param
			if(ps!=null) {
				ps.setString(1, user);
				ps.setString(2, pass);
			}
			
			//send and execute SQL Query
			if(ps!=null) {
				try(ResultSet rs = ps.executeQuery()){
					if(rs!=null) {
						rs.next();
						int count = rs.getInt(1);
						if(count == 0)
							System.out.println("InValid Credentials");
						else
							System.out.println("Valid Credentials");
					}
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
