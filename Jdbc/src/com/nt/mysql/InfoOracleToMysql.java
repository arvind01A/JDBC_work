//InfoOracleToMysql.java

package com.nt.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InfoOracleToMysql {
	private static final String GET_ALL_STUDENT_ORACLE = "SELECT SNO,SNAME,SADD, AVG FROM STUDENT";
	private static final String INSERT_STUDENT_MYSQL = "INSERT INTO STUDENT VALUES(?,?,?,?)";
	
	public static void main(String[] args) {
		try(Connection oraCon = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","mydb9am","arvind");
				Connection mysqlCon = DriverManager.getConnection("jdbc:mysql:///NTAJ916DB","root","root");
				Statement st = oraCon.createStatement();
				PreparedStatement ps = mysqlCon.prepareStatement(INSERT_STUDENT_MYSQL);
				){
			//execute the SELECT SQL Query in oracle db s/w
			try(ResultSet rs = st.executeQuery(GET_ALL_STUDENT_ORACLE)){
				//process the ResultSet and insert those records to mysql Db table
				if(rs!=null && ps!=null) {
					while(rs.next()) {
						//get each record  from oracle db table student
						int no = rs.getInt(1);
						String name = rs.getString(2);
						String addrs = rs.getString(3);
						float avg = rs.getFloat(4);
						//set the above to INSERT SQL Query params (?) refered by PreparedStatement obj
						ps.setInt(1, no);
						ps.setString(2, name);
						ps.setString(3, addrs);
						ps.setFloat(4, avg);
						//execute the SQL query
						ps.executeUpdate();
					}//while
					System.out.println("The student db table records of Oracle db s/w are copied to MySQL db table student");
				}//if
			}//try2
		}//try1
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//main
}//class
