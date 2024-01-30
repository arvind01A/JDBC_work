package com.nt.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnTest2 {

	public static void main(String[] args) throws SQLException {
	// create JDBC driver
		//oracle.jdbc.driver.OracleDriver driver = new oracle.jdbc.driver.OracleDriver();
		oracle.jdbc.OracleDriver driver = new oracle.jdbc.OracleDriver();
		
		// establish the connection of db s/w
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","mydb9am","arvind");
		if(con == null)
			System.out.println("ConnTest.main() failed");
		else 
			System.out.println("ConnTest.main() success");
		
	}

}
