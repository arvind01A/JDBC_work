//InsertTest_LOBs.java
package com.nt.oracle;

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
	private static final String INSERT_EMPLOYEE_LOB_QUERY = "INSERT INTO EMPLOYEE_LOBS VALUES(EID_SEQ.NEXTVAL,?,?,?,?)";
	
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","MYDB9AM","arvind");
				PreparedStatement ps = con.prepareStatement(INSERT_EMPLOYEE_LOB_QUERY);
				){
			// read inputs
			String name = null, desg = null, photoPath = null, resumePath = null;
			if(sc!=null) {
				System.out.print("Enter employee name:: ");
				name = sc.next();
				System.out.print("Enter employee desg:: ");
				desg = sc.next();
				System.out.print("Enter employee photo Path:: ");
				photoPath = sc.next();
				System.out.print("Enter employee resume Path:: ");
				resumePath = sc.next();
			}
			
			//create stream representing photo and resume files
			try(InputStream is = new FileInputStream(photoPath);
					Reader reader = new FileReader(resumePath)){
				
				if(ps!=null) {
					ps.setString(1, name);
					ps.setString(2, desg);
					ps.setBlob(3, is);
					ps.setClob(4, reader);
					
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
