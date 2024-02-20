//CS_ProcedureCallTest3.java
package com.nt.oracle;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

public class CS_ProcedureCallTest3 {
	private static final String GET_EMP_QUERY = "{CALL P_GET_EMP_DESGS(?,?,?)";
	
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","mydb9am","arvind");
				CallableStatement cs = con.prepareCall(GET_EMP_QUERY);
				){
			String desg1 = null, desg2 = null;
			if(sc!=null) {
				System.out.print("Enter employee desg1:: ");
				desg1 = sc.nextLine();
				System.out.print("Enter employee desg2:: ");
				desg2 = sc.nextLine();
			}
			
			if(cs!=null) {
				cs.registerOutParameter(3, OracleTypes.CURSOR);
				
				//set values IN params
				cs.setString(1, desg1);
				cs.setString(2, desg2);
				//call the PL/SQL procedure
				cs.execute();
				//call the ResultSet from OUT params
				try(ResultSet rs =(ResultSet)cs.getObject(3)){
					//process the ResultSet
					if(rs!=null) {
						System.out.println("Employee details having :: " + desg1 + " " + desg2);
						while(rs.next()) {
							System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getFloat(4));
						}
					}
				}//try2
			}//if
			
		}//try1
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//main
}//class
