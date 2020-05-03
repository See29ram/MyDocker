package com.dockerz.MyDocker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class StopDocker {

	@AfterTest
	public void killDocker() throws IOException, InterruptedException {

		System.out.println("After suite Running");
		//Runtime runTime = Runtime.getRuntime();
		//runTime.exec("cmd /c start dockerStop.bat");
		
		boolean flag = false;

		String logFile = "dockerLog.txt";

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, 50);
		long stopNow = cal.getTimeInMillis();

		while (System.currentTimeMillis() < stopNow) {

			if (flag) {
				break;
			}
			BufferedReader br = new BufferedReader(new FileReader(logFile));
			String CurrentLine = br.readLine();
			while (CurrentLine != null && !flag) {

				if (CurrentLine.contains("Shutdown complete")) {
					System.out.println("Shut Down Line Found..");
					flag = true;
					break;
				}

				CurrentLine = br.readLine();

			}
			br.close();
		}

		Thread.sleep(5000);
		File DellogFile = new File(logFile);
		System.out.println(DellogFile.exists());
		if (DellogFile.delete()) {
			System.out.println("Log File Deleted");
		} else {
			System.out.println("Unable to delete log file");
		}

	}

}
