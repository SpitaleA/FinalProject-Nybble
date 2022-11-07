package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetProperties {
    private static GetProperties instance = null;
    private String host;
    private String user;
    private String pwd;
    private String browser;


    private GetProperties(){
        Properties properties = new Properties();
        String nameFile=System.getProperty("envFile")==null?"qa.properties":System.getProperty("envFile");
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(nameFile);
        if (inputStream!=null) {
            try {
                properties.load(inputStream);
                host = properties.getProperty("host");
                user = properties.getProperty("user");
                pwd = properties.getProperty("pwd");
                browser = properties.getProperty("browser");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public static GetProperties getInstance(){
        if (instance == null)
            instance= new GetProperties();
        return instance;
    }

    public String getHost() {
        return host;
    }

    public String getUser() {
        return user;
    }

    public String getPwd() {
        return pwd;
    }

    public String getBrowser() {
        return browser;
    }
}
