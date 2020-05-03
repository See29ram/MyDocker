package com.dockerz.MyDocker;

import java.io.IOException;
import java.net.MalformedURLException;

import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ChromeStandAloneTest {

	
	@BeforeTest
	public void dockerInit() throws IOException, InterruptedException
	{
		new StartDockerHub().launchDocker();
		
	}
	
	@AfterTest
	public void dockerTeardown() throws IOException, InterruptedException
	{
		new StopDocker().killDocker();
	}
	
	
	
	@Test
	public void getGoogle() throws MalformedURLException {
		// System.setProperty("java.net.preferIPv4Stack" , "true");
		//URL url = new URL("http://172.18.0.2:4444/wd/hub");
		System.out.println("Test1 started");
		URL url = new URL("http://192.168.99.100:4444/wd/hub");
		// URL url=new URL("http://localhost:4444/wd/hub");
		// URL url=new URL("http://192.168.1.5:4444/wd/hub");
		DesiredCapabilities dc = DesiredCapabilities.chrome();
		RemoteWebDriver rDr = new RemoteWebDriver(url, dc);
		rDr.get("https://www.google.com");
		System.out.println(rDr.getCurrentUrl());
		System.out.println(rDr.getTitle());

		rDr.quit();
	}

}
