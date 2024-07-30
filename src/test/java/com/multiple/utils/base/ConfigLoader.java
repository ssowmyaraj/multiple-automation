package com.multiple.utils.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {

	public static final String propertiesPath = System.getProperty("user.dir")
			+ "/src/main/resources/testdata.properties";
	public static final File file = new File(propertiesPath);

	public static String getValue(String Key) throws IOException {

		Properties prop = new Properties();
		FileInputStream fileInputStream = new FileInputStream(file);
		prop.load(fileInputStream);
		return prop.getProperty(Key);
	}
}
