package com.crm.GenericLibrary;

import java.util.Random;

import java.util.Date;

/**
 * 
 * @author Deepthi
 *
 */


public class javaUtility {
	
	/**
	 * this method will generate a random numer and return it to user
	 * @return
	 */
	
	public int getRandomNumber() {
		Random ran = new Random();
		int random=ran.nextInt(500);
		return random;
	}
	
	/**
	 * this method will generate current system date and return it to user
	 * @return
	 */
	
	public String getSystemDate() {
		
		Date d= new Date();
		String date=d.toString();
		return date;
	}
	
	/**
	 * this method will generate system date and return date in format
	 * @return
	 */
	
	public String getSystemDateInFormat()
	{
		Date d= new Date();
		String d1 = d.toString();
		String[] date = d1.split(" ");
		
		String mon= date[1];
		String day= date[2];
		String time= date[3].replace(":", "-");
		String year=date[5];
		
		String DateFormat= day+"-"+mon+"-"+year+"-"+time;
		return DateFormat;
	}
	
}
