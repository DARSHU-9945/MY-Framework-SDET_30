package com.crm.Practice;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(com.crm.GenericLibrary.ListenerImplementationClass.class)
public class readdatafromcommandTest {
	@Test
	public void readdatafromcommandline() {
		
    String BROWSER=System.getProperty("browser");		
    String URL=System.getProperty("url");
    String USERNAME=System.getProperty("username");
    String PASSWORD=System.getProperty("password");
    
    System.out.println(BROWSER);
    System.out.println(URL);
    System.out.println(USERNAME);
    System.out.println(PASSWORD);
		
	}

}
