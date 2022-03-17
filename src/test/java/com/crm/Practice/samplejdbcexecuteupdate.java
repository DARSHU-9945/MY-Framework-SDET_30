package com.crm.Practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;
@Listeners(com.crm.GenericLibrary.ListenerImplementationClass.class)
public class samplejdbcexecuteupdate {
	
	@Test

	public void samplejdbcexecuteupdate() throws Throwable
	{
		Connection con=null;
		
		try {
			
			//step1: regester the database
			
			Driver driverref = new Driver();
			
			DriverManager.registerDriver(driverref);
			
			//step2: get connection from database
			
			con= DriverManager.getConnection(" jdbc:mysql://localhost:3306/students","root","root");
			
			System.out.println("connection eshtablished");
			
			//step3:issue create statement
			
			Statement state = con.createStatement();
			
			//step4: execute a querry
			//insert in to students values(6,'adarsha','japan');
			
			int result=state.executeUpdate("insert into students values('4','adarsha','japan');");
			if(result==1)
			{
				System.out.println("data added succesfully");
			}
			else
			{
				System.out.println("data not added");
			}
		} catch(Exception e)
		{
			//to handle exception
		}
		finally {
			
			//step5:close the database
			con.close();
			
			System.out.println("connection closed");
		}

	}

}
