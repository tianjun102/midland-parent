package com.midland.core.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class AppSetting {
	private static Properties prop = new Properties();    
    static{        
        try {
            InputStream inputStream =
                    AppSetting.class.getClassLoader().getResourceAsStream("/properties/appsetting.properties");
             prop.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static String getAppSetting(String key) {
		return prop.getProperty(key);
	}
    
    public static void updateProperties(String keyname,String keyvalue) {
        try {
            OutputStream fos = new FileOutputStream("/properties/appsetting.properties");
            prop.setProperty(keyname, keyvalue);
            prop.store(fos, "Update '" + keyname + "' value");
            fos.close();
        } catch (IOException e) {
        	e.printStackTrace();
            System.err.println("属性文件更新错误");
        }
    }
}
