//CS_ProcedureCallTest2.java
package com.nt.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CS_ProcedureCallTest2 {
	private static final String GET_PROCEDURE_QUERY = "{ CALL GET_PRODS_BY_PRICE_RANGE(?,?)}";
	
	public static void main(String[] args) {
		try(Scanner sc =  new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:mysql:///NTAJ916DB","root","root");
				CallableStatement cs = con.prepareCall(GET_PROCEDURE_QUERY);
				){
			float start = 0.0f, end = 0.0f;
			if(sc!=null) {
				System.out.print("Enter product start salary:: ");
				start = sc.nextFloat();
				System.out.print("Enter product end salary:: ");
				end = sc.nextFloat();
			}
			
			if(cs!=null) {
				//set values IN param
				cs.setFloat(1, Types.FLOAT);
				cs.setFloat(1, Types.FLOAT);
				
				//call the pl/sql procedure
				cs.execute();
				
				//get transfer result from out param
				try(ResultSet rs = cs.getResultSet();){
					//process the ResultSet
					if(rs!=null) {
						System.out.println("Product details of price range:: " + start + " " + end);
						while(rs.next()) {
							System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getFloat(3) + " " + rs.getFloat(4));
						}//while
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
