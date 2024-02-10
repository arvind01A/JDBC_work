package com.nt.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SelectTest1 {
	public static void main(String[] args) throws Exception{
		//Load JDBC driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//Connetion establish
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","mydb9am","arvind");
		
		//Create Statement object
		Statement st = con.createStatement();
		
		//Send and execute the SELECT SQL Query to DB s/w
		ResultSet rs = st.executeQuery("SELECT * FROM STUDENT");
		
		//process the ResultSet object
		while(rs.next()!=false) {
			System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getInt(3));
		}//while
		
		//close the JDBC objects
		con.close();
		st.close();
		rs.close();
	}//main
}//class
