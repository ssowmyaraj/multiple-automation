package com.multiple.utils.base;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportBuilder {

	private static ExtentReports report;

	public static ExtentReports createInstance(String fileName, String reportName) {

		ExtentSparkReporter sparkHtmlReporter = new ExtentSparkReporter(fileName);
		sparkHtmlReporter.config().setTheme(Theme.STANDARD);
		sparkHtmlReporter.config().setDocumentTitle("MultipleAutomationReport");
		sparkHtmlReporter.config().setEncoding("utf-8");
		sparkHtmlReporter.config().setReportName(reportName);
		report = new ExtentReports();
		report.setAnalysisStrategy(AnalysisStrategy.CLASS);
		report.attachReporter(sparkHtmlReporter);
		return report;

	}

}
