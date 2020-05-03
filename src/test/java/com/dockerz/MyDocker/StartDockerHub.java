package com.dockerz.MyDocker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

import org.testng.Assert;



public class StartDockerHub {


	public void launchDocker() throws IOException, InterruptedException {
        System.out.println("Before Test Running..");
		//Runtime runTime = Runtime.getRuntime();
		//runTime.exec("cmd /c start docker_seleniumHub.bat");
       // ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "docker_seleniumHub.bat");
        //File dir = new File(System.getProperty("user.dir"));
       // pb.directory(dir);
        //Process p = pb.start();
		boolean flag = false;
		String logFile = "dockerLog.txt";
       
		File file=new File(logFile);
		while(!file.exists())
		{
			Thread.sleep(2000);
		}
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, 60);
		long stopNow = cal.getTimeInMillis();

		while (System.currentTimeMillis() < stopNow) {

			if(flag)
			{
				break;
			}
			BufferedReader br = new BufferedReader(new FileReader(logFile));
			String CurrentLine = br.readLine();
			
			while (CurrentLine != null && !flag) {
            
				if (CurrentLine.contains("Registered a node")) {
					System.out.println("Found Line..");
					flag = true;
					break;
				}

				CurrentLine = br.readLine();

			}
			br.close();
		}

		Assert.assertTrue(flag);
		Thread.sleep(3000);
		 System.out.println("Before Test completed..");
	}

}
