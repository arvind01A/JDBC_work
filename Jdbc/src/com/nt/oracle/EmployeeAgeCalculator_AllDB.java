//EmployeeAgeCalculator_AllDB.java
package com.nt.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class EmployeeAgeCalculator_AllDB {
	private static final String GET_DOB_QUERY = "SELECT DOB FROM EMPLOYEE WHERE ENO=?";
	
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","MYDB9AM","arvind");
				PreparedStatement ps = con.prepareStatement(GET_DOB_QUERY);
				){
			int no = 0;
			//read inputs
			if(sc!=null) {
				System.out.print("Enter employee no:: ");
				no = sc.nextInt();
			}//if
			
			if(ps!=null) {
				//set query param value
				ps.setInt(1, no);
				
				//execute the Query
				try(ResultSet rs = ps.executeQuery()){
					if(rs!=null) {
						if(rs.next()) {
							//dob from DB table
							java.sql.Date sqdob = rs.getDate(1);
							//sys date
							java.util.Date sysDate = new java.util.Date();
							//dob in Millis
							long dobMs = sqdob.getTime();
							//sysDate in Millis
							long sysDateMs = sysDate.getTime();
							//calculate the age
							float age = (sysDateMs - dobMs)/1000.0f *60.0f*60.0f*24.0f*365.25f;
							System.out.println("Person age:: " + age);
						} else {
							System.err.println("Employee not found");
						}//if-else
					}//if2
				}//try2
			}//if1
			
		}//try1
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//main
}//class
