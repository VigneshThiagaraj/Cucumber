package com.framework.config;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportManager {
	private static ExtentReports extent;
	public static ExtentSparkReporter sparkReporter;
	public static String resultsPath = System.getProperty("user.dir") + File.separator+"test-results";
	public static String testExecutionReportTitle;

	public static ExtentReports getInstance() {
		if (extent == null) {
			createInstance();
		}
		return extent;
	}

	public synchronized static ExtentReports createInstance() {

		createReportPath(resultsPath);
		String fileName = getReportFileLocation();
		sparkReporter = new ExtentSparkReporter(fileName);
		sparkReporter.config().setDocumentTitle(testExecutionReportTitle);
		sparkReporter.config().setReportName("AutomationExecutionReport");
		sparkReporter.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		return extent;
	}

	private static String getReportFileLocation() {
		String reportFileLocation = null;
		reportFileLocation = System.getProperty("user.dir") + File.separator+"test-results"+File.separator + testExecutionReportTitle;
		System.setProperty("reportName." + Thread.currentThread().getId(),testExecutionReportTitle);
		return reportFileLocation;
	}

	// Create the report path if it does not exist
	private static void createReportPath(String path) {
		File testDirectory = new File(path);
		if (!testDirectory.exists()) {
			if (testDirectory.mkdir()) {
				System.out.println("Directory: " + path + " is created!");
			} else {
				System.out.println("Failed to create directory: " + path);
			}
		}
	}

}
