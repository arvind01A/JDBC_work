//CS_ProcedureCallTest.java
package com.nt.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.sql.CallableStatement;
import java.util.Scanner;

public class CS_ProcedureCallAddition {
	private static final String CALL_QUERY = "{CALL P_FIRST(?,?,?)}";
	
	public static void main(String[] args) {
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","MYDB9AM","arvind");
				CallableStatement cs = con.prepareCall(CALL_QUERY);
				Scanner sc = new Scanner(System.in);
				){
			int first=0, second=0;
			if(sc!=null) {
				System.out.print("Enter first value:: ");
				first = sc.nextInt();
				System.out.print("Enter second value:: ");
				second = sc.nextInt();
			}
			
			if(cs!=null) {
				//register OUT params with JDBC types
				cs.registerOutParameter(3, Types.INTEGER);
				
				//set values IN params
				cs.setInt(1, first);
				cs.setInt(2, second);
				
				//call pl/sql procedure
				cs.execute();
				
				//gather results from OUT params
				int result = cs.getInt(3);
				System.out.println("result sum of (" + first + ", " + second + ")values is:: " + result);
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
