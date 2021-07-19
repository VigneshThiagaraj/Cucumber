package com.driver.setup;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {

	private static Properties readPropertiesFile(String fileName) throws IOException {
		FileInputStream fis = null;
		Properties prop = null;
		try {
			fis = new FileInputStream(fileName);
			prop = new Properties();
			prop.load(fis);
		} catch(FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		} finally {
			fis.close();
		}
		return prop;
	}

	private static void setPropertyValues(Properties prop) {
		for (String key : prop.stringPropertyNames()) {
			Properties properties = System.getProperties();
			if(key.contains("webdriver.") || key.contains("launch.url")) {
				properties.setProperty(key, prop.getProperty(key).trim());
			}else {
				properties.setProperty(key + Thread.currentThread().getId(), prop.getProperty(key).trim());
			}
			
		}
	}

	public static void loadProperty() {
		try {
			setPropertyValues(readPropertiesFile("C:\\Workspace\\Cucumber\\src\\main\\config\\Properties.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
