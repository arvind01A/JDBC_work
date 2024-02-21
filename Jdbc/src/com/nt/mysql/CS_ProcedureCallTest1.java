//CS_ProcedureCallTest1.java
package com.nt.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;


public class CS_ProcedureCallTest1 {
	private static final String GET_PROCEDURE_QUERY = " {CALL GET_PROD_DETAILS_BY_PID(?,?,?,?)";
	
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:mysql:///NTAJ916DB","root","root");
				CallableStatement cs = con.prepareCall(GET_PROCEDURE_QUERY);
				){
			int id = 0;
			if(sc!=null) {
				System.out.print("Enter product id:: ");
				id = sc.nextInt();
			}
			
			if(cs!=null) {
				//register OUT param with JDBC types
				cs.registerOutParameter(2, Types.VARCHAR);
				cs.registerOutParameter(3, Types.FLOAT);
				cs.registerOutParameter(4, Types.FLOAT);
				
				//set value to IN param
				cs.setInt(1, id);
				
				//call PL/SQL procedure
				cs.execute();
				
				//gather result OUT param
				String name = cs.getString(2);
				float price = cs.getFloat(3);
				float qty = cs.getFloat(4);
				System.out.println("product name:: " + name);
				System.out.println("product price:: " + price);
				System.out.println("product price:: " + qty);
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
