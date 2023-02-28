package com.amazon.buspassmanagement.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class DB {
	
	public static String FILEPATH = "/Users/innoc/Desktop/buspassmanagement/buspassmanagement/src/main/java/dbconfig.txt";
	public static String URL = "jdbc:mysql://localhost/BusPass";
	public static String USER = "root";
	public static String PASSWORD = "Amazon@12345";
	
	Connection connection;  
	Statement statement;	

	private static DB db = new DB();
	
	public static DB getInstance() {
		return db;
	}
	
	private DB() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			System.out.println("[DB] Driver Loaded Successfully....");
			
			createConnection();
			
		} catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}
	
	}
	
	private void createConnection() {
		try {
			
			File file = new File(FILEPATH);
			if(file.exists()) {
				FileReader reader = new FileReader(file);
				BufferedReader buffer = new BufferedReader(reader);
				
				URL = buffer.readLine();
				USER = buffer.readLine();
				PASSWORD = buffer.readLine();
				
				buffer.close();
				reader.close();
				
				System.out.println("[DB] Configured using File :)");
			}else {
				System.err.println("[DB] Cannot Read the DB Config File...");
			}
			
			
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("[DB] Connection Created Successfully....");
			
			
		} catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}
	}
	
	public int executeSQL(String sql) {
		
		int result = 0;
		
		try {
			statement = connection.createStatement();
			result = statement.executeUpdate(sql); 
			} catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}
		
		return result;
	}
	
	
	public ResultSet executeQuery(String sql) {
		
		ResultSet set = null;
		
		try {
			statement = connection.createStatement();
			set = statement.executeQuery(sql); 
		} catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}
		
		return set;
	}
	
	public void closeConnection() {
		try {
			connection.close();
			System.out.println("[DB] Connection Closed...");
		} catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}
	}
	
}
