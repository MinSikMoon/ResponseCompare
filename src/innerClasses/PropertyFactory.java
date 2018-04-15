package innerClasses;

import java.util.Properties;

public class PropertyFactory {
	//1. members
	private Properties configFile;
			
	//constructor
	public PropertyFactory(String filePath){
		configFile = new Properties();
		try {
			configFile.load(this.getClass().getClassLoader().getResourceAsStream(filePath));
		} catch (Exception e) {
		}
	}
	
	public String getValue(String property){
		return configFile.getProperty(property);
	}
}
