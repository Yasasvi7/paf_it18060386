package com;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;

public class Doctor {

	public Connection connect() {

		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/doctor","root", "");
			// For testing
			System.out.println("Successfully connected---1");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	// inserting an items .........................

	public String insertdoctors(String code, String name, String salary, String desc) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = "insert into doctors" + "(`drID`,`drCode`,`drName`,`drSalary`,`drDesc`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, code);
			preparedStmt.setString(3, name);
			preparedStmt.setDouble(4, Double.parseDouble(salary));
			preparedStmt.setString(5, desc);

			// execute the statement
			preparedStmt.execute();
//		 System.out.print("successfuly inserted");
			con.close();
			
			
			String newdoctors = readdoctor();
			output = "{\"status\":\"success\", \"data\": \"" +newdoctors + "\"}";
			
			//output = "Inserted successfully";
			
			
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the doctors.\"}";
					System.err.println(e.getMessage());
		}
		return output;
	}

	// read the items from database and display----------------------

	public String readdoctor()
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{
	return "Error while connecting to the database for reading.";
	}
	// Prepare the html table to be displayed
	output = "<table border='1'><tr><th>doctors Code</th>"
			+ "<th>doctors Name</th><th>doctors Salary</th>"
			+"<th>doctors Description</th>"
			+ "<th>Update</th><th>Remove</th></tr>";
	String query = "select * from doctors";
	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery(query);
	// iterate through the rows in the result set
	while (rs.next())
	{
	String drID = Integer.toString(rs.getInt("drID"));
	String drCode = rs.getString("drCode");
	String drName = rs.getString("drName");
	String drSalary = Double.toString(rs.getDouble("drSalary"));
	String drDesc = rs.getString("drDesc");
	
	
	// Add into the html table
	output += "<tr><td><input id='hidItemIDUpdate'"
			+ "name='hidItemIDUpdate' type='hidden'"
			+ "value='" + drID + "'>" + drCode + "</td>";
	output += "<td>" + drName + "</td>";
	output += "<td>" + drSalary + "</td>";
	output += "<td>" + drDesc + "</td>";
	// buttons
	output += "<td><input name='btnUpdate' type='button'"
			+ "value='Update'"
			+ "class='btnUpdate btn btn-secondary'></td>"
			+ "<td><input name='btnRemove' type='button'"
			+ "value='Remove'"
			+ "class='btnRemove btn btn-danger' data-drID='"
	+ drID + "'>" + "</td></tr>";
	}
	con.close();
	// Complete the html table
	output += "</table>";
	}
	catch (Exception e)
	{
	output = "Error while reading the doctorss.";
	System.err.println(e.getMessage());
	}
	return output;
	}
	
	
	
	
	
	
	// update items ---------------------------------------------

	public String updatedoctors(String ID, String code, String name, String salary, String desc) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE doctors SET drCode=?,drName=?,drSalary=?,drDesc=? WHERE drID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, code);
			preparedStmt.setString(2, name);
			preparedStmt.setDouble(3, Double.parseDouble(salary));
			preparedStmt.setString(4, desc);
 			preparedStmt.setInt(5, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			
			String newdoctors = readdoctor();
			output = "{\"status\":\"success\", \"data\": \"" +newdoctors+ "\"}";
			
			//output = "Updated successfully";
			
			
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while updating the doctors.\"}";
					System.err.println(e.getMessage());
		}
		return output;
	}

	// delete items--------------------------------------

	public String deletedoctors(String drID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from doctors where drID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(drID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newdoctors = readdoctor();
			output = "{\"status\":\"success\", \"data\": \"" +newdoctors + "\"}";
			
			//output = "Deleted successfully";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the doctors.\"}";
					System.err.println(e.getMessage());
		}
		return output;
	}

}
