//InsertTest_SurrogateKey.java
package com.nt.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertTest_SurrogateKey {
	private static final String INSERT_EMP_QUERY = "INSERT INTO EMP(EMPNO, ENAME, JOB, SAL) VALUES(EID_SEQ.NEXTVAL,?,?,?)";
	
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","mydb9am","arvind");
				PreparedStatement ps = con.prepareStatement(INSERT_EMP_QUERY);
				){
			//read inputs from enduser
			String name = null, desg = null;
			float salary = 0.0f;
			if(sc!=null) {
				System.out.print("Enter employee name:: ");
				name = sc.next();
				System.out.print("Enter employee desg:: ");
				desg = sc.next();
				System.out.print("Enter employee salary:: ");
				salary = sc.nextFloat();
			}
			
			if(ps!=null) {
				//set values to query params
				ps.setString(1, name);
				ps.setString(2, desg);
				ps.setFloat(3, salary);
				//execute the SQL Query
				int count = ps.executeUpdate();
				//process the record result
				if(count==0)
					System.err.println("Record not inserted");
				else
					System.out.println("Record inserted");
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
