package com.crm.Practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;
@Listeners(com.crm.GenericLibrary.ListenerImplementationClass.class)
public class DtaverificationDBTest {
	
	@Test

	public void DtaverificationDB() throws Throwable 
	{
		
		String expdata = " rahul";
		
		Driver driverreference = new Driver();
		
		DriverManager.registerDriver(driverreference);
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root","root");
		
		Statement statement = con.createStatement();
		
		ResultSet result = statement.executeQuery("select * from student;");
		
		while(result.next())
		{
			String actdata=result.getString(1);
			
			if(expdata.equalsIgnoreCase(actdata))
			{
				System.out.println(actdata+ "data is verified");
				break;
			}
		}
		
	}

}
