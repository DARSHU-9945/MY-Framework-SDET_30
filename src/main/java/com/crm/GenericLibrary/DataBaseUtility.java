package com.crm.GenericLibrary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import com.mysql.cj.jdbc.Driver;

/**
 * this class contains generic methods to read from database
 * 
 * @author Deepthi
 *
 */
public class DataBaseUtility {
	
	Connection con=null;
	
	/**
	 * this method will regester the driver and eshtablish connection with database
	 * @param URL
	 * @param UN
	 * @param PWD
	 * @param Query
	 * @throws Throwable
	 */
	
public void connectToDB() throws Throwable {
	
		Driver driver = new Driver();
		
		DriverManager.registerDriver(driver);
		
		con = DriverManager.getConnection(IpathConstants.dbURL,IpathConstants.dbUserName,IpathConstants.dbPassword );
		
   }

/**
 * this method will close database connection
 * @throws Throwable
 */

public void closeDB() throws Throwable {
	
	con.close();
}

/**
 * this method will execute the query and return the matching data to user
 * @param Query
 * @param columnIndex
 * @param expdata
 * @return
 * @throws Throwable
 */

public String executeQueryAndGetData(String Query,int columnIndex, String expdata) throws Throwable {
	
	String data=null;
	boolean flag = false;
	ResultSet result = con.createStatement().executeQuery(Query);
	while(result.next()) {
		
		data=result.getString(columnIndex);
		if(data.equalsIgnoreCase(expdata)) {
			
			flag=true; // flag rising;
			break;
		}
	}
	
	if(flag) 
	{
		System.out.println(data+"-----> data verififd");
		return expdata;
	}
	else 
	{
		System.out.println("data not verified");
	}
	return "";
	}
}