package com.nt.core;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest4 {

	public static void main(String[] args) {
				Scanner sc = null;
				Connection con = null;
				Statement st = null;
				ResultSet rs = null;
				
				try {
						//read input
						sc = new Scanner(System.in);
						int no = 0;
						if(sc != null) {
								System.out.println("Enter employee number :: ");
								no = sc.nextInt();
						}
						//Load jdbc driver class (optional)
						Class.forName("oracle.jdbc.driver.OracleDriver");
						//establish the connection
						con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","mydb9am","arvind");
						//create statement obj
						if(con != null) 
								st = con.createStatement();
						
								//prepare SQL Query
								String query = "SELECT EMPNO, ENAME, JOB, SAL, DEPTNO FROM EMP WHERE EMPNO="+no;
								System.out.println(query);
								
								//send and execute SQL query
								if(st != null)
										rs = st.executeQuery(query);
								
								//process the ResultSet obj ( 1 or 0 records)
								if(rs != null) {
										if(rs.next())
												System.out.println(rs.getInt(1) + rs.getString(2) + rs.getString(3) + rs.getDouble(4) + rs.getInt(5) );
										else 
												System.out.println("Record not found");
								}
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
				catch(ClassNotFoundException cnf) {
					cnf.printStackTrace();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				finally {
						//close jdbc objs
						try {
							if(rs != null)
								rs.close();
						}
						catch(SQLException se) {
							se.printStackTrace();
						}
				}
	}

}
