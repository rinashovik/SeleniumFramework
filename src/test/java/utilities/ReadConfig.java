package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {

	Properties pro;

//	public ReadConfig(Properties pro) {
//		super();
//		this.pro = pro;
//	}
//	
	
	public ReadConfig() {
		//File file = new File("./src/test/resources/testData/login_Testdata.xlsx");

		
		File file = new File("./src/test/resources/Configuration/config.properties");
		
		
		//String file ="./src/test/resources/Configuration/config.properties";
		
		try {
			FileInputStream fi = new FileInputStream(file);
			pro = new Properties();
			try {
				pro.load(fi);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public String getBaseURL() {
		
		String url = pro.getProperty("baseURL");		
		return url;
		
	}
	
	public String getUserName() {
		
		String useName = pro.getProperty("userName");
				return useName;
	}
	
	
public String getPasswaord() {
		
		String Password = pro.getProperty("Password");
		return Password;
	}



public String getBrowserName() {

	String browserName = pro.getProperty("browserName");
	return browserName;
	
}
public String getBrUrl() {
	
	return pro.getProperty("url");		
	
}
	
}
