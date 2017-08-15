package kr.co.code.common.util;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

public class ConfigPropertiesManager {
	private static Properties prop;

	private static ClassLoader cl;
	private static boolean loadFlag = false;

	public static void PropertiesLoad() {
        try {
			cl = Thread.currentThread().getContextClassLoader();
			if ( cl == null ) cl = ClassLoader.getSystemClassLoader();
        	URL url = cl.getResource("system.properties");
        	String openPath = url.getPath();
        	prop =  new Properties();
        	
        	FileInputStream istream = new FileInputStream(openPath);
        	prop.load(istream);
        	
        	loadFlag=true;
	    } catch (Exception e) {
			e.printStackTrace();
	    }
	}

	public static String getProperties(String key) {
		if ( loadFlag == false ) {
			PropertiesLoad();
		}
		return prop.getProperty(key);
	}

	public static void setProperties(String key, String value) {
		prop.setProperty(key, value);
	}
}
