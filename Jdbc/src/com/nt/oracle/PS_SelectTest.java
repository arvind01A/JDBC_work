//PS_SelectTest.java
package com.nt.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PS_SelectTest {
	private static final String GET_ALL_EMPS = "SELECT EMPNO, ENAME, JOB, SAL FROM EMP";
	
	public static void main(String[] args) {
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","mydb9am","arvind");
				PreparedStatement ps = con.prepareStatement(GET_ALL_EMPS);
				){
			//send and execute SQL Query
			try(ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
				}//while
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
