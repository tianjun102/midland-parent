package com.bluemobi.core.util;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Properties;
public class AppSetting {
	private static Properties prop = new Properties();    
    static{        
        try {
            //加载appsetting.properties配置文件
        	//prop.load(Config.class.getResourceAsStream("/appsetting.properties"));
            prop.load(new InputStreamReader(Config.class.getClassLoader().getResourceAsStream("/appsetting.properties"), "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String getAppSetting(String key) {
		return prop.getProperty(key);
	}
    
    public static void updateProperties(String keyname,String keyvalue) {
        try {
            OutputStream fos = new FileOutputStream("/appsetting.properties");           
            prop.setProperty(keyname, keyvalue);
            prop.store(fos, "Update '" + keyname + "' value");
            fos.close();
        } catch (IOException e) {
        	e.printStackTrace();
            System.err.println("属性文件更新错误");
        }
    }
}
