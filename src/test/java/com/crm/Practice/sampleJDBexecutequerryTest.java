package com.crm.Practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;
@Listeners(com.crm.GenericLibrary.ListenerImplementationClass.class)
public class sampleJDBexecutequerryTest {
	
	@Test

	public void sampleJDBexecutequerry() throws Throwable {
		
		// step-1 regester the database
		Driver driverref = new Driver();
		
		DriverManager.registerDriver(driverref);
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root","root");
		
		Statement state = con.createStatement();
		
		ResultSet result = state.executeQuery("select * from student;");
		
		while(result.next())
		{
			System.out.println(result.getString(1)+" "+result.getString(2)+" "+result.getString(3));
		}
		
		

	}

}
