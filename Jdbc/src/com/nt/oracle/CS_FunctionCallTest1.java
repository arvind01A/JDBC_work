//CS_FunctionCallTest1.java
package com.nt.oracle;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CS_FunctionCallTest1 {
	private static final String GET_FUNCTION_QUERY = "{ ?=CALL FX_GET_STUDENT_DETAILS(?,?,?)}";
	
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","mydb9am","arvind");
				CallableStatement cs = con.prepareCall(GET_FUNCTION_QUERY);
				){
			int no = 0;
			if(sc!=null) {
				//read input
				System.out.print("Enter student no:: ");
				no = sc.nextInt();
			}
			
			if(cs!=null) {
				//register RETURN, OUT params
				cs.registerOutParameter(1, Types.VARCHAR); 	//return param
				cs.registerOutParameter(3, Types.VARCHAR);	//out param
				cs.registerOutParameter(4, Types.FLOAT);			//out param
				//set value to IN param
				cs.setInt(2, no);
				//call PL/SQL function
				cs.execute();
				//get results from OUT param
				String addrs = cs.getString(1);
				String name = cs.getString(3);
				float avg = cs.getFloat(4);
				System.out.println("student name:: " + name);
				System.out.println("student addrs:: " + addrs);
				System.out.println("student avg:: " + avg);
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
