//RetriveTest_LOBs.java
package com.nt.oracle;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class RetriveTest_LOBs {
	private static final String GET_LOBS_EMPLOYEE = "SELECT * FROM EMPLOYEE_LOBS WHERE ENO=?";
	
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","MYDB9AM","arvind");
				PreparedStatement ps = con.prepareStatement(GET_LOBS_EMPLOYEE);
				){
			//read inputs
			int no = 0;
			if(sc!=null) {
				System.out.print("Enter employee no:: ");
				no = sc.nextInt();
			}
			
			if(ps!=null) {
				ps.setInt(1, no);
				try(ResultSet rs = ps.executeQuery();){
					if(rs!=null) {
						if(rs.next()) {
							try(InputStream is = rs.getBinaryStream(4);
									Reader reader = rs.getCharacterStream(5);
									OutputStream os = new FileOutputStream("new_photo.jpg");
									Writer writer = new FileWriter("new_resume.txt");
									){
									
								//complete the file copy operation
								IOUtils.copy(is, os);
								IOUtils.copy(reader, writer);
								System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
								System.out.println("LOBs are retrieved to new files");
							}//try2
						} else {
							System.err.println("Record not found");
						}//if-else
					}//if2
				}//try1
			}//if1
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//main
}//class
