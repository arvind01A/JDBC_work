//CS_ProcudereCallTest2.java
package com.nt.oracle;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CS_ProcedureCallTest2 {
	private static final String GET_EMP_QUERY = "{CALL P_GET_EMP_DETAILS(?,?,?,?)}";
	
	public static void main(String[] args) {
		try(Scanner sc =new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","mydb9am","arvind");
				CallableStatement cs = con.prepareCall(GET_EMP_QUERY);
				){
			int no = 0;
			if(sc!=null) {
				//read input
				System.out.print("Enter employee no:: ");
				no = sc.nextInt();
			}
			
			if(cs!=null) {
				cs.registerOutParameter(2, Types.VARCHAR);
				cs.registerOutParameter(3, Types.VARCHAR);
				cs.registerOutParameter(4, Types.FLOAT);
				
				cs.setInt(1, no);
				
				cs.execute();
				
				String name = cs.getString(2);
				String desg = cs.getString(3);
				float sal = cs.getFloat(4);
				System.out.println("emp name: " + name);
				System.out.println("emp desg: " + desg);
				System.out.println("emp sal: " + sal);
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
