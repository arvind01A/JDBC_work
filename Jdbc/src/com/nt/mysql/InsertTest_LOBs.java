//InsertTest_LOBs.java
package com.nt.mysql;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertTest_LOBs {
	private static final String INSERT_EMPLOYEE_LOB_QUERY = "INSERT INTO EMPLOYEE_LOBS(ENAME,DESG,PHOTO,RESUME) VALUES(?,?,?,?)";
	
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:mysql://@localhost:3306/NTAJ916DB","root","root");
				PreparedStatement ps = con.prepareStatement(INSERT_EMPLOYEE_LOB_QUERY);
				){
			// read inputs
			String name = null, desg = null, photoPath = null, resumePath = null;
			if(sc!=null) {
				System.out.print("Enter employee name:: ");
				name = sc.nextLine();
				System.out.print("Enter employee desg:: ");
				desg = sc.nextLine();
				System.out.print("Enter employee photo path:: ");
				photoPath = sc.nextLine();
				System.out.print("Enter employee resume path:: ");
				resumePath = sc.nextLine();
			}
			
			try(InputStream is = new FileInputStream(photoPath);
					Reader reader = new FileReader(resumePath);
					){
				
				if(ps!=null) {
					ps.setString(1, name);
					ps.setString(2, desg);
					ps.setBinaryStream(3, is);
					ps.setCharacterStream(4, reader);
					
					int count = ps.executeUpdate();
					if(count==0)
						System.err.println("Record not inserted");
					else
						System.out.println("Record inserted");
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
